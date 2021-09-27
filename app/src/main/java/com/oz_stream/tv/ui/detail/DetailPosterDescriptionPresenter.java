package com.oz_stream.tv.ui.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.leanback.widget.Presenter;

import com.oz_stream.tv.R;


public class DetailPosterDescriptionPresenter extends Presenter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detail, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        /*Poster poster = (Poster) item;
        if(poster != null){
            DetailViewHolder holder = (DetailViewHolder) viewHolder;
            holder.bindPoster(poster);
        }*/
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
