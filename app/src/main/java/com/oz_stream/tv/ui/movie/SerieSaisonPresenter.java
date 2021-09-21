package com.oz_stream.tv.ui.movie;

import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.oz_stream.tv.data.models.Episode;


public class SerieSaisonPresenter extends Presenter {

    public SerieSaisonPresenter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new SerieSaisonCardView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ((SerieSaisonCardView) viewHolder.view).bind((Episode) item);
    }



    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
