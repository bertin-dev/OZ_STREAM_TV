package com.oz_stream.tv.ui.detail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityOptionsCompat;
import androidx.leanback.app.DetailsFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DetailsOverviewLogoPresenter;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.FullWidthDetailsOverviewSharedElementHelper;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.oz_stream.tv.App;
import com.oz_stream.tv.Config;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.api.TheMovieDbAPI;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.ActorDetails;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.Media;
import com.oz_stream.tv.data.models.PaletteColors;
import com.oz_stream.tv.ui.base.GlideBackgroundManager;
import com.oz_stream.tv.ui.base.PaletteUtils;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class DetailFragment extends DetailsFragment implements OnItemViewClickedListener, Palette.PaletteAsyncListener {

    public static String TRANSITION_NAME = "data_transition";
    private static final String TAG = "DetailFragment";
    @Inject
    TheMovieDbAPI theMovieDbAPI;

    Actor actor;
    ArrayObjectAdapter mAdapter;
    CustomDetailPresenter customDetailPresenter;
    DetailsOverviewRow detailsOverviewRow;
    ArrayObjectAdapter mFilmographyAdapter = new ArrayObjectAdapter(new filmographyPresenter());
    //GlideBackgroundManager glideBackgroundManager;

    public static DetailFragment newInstance(Actor actor) {
        Bundle args = new Bundle();
        args.putParcelable(Actor.class.getSimpleName(), actor);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.instance().appComponent().inject(this);
        if (getArguments() == null || !getArguments().containsKey(Actor.class.getSimpleName())) {
            throw new RuntimeException("A actor is necessary for DetailFragment");
        }

        actor = getArguments().getParcelable(Actor.class.getSimpleName());
        /*glideBackgroundManager = new GlideBackgroundManager(getActivity());
        glideBackgroundManager.setBackgroundColors(Color.parseColor("#FF263238"));*/
        setUpAdapter();
        setUpDetailsOverviewRow();
        setupFilmographyRow();
        setupEventListeners1();
    }

    private void setupEventListeners1() {
        setOnItemViewClickedListener(this);
    }

    private void setUpAdapter() {

        customDetailPresenter = new CustomDetailPresenter(new DetailDescriptionPresenter(),
                new DetailsOverviewLogoPresenter());
        Log.w(TAG, "setUpAdapter: " + customDetailPresenter);

        FullWidthDetailsOverviewSharedElementHelper helper = new FullWidthDetailsOverviewSharedElementHelper();
        helper.setSharedElementEnterTransition(getActivity(), TRANSITION_NAME);
        customDetailPresenter.setListener(helper);
        customDetailPresenter.setParticipatingEntranceTransition(false);
        ClassPresenterSelector classPresenterSelector = new ClassPresenterSelector();
        classPresenterSelector.addClassPresenter(DetailsOverviewRow.class, customDetailPresenter);
        classPresenterSelector.addClassPresenter(ListRow.class, new ListRowPresenter());
        mAdapter = new ArrayObjectAdapter(classPresenterSelector);
        setAdapter(mAdapter);
    }


    private void setUpDetailsOverviewRow() {

        if(actor != null){
            detailsOverviewRow = new DetailsOverviewRow(new ActorDetails());
            mAdapter.add(detailsOverviewRow);

            loadImage(Config.GLOBAL_URL + actor.getAvatar());
            detailsOverviewRow.setItem(this.actor);
        }
    }


    private void setupFilmographyRow() {
        mAdapter.add(new ListRow(new HeaderItem(2, getString(R.string.filmographie)), mFilmographyAdapter));
        Filmography();
    }

    private void Filmography() {
        bindFilmography(actor.getMedias());
    }


    private void bindFilmography(List<Media> mediaList) {
        mFilmographyAdapter.addAll(0, mediaList);
    }

    private SimpleTarget<GlideDrawable> mGlideDrawableSimpleTarget = new SimpleTarget<GlideDrawable>() {
        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
            detailsOverviewRow.setImageDrawable(resource);
        }
    };


    private void loadImage(String url) {
        Glide.with(getActivity())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        changePalette(((GlideBitmapDrawable) resource).getBitmap());
                        return false;
                    }
                })
                .into(mGlideDrawableSimpleTarget);
    }


    private void changePalette(Bitmap bmp) {
        Palette.from(bmp).generate(this);
    }

    @Override
    public void onGenerated(Palette palette) {
        PaletteColors colors = PaletteUtils.getPaletteColors(palette);
        customDetailPresenter.setActionsBackgroundColor(colors.getStatusBarColor());
        customDetailPresenter.setBackgroundColor(colors.getToolbarBackgroundColor());

        /*customDetailPresenter.setActionsBackgroundColor(Color.parseColor("#FF263238"));
        customDetailPresenter.setBackgroundColor(Color.parseColor("#FF263238"));*/
        if (actor != null) {
            this.actor.setPaletteColors(colors);
        }
        notifyDetailsChanged();
    }

    private void notifyDetailsChanged() {
        detailsOverviewRow.setItem(this.actor);
        int index = mAdapter.indexOf(detailsOverviewRow);
        mAdapter.notifyArrayItemRangeChanged(index, 1);
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {

        if( item instanceof Data){
            Data data = (Data) item;
            Intent intent = new Intent(getActivity(), DetailDataActivity.class);
            // Pass the langue to the activity
            intent.putExtra(Data.class.getSimpleName(), data);

            if (itemViewHolder.view instanceof filmographyPresenter.FilmographyCardView) {
                // Pass the ImageView to allow a nice transition
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        ((filmographyPresenter.FilmographyCardView) itemViewHolder.view).getPosterIV(),
                        DetailDataFragment.TRANSITION_NAME).toBundle();
                getActivity().startActivity(intent, bundle);
            } else {
                startActivity(intent);
            }
        }
    }
}
