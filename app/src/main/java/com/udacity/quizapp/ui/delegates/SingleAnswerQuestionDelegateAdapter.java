package com.udacity.quizapp.ui.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.udacity.quizapp.R;
import com.udacity.quizapp.ui.models.Question;
import com.udacity.quizapp.ui.models.SelectableAnswer;

public class SingleAnswerQuestionDelegateAdapter extends BaseQuestionDelegateAdapter<SingleAnswerQuestionDelegateAdapter.SingleAnswerQuestionViewHolder, Question<SelectableAnswer>> {

    private OnSingleAnswerSelectedListener listener;

    public SingleAnswerQuestionDelegateAdapter(OnSingleAnswerSelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public SingleAnswerQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SingleAnswerQuestionViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(SingleAnswerQuestionViewHolder holder, Question<SelectableAnswer> question) {
        super.onBindViewHolder(holder, question);
        holder.radioGroup.clearCheck();
        for (int i = 0; i < holder.radioGroup.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) holder.radioGroup.getChildAt(i);
            SelectableAnswer answer = question.getAnswerOptions().get(i);
            radioButton.setText(answer.getValue());
            radioButton.setChecked(answer.isSelected());
            radioButton.setEnabled(!question.isGraded());
        }
    }

    public interface OnSingleAnswerSelectedListener {
        void onAnswerSelected(int modelPosition, int answerPosition);
    }

    public class SingleAnswerQuestionViewHolder extends BaseQuestionDelegateAdapter.BaseQuestionViewHolder {

        RadioGroup radioGroup;
        RadioButton answerOption1;
        RadioButton answerOption2;
        RadioButton answerOption3;
        RadioButton answerOption4;

        public SingleAnswerQuestionViewHolder(ViewGroup parent) {
            super(parent, R.layout.layout_item_single_answer_question);
            radioGroup = itemView.findViewById(R.id.radio_group_answer_options);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int pos = getAdapterPosition();
                    int checkPosition = getRadioButtonPosition(checkedId);
                    if (pos != RecyclerView.NO_POSITION && checkPosition != -1 && listener != null) {
                        listener.onAnswerSelected(pos, checkPosition);
                    }
                }
            });
            answerOption1 = itemView.findViewById(R.id.radio_btn_opt_1);
            answerOption2 = itemView.findViewById(R.id.radio_btn_opt_2);
            answerOption3 = itemView.findViewById(R.id.radio_btn_opt_3);
            answerOption4 = itemView.findViewById(R.id.radio_btn_opt_4);
        }

        private int getRadioButtonPosition(int checkedId) {
            for (int i = 0; i < radioGroup.getChildCount() && checkedId > -1; i++) {
                RadioButton radioButton = (RadioButton) radioGroup.getChildAt(i);
                if (radioButton.getId() == checkedId) {
                    return i;
                }
            }
            return -1;
        }
    }
}
