package ru.kakDela.notes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Михаил on 18.10.2017.
 * Класс для работы с базой данных при помощи библиотеки Hibernate
 */
@Component
@JsonAutoDetect
@Entity
@Table(name = "notes_task", schema = "test")
public class NoteEntity {
    private int id;
    private String title;
    private String content;
    private int parent;
    private int priority;
    private Timestamp createdDate;
    private byte done;
    private byte task;

    /**
     * @return int id  - Служебное поле базы, служит для индефикации записи. Имеет уникальное значение.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    public int getId() {
        return id;
    }

    /**
     * @param id -  Служебное поле базы, служит для индефикации записи. Имеет уникальное значение.
     *
     */
    //TODO: разобраться нужен ли публичный setter .По идеи, не нужен сеттер, т.к. значение устанавливается автоматом.
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return Заголовок заметки
     */
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    /**
     * @param title - Заголовок заметки
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Содержимое заметки.
     */
    //TODO: По заданию это веб приложение, так что надо бы сделать подержку html \bbcode(bold, italic и тд).Да и картинки тоже
    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    /**
     * @param content - Содержимое заметки.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return id родительской заметки, нужно для разбиение задачи на подзадачи, либо использования как папки.
     */
    @Basic
    @Column(name = "parent")
    public int getParent() {
        return parent;
    }

    /**
     * @param parent - id родительской заметки, нужно для разбиение задачи на подзадачи, либо использования как папки.
     */
    public void setParent(int parent) {
        this.parent = parent;
    }

    /**
     * @return приоритет задачи-заметки. 1-10, где 1- самое неотлагаемое, 10 - после дождика в четверг.
     */
    @Basic
    @Column(name = "priority")
    public int getPriority() {
        return priority;
    }

    /**
     * @param priority приоритет задачи-заметки. 1-10, где 1- самое неотлагаемое, 10 - после дождика в четверг.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * @return дата написания \ обновления заметки
     */
    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate дата написания \ обновления заметки
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return 0 - не задача-заметка не выолнена еще, 1 - выполнена
     */
    @Column(name = "is_done")
    public byte getDone() {
        return done;
    }

    /**
     * @param isDone 0 - не задача-заметка не выолнена еще, 1 - выполнена
     */
    public void setDone(byte isDone) {
        this.done = isDone;
    }


    /**
     * @return является ли заметка задачей ( 0 - нет, 1 - да)
     */
    @Column(name = "is_task")
    public byte getTask() {
        return task;
    }

    /**
     * @return  дату в  человеческом  формате
     */
    public String formatDate() {
        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(createdDate);
        return formattedDate;
    }

    /**
     * @param isTask является ли заметка задачей ( 0 - нет, 1 - да)
     */
    public void setTask(byte isTask) {
        this.task = isTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteEntity that = (NoteEntity) o;

        if (id != that.id) return false;
        if (parent != that.parent) return false;
        if (priority != that.priority) return false;
        if (done != that.done) return false;
        if (task != that.task) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + parent;
        result = 31 * result + priority;
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (int) done;
        result = 31 * result + (int) task;
        return result;
    }

    @Override
    public String toString() {
        return "NotesEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", parent=" + parent +
                ", priority=" + priority +
                ", createdDate=" + createdDate +
                ", isDone=" + done +
                ", isTask=" + task +
                '}';
    }
}
