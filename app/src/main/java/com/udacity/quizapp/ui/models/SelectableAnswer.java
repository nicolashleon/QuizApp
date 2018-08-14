package com.udacity.quizapp.ui.models;

import java.io.Serializable;

public class SelectableAnswer extends Answer implements Serializable {

    protected boolean isSelected;

    public SelectableAnswer(String value, boolean isCorrect) {
        super(value, isCorrect);
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
