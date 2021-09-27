package com.oz_stream.tv.ui.detail;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.leanback.widget.Presenter;

import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.Gender;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailViewHolder extends Presenter.ViewHolder {

    private static final String TAG = "DetailViewHolder";

    @BindView(R.id.movie_title)
    TextView movieTitleTV;

    @BindView(R.id.movie_year)
    TextView movieYearTV;

    @BindView(R.id.overview)
    TextView movieOverview;

    @BindView(R.id.runtime)
    TextView mRuntimeTV;

    @BindView(R.id.tagline)
    TextView mTaglineTV;

    @BindView(R.id.director_tv)
    TextView mDirectorTv;

    @BindView(R.id.overview_label)
    TextView mOverviewLabelTV;

    @BindView(R.id.genres)
    LinearLayout mGenresLayout;

    @BindView(R.id.rating_bar)
    AppCompatRatingBar rating_bar;

    @BindView(R.id.rating)
    TextView rating;

    @BindView(R.id.imdb)
    TextView imdb;

    @BindView(R.id.classification)
    LinearLayout classification;


    private View itemView;

    public DetailViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        itemView = view;
    }

    @SuppressLint("SetTextI18n")
    public void bindActor(Actor actor) {
        if (actor != null && actor.getLastName() != null) {
            mOverviewLabelTV.setText(R.string.biographie);
            mRuntimeTV.setText(actor.getBirthDate());
            movieTitleTV.setText(actor.getFirstName() + " " + actor.getLastName());
            movieYearTV.setText(actor.getHeight());
            movieOverview.setText(actor.getBibliographie());
            mGenresLayout.removeAllViews();

            if (actor.getPaletteColors() != null) {
                movieTitleTV.setTextColor(actor.getPaletteColors().getTitleColor());
                mRuntimeTV.setTextColor(actor.getPaletteColors().getTextColor());
                movieYearTV.setTextColor(actor.getPaletteColors().getTextColor());
                movieOverview.setTextColor(actor.getPaletteColors().getTextColor());
            }


        }

    }


    @SuppressLint("SetTextI18n")
    public void bindDate(Data data){

        if (data != null && data.getTitle() != null) {
            classification.setVisibility(View.VISIBLE);
            mRuntimeTV.setText(data.getDuration());
            mTaglineTV.setText(data.getClassification());
            movieTitleTV.setText(data.getTitle().toUpperCase());
            movieYearTV.setText(data.getYear());
            movieOverview.setText(data.getDescription());
            mGenresLayout.removeAllViews();

            if (data.getDirector() != null) {
                mDirectorTv.setText(String.format(Locale.getDefault(), "Directeur: %s", data.getDirector()));
            }

            rating_bar.setRating(data.getRating());
            rating.setText(data.getRating() + "/5");
            imdb.setText(data.getImdb() + "/10");

            int _16dp = (int) itemView.getResources().getDimension(R.dimen.full_padding);
            int _8dp = (int) itemView.getResources().getDimension(R.dimen.half_padding);
            float corner = itemView.getResources().getDimension(R.dimen.genre_corner);

            if (data.getPaletteColors() != null) {
                Log.w(TAG, "bindPoster---------BONJOUR: " );
                movieTitleTV.setTextColor(data.getPaletteColors().getTitleColor());
                mOverviewLabelTV.setTextColor(data.getPaletteColors().getTitleColor());
                mTaglineTV.setTextColor(data.getPaletteColors().getTextColor());
                mRuntimeTV.setTextColor(data.getPaletteColors().getTextColor());
                movieYearTV.setTextColor(data.getPaletteColors().getTextColor());
                movieOverview.setTextColor(data.getPaletteColors().getTextColor());
                mDirectorTv.setTextColor(data.getPaletteColors().getTextColor());
                rating.setTextColor(data.getPaletteColors().getTextColor());
                imdb.setTextColor(data.getPaletteColors().getTextColor());
                int primaryDarkColor = data.getPaletteColors().getStatusBarColor();
                // Adds each genre to the genre layout
                for (Gender genre : data.getGenders()) {
                    TextView textView = new TextView(itemView.getContext());
                    Log.w(TAG, "bindData---------1: " + genre.getTitle() );
                    textView.setText(genre.getTitle());
                    //GradientDrawable shape = new GradientDrawable();
                    //shape.setShape(GradientDrawable.RECTANGLE);
                    //shape.setCornerRadius(corner);
                    //shape.setColor(primaryDarkColor);
                    //textView.setPadding(_8dp, _8dp, _8dp, _8dp);
                    //textView.setBackground(shape);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    params.setMargins(0, 0, _16dp, 0);
                    textView.setLayoutParams(params);

                    mGenresLayout.addView(textView);
                }

            }


        }

    }
}
