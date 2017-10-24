package ru.kakDela.notes.dao;

import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.kakDela.notes.stuf.FilterTypes;
import ru.kakDela.notes.NoteEntity;
import ru.kakDela.notes.stuf.SortingTypes;


import java.util.List;

/**
 * Created by Михаил on 18.10.2017.
 * Реализация интерфейса NoteDao  для Hibernate/
 */
@Component
public class NoteDaoHibernateImpl implements NoteDao {
    private static final SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(NoteDaoHibernateImpl.class);

    static {

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        SessionFactory factory = null;
        try {
            factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            logger.error("Ошибка создания фабрики сессий Hibernate");
            StandardServiceRegistryBuilder.destroy(registry);
        }
        sessionFactory = factory;
    }

    /**
     * Создает и возвращает новую ссесию Hibernate
     *
     * @return Session -сеанс с БД
     */
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }


    /**
     * Создает объект класса NoteEntity  в БД.
     *
     * @param note - новый объект в БД.
     */
    @Override
    public void insert(NoteEntity note) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.persist(note);
            tx.commit();
            logger.info("Запись успешно сохранена в БД , " + note);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Вставить элемент не удалось " + note, e);
        }

    }
    /**
     * Возвращает количество элементов в  БД.
     *
     * @return Long count .
     */
    @Override
    public long size() {
        Long countResults = 0L;
        try (Session session = getSession()) {
            String countQ = "Select count (*) from NoteEntity n";
            Query countQuery = session.createQuery(countQ);
           countResults = (Long) countQuery.uniqueResult();
        }
        return countResults;
    }
    @Override
    public long size(FilterTypes filter) {
        Long countResults = 0L;
        try (Session session = getSession()) {
            String countQ = "Select count (*) from NoteEntity n " + filter.toString();
            Query countQuery = session.createQuery(countQ);
            countResults = (Long) countQuery.uniqueResult();
        }
        return countResults;
    }

    /**
     * Обновляет данные объекта note класса NoteEntity  в БД.
     *
     * @param note - существующий объект в БД.
     */
    @Override
    public void update(NoteEntity note) {
        Transaction tx = null;
        try (Session session = getSession()) {
            tx = session.beginTransaction();
            session.update(note);
            tx.commit();
            logger.info("Запись успешно обнолвена в БД , " + note);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error(" Обновить элемент не удалось " + note, e);
        }
    }

    /**
     * Удаляет объект класса NoteEntity  с заданным id из БД.
     *
     * @param id - id существующиtuj объекта в БД.
     */
    @Override
    public void remove(int id) {
        Session session = getSession();
        session.beginTransaction();
        NoteEntity p =  session.load(NoteEntity.class, id);
        if(null != p){
            session.delete(p);
            logger.info("Запись успешно удалена :"+p);
            session.getTransaction().commit();
        } else {
            logger.info("Запись не удалена, такой нет в бд:"+ id);
        }
    }

    /**
     * Удаляет объект note класса NoteEntity  из БД.
     *
     * @param note - существующий объект в БД.
     */
    @Override
    public void remove(NoteEntity note) {
            remove(note.getId());
    }

    /**
     * Возвращает весь список объеков класса NoteEntity с данными извлеченными из БД.
     *
     * @return List<NoteEntity>  list - список обьектов класса NoteEntity, если база пуста , то и список пустой.
     */
    @Override
    public List<NoteEntity> getAll() {
        final Session session = getSession();
        List<NoteEntity> list = (List<NoteEntity>) session.createQuery("FROM NoteEntity ").list();
        session.close();
        return list;
    }

    /**
     * Возвращает объект класса NoteEntity с данными извлеченными из БД.
     * Для поиска используется идентификатор.
     *
     * @param id - соответствует колонке идентификаторов в БД.
     * @return объект класса NoteEntity, если такого нет  - Null.
     */
    @Override
    public NoteEntity getById(int id) {
        Session session = getSession();
        NoteEntity p =  session.get(NoteEntity.class, id);
        if(null != p){
            logger.info("Запись успешно получена :"+ p);
        } else {
            logger.info("Запись не получена, такой нет в бд:"+ id);
        }
        session.close();
        return p;
    }

    /**
     * Возвращает список объектов класса NoteEntity с данными извлеченными из БД количеством countOfNotes
     * и отсортированные по критерию type.
     * Для поиска используется идентификатор.
     *
     * @param orderBy         - тип сортировки из enum SortingTypes.
     * @param filterBy         - тип сортировки из enum FilterTypes.
     * @param startPosition - позиция с которой начинать показ  записей(записок-задач) из БД.
     * @param lastPosition - позиция до которой показывать  записи(записки-задачи) из БД.
     * @return List<NoteEntity>, если результат нулевой, списк пустой.
     */
    @Override
    public List<NoteEntity> find(SortingTypes orderBy, FilterTypes filterBy, int startPosition, int lastPosition) {
        Session session = getSession();
        Query query = session.createQuery("from NoteEntity as n "+ filterBy.toString() +" order by " + orderBy.toString());
       // query.setParameter("paramName", directionAndType.toString());
        query.setFirstResult(startPosition);
        query.setMaxResults(lastPosition);
        return (List<NoteEntity>)query.getResultList();
    }

    /**
     * Возвращает список объектов класса NoteEntity с данными извлеченными из БД количеством countOfNotes
     * и отсортированные по критерию type.
     * Для поиска используется идентификатор.
     *
     * @param type - тип фильтрации из enum FilterTypes.
     * @param startPosition - позиция с которой начинать показ  записей(записок-задач) из БД.
     * @param lastPosition - позиция до которой показывать  записи(записки-задачи) из БД.
     * @return List<NoteEntity>, если результат нулевой, списк пустой.
     */
    @Override
    public List<NoteEntity> filter(FilterTypes type, int startPosition, int lastPosition) {
        StringBuilder queryStr = new StringBuilder("from NoteEntity as n");
        Session session = getSession();

        Query query = session.createQuery(queryStr.toString());
        query.setFirstResult(startPosition);
        query.setMaxResults(lastPosition);
        return (List<NoteEntity>)query.getResultList();
    }

    /**
     * Очистка базы.
     */
    @Override
    public void removeAll() {
        Session session = getSession();
        session.beginTransaction();
        for (NoteEntity note : getAll()) {
            session.remove(note);
        }
        session.getTransaction().commit();
        logger.info("Все успешно удалено.");

    }
   /* public static void main(String[] args){
        NoteDaoHibernateImpl base = new NoteDaoHibernateImpl();
        NoteEntity test = new NoteEntity();
        test.setContent("Это тест Дао.");
        test.setTitle("Title Test");
        test.setPriority(7);
        NoteEntity test2 = new NoteEntity();
        test2.setContent("Это тест Дао2.");
        test2.setTitle("Title Tes2t");
        test2.setPriority(1);
        base.insert(test);
        for (NoteEntity n : base.getAll()){
            System.out.println(n);}
        NoteEntity g = base.get(3);
        g.setContent("Test get and update");
            base.update(g);
            base.remove(5);
       for (NoteEntity note : base.sortAndGetSome(SortingTypes.ByPriorityDesc, 0,10)){
           System.out.println(note);
       }
        System.out.println("==============");
        for (NoteEntity note : base.sortAndGetSome(SortingTypes.ByPriorityAsc, 0,10)){
            System.out.println(note);
        }
        System.out.println("==============");
        for (NoteEntity note : base.sortAndGetSome(SortingTypes.ByDateAsc, 0,10)){
            System.out.println(note);
        }
        System.out.println("==============");
        for (NoteEntity note : base.sortAndGetSome(SortingTypes.ByDateDesc, 0,10)){
            System.out.println(note);
        }
        System.out.println("==============");
        for (NoteEntity note : base.filter(FilterTypes.ALL, 0,10)){
            System.out.println(note);
        }
        System.out.println("==============");
        for (NoteEntity note : base.filter(FilterTypes.ALL, 0,2)){
            System.out.println(note);
        }
        System.out.println("==============");
        for (NoteEntity note : base.filter(FilterTypes.DONE, 0,10)){
            System.out.println(note);
        }
        System.out.println("==============");
        for (NoteEntity note : base.filter(FilterTypes.UNDONE, 0,10)){
            System.out.println(note);
        }
        System.out.println("==============");
        for (NoteEntity note : base.filter(FilterTypes.TASK, 0,10)){
            System.out.println(note);
        }
        System.out.println("==============");
    }*/
}