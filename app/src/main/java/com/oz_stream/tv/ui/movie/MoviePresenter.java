package com.oz_stream.tv.ui.movie;

import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;


public class MoviePresenter extends Presenter {

    public MoviePresenter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new MovieCardView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ((MovieCardView) viewHolder.view).bind((Poster) item);
    }



    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
