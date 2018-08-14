package com.udacity.quizapp;

import com.udacity.quizapp.ui.models.Question;
import com.udacity.quizapp.ui.models.SelectableAnswer;

public class SingleAnswerGrader implements Grader<Question<SelectableAnswer>> {
    @Override
    public boolean isQuestionRight(Question<SelectableAnswer> question) {
        for (SelectableAnswer selectableAnswer : question.getAnswerOptions()) {
            if (selectableAnswer.isSelected() && selectableAnswer.isCorrect()) {
                return true;
            }
        }
        return false;
    }
}
