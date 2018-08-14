package com.udacity.quizapp.ui.models;

import java.io.Serializable;

public class Answer implements Serializable {

    protected String value;
    protected boolean isCorrect;

    public Answer(String value) {
        this(value, false);
    }
    public Answer(String value, boolean isCorrect) {
        this.value = value;
        this.isCorrect = isCorrect;
    }

    public String getValue() {
        return value;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
