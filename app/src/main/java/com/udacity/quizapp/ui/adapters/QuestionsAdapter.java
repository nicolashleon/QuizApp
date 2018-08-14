package com.udacity.quizapp.ui.adapters;

import com.udacity.quizapp.ui.delegates.MultipleAnswerQuestionDelegateAdapter;
import com.udacity.quizapp.ui.delegates.OpenNumericQuestionDelegateAdapter;
import com.udacity.quizapp.ui.delegates.SingleAnswerQuestionDelegateAdapter;
import com.udacity.quizapp.ui.models.Answer;
import com.udacity.quizapp.ui.models.DelegateUIModel;
import com.udacity.quizapp.ui.models.OpenNumericQuestion;
import com.udacity.quizapp.ui.models.Question;
import com.udacity.quizapp.ui.models.SelectableAnswer;

import java.util.ArrayList;

public class QuestionsAdapter extends BaseAdapter implements SingleAnswerQuestionDelegateAdapter.OnSingleAnswerSelectedListener, MultipleAnswerQuestionDelegateAdapter.OnMultipleAnswerCheckedListener, OpenNumericQuestionDelegateAdapter.OnOpenNumericAnswerEdited {

    public QuestionsAdapter() {
        super();
        addDelegate(new MultipleAnswerQuestionDelegateAdapter(this), Question.Type.MULTIPLE_ANSWER);
        addDelegate(new SingleAnswerQuestionDelegateAdapter(this), Question.Type.SINGLE_ANSWER);
        addDelegate(new OpenNumericQuestionDelegateAdapter(this), Question.Type.OPEN_NUMERIC);
    }


    @Override
    public void onAnswerSelected(int modelPosition, int answerPosition) {
        Question<SelectableAnswer> question = getItem(modelPosition);
        for (int i = 0; i < question.getAnswerOptions().size(); i++) {
            question.getAnswerOptions().get(i).setSelected(i == answerPosition);
        }
    }

    @Override
    public void onAnswerChecked(int modelPosition, int answerPosition, boolean isChecked) {
        Question<SelectableAnswer> question = getItem(modelPosition);
        question.getAnswerOptions().get(answerPosition).setSelected(isChecked);
    }

    @Override
    public void onAnswerChanged(int modelPosition, String answerValue) {
        OpenNumericQuestion question = getItem(modelPosition);
        question.setUserResponse(new Answer(answerValue));
    }

    public ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        for (DelegateUIModel model : delegateUIModels) {
            if (model instanceof Question) {
                questions.add((Question) model);
            }
        }
        return questions;
    }
}
