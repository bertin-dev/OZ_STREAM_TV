package com.oz_stream.tv.ui.movie;

import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.SearchResultActor;


public class SearchActorPresenter extends Presenter {

    public SearchActorPresenter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new SearchActorCardView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ((SearchActorCardView) viewHolder.view).bind((SearchResultActor) item);
    }



    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
