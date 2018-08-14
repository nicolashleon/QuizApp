package com.udacity.quizapp.ui.delegates;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.udacity.quizapp.ui.models.DelegateUIModel;


public interface DelegateAdapter<VH extends RecyclerView.ViewHolder, UIModel extends DelegateUIModel> {

    VH onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(VH viewHolder, UIModel uiModel);

}
