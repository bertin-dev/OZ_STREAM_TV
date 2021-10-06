package com.oz_stream.tv.ui.movie;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Episode;
import com.oz_stream.tv.ui.base.BindableCardView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SerieSaisonCardView extends BindableCardView<Episode> {

    @BindView(R.id.poster_iv)
    ImageView mPosterIV;

    @BindView(R.id.title_tv)
    TextView title_tv;

    public SerieSaisonCardView(Context context) {
        super(context);
        ButterKnife.bind(this);
    }

    @Override
    protected void bind(Episode episode) {

            /*Glide.with(getContext())
                    .load(episode.getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPosterIV);*/

            title_tv.setText(episode.getName());
    }

    public ImageView getPosterIV() {
        return mPosterIV;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.card_season;
    }
}