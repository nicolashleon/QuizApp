package com.udacity.quizapp.ui.delegates;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.quizapp.R;
import com.udacity.quizapp.ui.models.Question;

public abstract class BaseQuestionDelegateAdapter<VH extends BaseQuestionDelegateAdapter.BaseQuestionViewHolder, Q extends Question> implements DelegateAdapter<VH, Q> {

    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, Q question) {
        holder.question.setText(question.getQuestion());
        if (question.isGraded()) {
            holder.question.setCompoundDrawablesWithIntrinsicBounds(0, 0, question.isCorrect() ? R.drawable.ic_check : R.drawable.ic_error, 0);
            holder.itemView.setEnabled(false);
        }
    }

    public static class BaseQuestionViewHolder extends RecyclerView.ViewHolder {
        TextView question;

        public BaseQuestionViewHolder(ViewGroup parent, @LayoutRes int layoutId) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
            question = itemView.findViewById(R.id.text_view_question);
        }
    }
}
