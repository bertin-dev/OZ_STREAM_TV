package com.oz_stream.tv.ui.detail;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.ui.base.BindableCardView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class filmographyPresenter extends Presenter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new FilmographyCardView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ((FilmographyCardView) viewHolder.view).bind((Data) item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }


    public static class FilmographyCardView extends BindableCardView<Data> {

        @BindView(R.id.poster_iv)
        ImageView mPosterIV;

        public FilmographyCardView(Context context) {
            super(context);
            ButterKnife.bind(this);
        }

        @Override
        protected void bind(Data data) {
            Glide.with(getContext())
                    .load(data.getPhoto().getLink())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPosterIV);

        }

        public ImageView getPosterIV() {
            return mPosterIV;
        }

        @Override
        protected int getLayoutResource() {
            return R.layout.card_filmography;
        }
    }

}
