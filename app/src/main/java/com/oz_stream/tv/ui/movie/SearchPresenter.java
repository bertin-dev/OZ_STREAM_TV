package com.oz_stream.tv.ui.movie;

import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.SearchResult;
import com.oz_stream.tv.data.models.SearchResultActor;


public class SearchPresenter extends Presenter {

    public SearchPresenter() {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new SearchCardView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ((SearchCardView) viewHolder.view).bind((SearchResultActor) item);
    }



    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
