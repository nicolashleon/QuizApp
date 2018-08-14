package com.udacity.quizapp.ui.models;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.udacity.quizapp.R;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends ViewModel {

    private MutableLiveData<List<Question>> values;
    private boolean isGraded;

    private static MutableLiveData<List<Question>> generateCyclistQuizData(Context context) {
        MutableLiveData<List<Question>> data = new MutableLiveData<>();
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question.Builder<SelectableAnswer, Question<SelectableAnswer>>(context.getString(R.string.txt_cyclist_question_1), Question.Type.MULTIPLE_ANSWER)
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_1_answer_1), true))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_1_answer_2), true))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_1_answer_3), false))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_1_answer_4), false))
                .build());

        questions.add(new Question.Builder<SelectableAnswer, Question<SelectableAnswer>>(context.getString(R.string.txt_cyclist_question_2), Question.Type.SINGLE_ANSWER)
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_2_answer_1), true))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_2_answer_2), false))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_2_answer_3), false))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_2_answer_4), false))
                .build());

        questions.add(new OpenNumericQuestion.Builder(context.getString(R.string.txt_cyclist_question_3), Question.Type.OPEN_NUMERIC)
                .addAnswerOption(new Answer(context.getString(R.string.txt_cyclist_question_3_answer_1), true))
                .build());


        questions.add(new Question.Builder<SelectableAnswer, Question<SelectableAnswer>>(context.getString(R.string.txt_cyclist_question_4), Question.Type.SINGLE_ANSWER)
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_4_answer_1), true))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_4_answer_2), false))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_4_answer_3), false))
                .addAnswerOption(new SelectableAnswer(context.getString(R.string.txt_cyclist_question_4_answer_4), false))
                .build());

        data.postValue(questions);
        return data;

    }

    public MutableLiveData<List<Question>> setQuizQuestions(List<Question> questions) {
        values = new MutableLiveData<>();
        values.postValue(questions);
        return values;
    }

    public MutableLiveData<List<Question>> getCyclistQuizQuestions(Context context) {
        if (values == null) {
            values = generateCyclistQuizData(context);
        }
        return values;
    }

    public boolean isGraded() {
        return isGraded;
    }

    public void setGraded(boolean graded) {
        isGraded = graded;
    }
}
