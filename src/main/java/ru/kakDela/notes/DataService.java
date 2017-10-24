package ru.kakDela.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kakDela.notes.dao.NoteDao;
import ru.kakDela.notes.stuf.FilterTypes;
import ru.kakDela.notes.stuf.SortingTypes;


import java.util.List;

/**
 * Created by Михаил on 19.10.2017.
 *
 */
//Должно было быть бизнес-логикой, получилось художник знает что.
@Service
public class DataService {
    private final NoteDao dao;

    @Autowired
    public DataService(NoteDao dao) {
        this.dao = dao;
    }

    public void insert(NoteEntity note) {
        dao.insert(note);
    }


    public long size() {
        return dao.size();
    }
    public long size(FilterTypes type) {
        return dao.size(type);
    }

    public void update(NoteEntity note) {
        dao.update(note);
    }

    public void remove(int id) {
        dao.remove(id);
    }

    public void remove(NoteEntity note) {
        dao.remove(note);
    }

    public List<NoteEntity> getAll() {
        return dao.getAll();
    }

    public NoteEntity getById(int id)
    {
        return dao.getById(id);
    }

    public List<NoteEntity> find(SortingTypes orderBy, FilterTypes sortBy, String page, String limit) {
        int startPosition = Integer.parseInt(page) * Integer.parseInt(limit);
        int lastPosition =  Integer.parseInt(limit);
        return dao.find(orderBy, sortBy, startPosition, lastPosition);
    }

    public List<NoteEntity> filter(FilterTypes type, int startPosition, int lastPosition) {
        return dao.filter(type, startPosition, lastPosition);
    }

    public int countOfPages(String limit, FilterTypes filter){
        long count = size(filter);
        return (int) Math.ceil(count / Integer.parseInt(limit));
    }
    public void removeAll() {
        dao.removeAll();
    }
}
