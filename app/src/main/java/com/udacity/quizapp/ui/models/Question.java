package com.udacity.quizapp.ui.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Question<T extends Answer> implements DelegateUIModel, Serializable {

    protected List<T> answerOptions;
    protected String question;
    protected boolean isCorrect;
    protected boolean isGraded;
    protected int viewType;

    protected Question(Question.Builder builder) {
        question = builder.question;
        answerOptions = builder.answerOptions;
        viewType = builder.type;
    }

    public List<T> getAnswerOptions() {
        return answerOptions;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public boolean isGraded() {
        return isGraded;
    }

    public void setGraded(boolean graded) {
        isGraded = graded;
    }

    @Override
    public int getViewType() {
        return viewType;
    }

    public static class Type {
        public final static int OPEN_NUMERIC = 1;
        public final static int SINGLE_ANSWER = 2;
        public final static int MULTIPLE_ANSWER = 3;
    }

    public static class Builder<T extends Answer, Q extends Question<T>> {

        private List<T> answerOptions = new ArrayList<>();
        private String question;
        private int type;

        public Builder(String question, int type) {
            this.question = question;
            this.type = type;
        }

        public Builder<T, Q> addAnswerOption(T answer) {
            answerOptions.add(answer);
            return this;
        }

        public Question<T> build() {
            return new Question<T>(this);
        }
    }
}
