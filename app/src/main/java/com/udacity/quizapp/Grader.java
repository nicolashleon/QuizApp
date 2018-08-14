package com.udacity.quizapp;

import com.udacity.quizapp.ui.models.Question;

public interface Grader<Q extends Question> {
    boolean isQuestionRight(Q question);
}
