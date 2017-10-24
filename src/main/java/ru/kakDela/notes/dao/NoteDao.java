package ru.kakDela.notes.dao;



import ru.kakDela.notes.stuf.FilterTypes;
import ru.kakDela.notes.NoteEntity;
import ru.kakDela.notes.stuf.SortingTypes;

import java.util.List;

/**
 * Created by Михаил on 18.10.2017.
 * Интерфейс DAO (Data Access Object) -содержит методы по работе с  обьектами NoteEntity из разных источников.
 */


public interface NoteDao{

    void insert(NoteEntity note);
    long size();
    long size(FilterTypes type);
    void update(NoteEntity note);
    void remove(int id);
    void remove(NoteEntity note);
    List<NoteEntity> getAll();
    NoteEntity getById(int id);
    List<NoteEntity> find(SortingTypes type, FilterTypes filterBy, int startPosition, int lastPosition);
    List<NoteEntity> filter(FilterTypes type, int startPosition, int lastPosition);
    void removeAll();
}
