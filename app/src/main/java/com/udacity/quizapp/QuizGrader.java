package com.udacity.quizapp;

import android.util.Pair;
import android.util.SparseArray;

import com.udacity.quizapp.ui.models.Question;

import java.util.List;

public class QuizGrader {

    private SparseArray<Grader> graders = new SparseArray<>();

    public QuizGrader() {
        graders.put(Question.Type.OPEN_NUMERIC, new OpenQuestionGrader());
        graders.put(Question.Type.SINGLE_ANSWER, new SingleAnswerGrader());
        graders.put(Question.Type.MULTIPLE_ANSWER, new MultipleSelectionGrader());
    }

    public Pair<Integer, Integer> gradeQuiz(List<Question> questions) {
        int rightAnswers = 0;
        for (Question question : questions) {
            Grader grader = graders.get(question.getViewType());
            if (grader != null) {
                if (grader.isQuestionRight(question)) {
                    question.setCorrect(true);
                    question.setGraded(true);
                    rightAnswers++;
                } else {
                    question.setCorrect(false);
                    question.setGraded(true);
                }
            }
        }
        return new Pair<>(rightAnswers, questions.size());
    }

}
