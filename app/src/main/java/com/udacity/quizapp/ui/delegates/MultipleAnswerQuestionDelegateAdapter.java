package com.udacity.quizapp.ui.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.udacity.quizapp.R;
import com.udacity.quizapp.ui.models.Question;
import com.udacity.quizapp.ui.models.SelectableAnswer;

public class MultipleAnswerQuestionDelegateAdapter extends BaseQuestionDelegateAdapter<MultipleAnswerQuestionDelegateAdapter.MultipleAnswerQuestionViewHolder, Question<SelectableAnswer>> {


    private final OnMultipleAnswerCheckedListener listener;

    public MultipleAnswerQuestionDelegateAdapter(OnMultipleAnswerCheckedListener listener) {
        this.listener = listener;
    }

    @Override
    public MultipleAnswerQuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MultipleAnswerQuestionViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(MultipleAnswerQuestionViewHolder holder, Question<SelectableAnswer> question) {
        super.onBindViewHolder(holder, question);
        for (int i = 0; i < holder.checkboxGroup.getChildCount(); i++) {
            CheckBox checkBox = (CheckBox) holder.checkboxGroup.getChildAt(i);
            SelectableAnswer answer = question.getAnswerOptions().get(i);
            checkBox.setText(answer.getValue());
            checkBox.setChecked(answer.isSelected());
            checkBox.setEnabled(!question.isGraded());
        }
    }

    public interface OnMultipleAnswerCheckedListener {
        void onAnswerChecked(int modelPosition, int answerPosition, boolean isChecked);
    }


    public class MultipleAnswerQuestionViewHolder extends BaseQuestionDelegateAdapter.BaseQuestionViewHolder {

        LinearLayout checkboxGroup;
        CheckBox answerOption1;
        CheckBox answerOption2;
        CheckBox answerOption3;
        CheckBox answerOption4;

        public MultipleAnswerQuestionViewHolder(ViewGroup parent) {
            super(parent, R.layout.layout_item_multiple_answers_question);
            checkboxGroup = itemView.findViewById(R.id.checkbox_group);

            CompoundButton.OnCheckedChangeListener checkListener = new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton button, boolean isChecked) {
                    int pos = getAdapterPosition();
                    int checkPosition = getCheckboxPosition(button.getId());
                    if (listener != null && pos != RecyclerView.NO_POSITION && checkPosition != -1) {
                        listener.onAnswerChecked(pos, checkPosition, isChecked);
                    }
                }
            };

            answerOption1 = itemView.findViewById(R.id.checkbox_opt_1);
            answerOption2 = itemView.findViewById(R.id.checkbox_opt_2);
            answerOption3 = itemView.findViewById(R.id.checkbox_opt_3);
            answerOption4 = itemView.findViewById(R.id.checkbox_opt_4);
            answerOption1.setOnCheckedChangeListener(checkListener);
            answerOption2.setOnCheckedChangeListener(checkListener);
            answerOption3.setOnCheckedChangeListener(checkListener);
            answerOption4.setOnCheckedChangeListener(checkListener);
        }

        private int getCheckboxPosition(int checkedId) {
            for (int i = 0; i < checkboxGroup.getChildCount() && checkedId > -1; i++) {
                CheckBox checkBox = (CheckBox) checkboxGroup.getChildAt(i);
                if (checkBox.getId() == checkedId) {
                    return i;
                }
            }
            return -1;
        }
    }
}
