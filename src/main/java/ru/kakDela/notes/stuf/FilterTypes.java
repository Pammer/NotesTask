package ru.kakDela.notes.stuf;

/**
 * Created by Михаил on 20.10.2017.
 */
public enum FilterTypes {
    DONE("where n.done = 1"),
    UNDONE("where n.done = 0"),
    ALL(""),
    TASK("where n.task = 1"),
    TEXTNOTE("where n.task = 0");

    private String stringPresentation;

    FilterTypes(String stringPresentation) {
        this.stringPresentation = stringPresentation;
    }

    @Override
    public String toString() {
        return stringPresentation;
    }
}
