package ru.kakDela.notes.stuf;

/**
 * Created by Михаил on 18.10.2017.
 */
public enum SortingTypes {
    ByDateAsc("created_date asc"),
    ByDateDesc("created_date desc"),
    ByPriorityAsc("priority asc"),
    ByPriorityDesc("priority desc");

    private String stringPresentation;

    SortingTypes(String stringPresentation) {
        this.stringPresentation = stringPresentation;
    }

    @Override
    public String toString() {
        return stringPresentation;
    }
}
