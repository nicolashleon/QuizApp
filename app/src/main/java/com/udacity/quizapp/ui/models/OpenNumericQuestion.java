package com.udacity.quizapp.ui.models;

import java.io.Serializable;

import static com.udacity.quizapp.ui.models.Question.Type.OPEN_NUMERIC;

public class OpenNumericQuestion extends Question<Answer> implements Serializable {

    private Answer userResponse;

    public OpenNumericQuestion(Builder builder) {
        super(builder);
    }

    public Answer getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(Answer userResponse) {
        this.userResponse = userResponse;
    }

    @Override
    public int getViewType() {
        return OPEN_NUMERIC;
    }

    public static class Builder extends Question.Builder<Answer, OpenNumericQuestion> {

        public Builder(String question, int type) {
            super(question, type);
        }

        @Override
        public OpenNumericQuestion build() {
            return new OpenNumericQuestion(this);
        }
    }
}
