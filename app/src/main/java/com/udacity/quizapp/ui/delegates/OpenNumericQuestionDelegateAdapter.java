package com.udacity.quizapp.ui.delegates;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;

import com.udacity.quizapp.R;
import com.udacity.quizapp.ui.models.OpenNumericQuestion;

public class OpenNumericQuestionDelegateAdapter extends BaseQuestionDelegateAdapter<OpenNumericQuestionDelegateAdapter.OpenNumericQuestionViewHolder, OpenNumericQuestion> {

    private final OnOpenNumericAnswerEdited listener;

    public OpenNumericQuestionDelegateAdapter(OnOpenNumericAnswerEdited listener) {
        this.listener = listener;
    }

    @Override
    public OpenNumericQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OpenNumericQuestionViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(OpenNumericQuestionViewHolder holder, OpenNumericQuestion question) {
        super.onBindViewHolder(holder, question);
        if (question.getUserResponse() != null) {
            holder.numericAnswer.setText(question.getUserResponse().getValue());
        }
        if (question.isGraded()) {
            holder.numericAnswer.setEnabled(false);
        }
    }

    public interface OnOpenNumericAnswerEdited {
        void onAnswerChanged(int questionPosition, String answerValue);
    }

    public class OpenNumericQuestionViewHolder extends BaseQuestionDelegateAdapter.BaseQuestionViewHolder {

        EditText numericAnswer;

        public OpenNumericQuestionViewHolder(ViewGroup parent) {
            super(parent, R.layout.layout_item_numeric_open_question);
            numericAnswer = itemView.findViewById(R.id.edit_text_numeric_answer);
            numericAnswer.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && listener != null) {
                        listener.onAnswerChanged(pos, s.toString());
                    }

                }
            });
        }
    }

}
