package com.udacity.quizapp;

import com.udacity.quizapp.ui.models.Question;
import com.udacity.quizapp.ui.models.SelectableAnswer;

public class MultipleSelectionGrader implements Grader<Question<SelectableAnswer>> {
    @Override
    public boolean isQuestionRight(Question<SelectableAnswer> question) {
        for (SelectableAnswer selectableAnswer : question.getAnswerOptions()) {
            boolean isCorrect = (selectableAnswer.isSelected() && selectableAnswer.isCorrect())
                    || (!selectableAnswer.isCorrect() && !selectableAnswer.isSelected());
            if (!isCorrect) {
                return false;
            }
        }
        return true;
    }
}
