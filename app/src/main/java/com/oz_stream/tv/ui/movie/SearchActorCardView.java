package com.oz_stream.tv.ui.movie;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oz_stream.tv.Config;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.SearchResultActor;
import com.oz_stream.tv.ui.base.BindableCardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class SearchActorCardView extends BindableCardView<SearchResultActor> {

    @BindView(R.id.poster_iv)
    CircleImageView mPosterIV;

    @BindView(R.id.title_tv)
    TextView title_tv;

    public SearchActorCardView(Context context) {
        super(context);
        ButterKnife.bind(this);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void bind(SearchResultActor searchResultActor) {

        if(searchResultActor.getAvatarLink() != null){
            Glide.with(getContext())
                    .load(Config.GLOBAL_URL + searchResultActor.getAvatarLink())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPosterIV);
        }else {
            Glide.with(getContext())
                    .load(R.drawable.placeholder_profile)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPosterIV);
        }

        title_tv.setText(searchResultActor.getFirstName() + " " + searchResultActor.getLastName());

    }

    public CircleImageView getPosterIV() {
        return mPosterIV;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.card_actor;
    }
}