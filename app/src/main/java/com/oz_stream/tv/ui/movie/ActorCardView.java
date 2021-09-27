package com.oz_stream.tv.ui.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.ui.base.BindableCardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class ActorCardView extends BindableCardView<Actor> {

    @BindView(R.id.poster_iv)
    CircleImageView mPosterIV;

    @BindView(R.id.title_tv)
    TextView title_tv;

    public ActorCardView(Context context) {
        super(context);
        ButterKnife.bind(this);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void bind(Actor actor) {
        Glide.with(getContext())
                .load(actor.getAvatar())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mPosterIV);
        title_tv.setText(actor.getFirstName() + " " + actor.getLastName());

    }

    public CircleImageView getPosterIV() {
        return mPosterIV;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.card_actor;
    }
}