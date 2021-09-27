package com.oz_stream.tv.ui.detail;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.ui.base.BindableCardView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonPresenter_casting extends Presenter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(new CastingActorMovieCardView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        ((CastingActorMovieCardView) viewHolder.view).bind((Actor) item);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }


    public static class CastingActorMovieCardView extends BindableCardView<Actor> {

        @BindView(R.id.poster_iv)
        ImageView mPosterIV;

        @BindView(R.id.title_tv)
        TextView title_tv;

        @BindView(R.id.sub_title)
        TextView sub_title;

        public CastingActorMovieCardView(Context context) {
            super(context);
            ButterKnife.bind(this);
        }

        @Override
        protected void bind(Actor actor) {
            Glide.with(getContext())
                    .load(actor.getAvatar())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mPosterIV);

            title_tv.setText(actor.getLastName());
            sub_title.setText(actor.getFirstName());
        }


        @Override
        protected int getLayoutResource() {
            return R.layout.card_casting;
        }
    }

}
