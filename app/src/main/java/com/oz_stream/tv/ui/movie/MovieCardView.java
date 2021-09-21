package com.oz_stream.tv.ui.movie;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oz_stream.tv.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieCardView extends BindableCardView<Poster> {

    @BindView(R.id.poster_iv)
    ImageView mPosterIV;

    @BindView(R.id.title_tv)
    TextView title_tv;

    public MovieCardView(Context context) {
        super(context);
        ButterKnife.bind(this);
    }

    @Override
    protected void bind(Poster poster) {
        Glide.with(getContext())
                .load(poster.getImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mPosterIV);
        title_tv.setText(poster.getTitle());

    }

    public ImageView getPosterIV() {
        return mPosterIV;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.card_movie;
    }


}