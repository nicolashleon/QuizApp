package com.udacity.quizapp;

import com.udacity.quizapp.ui.models.OpenNumericQuestion;

public class OpenQuestionGrader implements Grader<OpenNumericQuestion> {
    @Override
    public boolean isQuestionRight(OpenNumericQuestion question) {
        return question.getUserResponse() != null &&
                question.getAnswerOptions().get(0).getValue().equals(question.getUserResponse().getValue());
    }
}
