package com.oz_stream.tv.ui.movie;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.oz_stream.tv.Config;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.SearchResult;
import com.oz_stream.tv.data.models.SearchResultActor;
import com.oz_stream.tv.ui.base.BindableCardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class SearchCardView extends BindableCardView<SearchResultActor> {

    @BindView(R.id.movie_img)
    ImageView movie_img;

    @BindView(R.id.diffuser_name)
    CircleImageView diffuser_name;

    @BindView(R.id.movie_title)
    TextView movie_title;

    @BindView(R.id.movie_year)
    TextView movie_year;

    @BindView(R.id.rating_bar)
    AppCompatRatingBar rating_bar;

    public SearchCardView(Context context) {
        super(context);
        ButterKnife.bind(this);
    }

    @Override
    protected void bind(SearchResultActor searchResultActor) {

        rating_bar.setRating(3);

        //photo de couverture films ou série
        Glide.with(getContext())
                .load(Config.GLOBAL_URL + searchResultActor.getAvatarLink())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(movie_img);

        //photo de couverture diffuseur
        if(searchResultActor.getCover() != null){
            Glide.with(getContext())
                    .load(Config.GLOBAL_URL + searchResultActor.getCover())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(diffuser_name);
        }else {
            Glide.with(getContext())
                    .load(R.drawable.placeholder_profile)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(diffuser_name);
        }

        //Nom de la vidéo
        movie_title.setText(searchResultActor.getFirstName());

        //année de sortie
        movie_year.setText("2021");
    }

    public ImageView getMovie_img() {
        return movie_img;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.card_movie;
    }


}