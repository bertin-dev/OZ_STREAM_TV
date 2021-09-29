package com.oz_stream.tv.ui.search;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityOptionsCompat;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.oz_stream.tv.App;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.api.TheMovieDbAPI;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.Root;
import com.oz_stream.tv.ui.detail.DetailActivity;
import com.oz_stream.tv.ui.detail.DetailDataActivity;
import com.oz_stream.tv.ui.detail.DetailDataFragment;
import com.oz_stream.tv.ui.detail.DetailFragment;
import com.oz_stream.tv.ui.movie.ActorCardView;
import com.oz_stream.tv.ui.movie.ActorPresenter;
import com.oz_stream.tv.ui.movie.MovieCardView;
import com.oz_stream.tv.ui.movie.MoviePresenter;

import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class SearchFragment extends androidx.leanback.app.SearchFragment
        implements androidx.leanback.app.SearchFragment.SearchResultProvider, OnItemViewClickedListener {


    @Inject
    TheMovieDbAPI theMovieDbAPI;

    ArrayObjectAdapter mAdapter;

    DetailsOverviewRow detailsOverviewRow;

    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new ActorPresenter());

    ArrayObjectAdapter arrayObjectAdapterData = new ArrayObjectAdapter(new MoviePresenter());

    List<Actor> actorList;
    Actor actor;

    Root root;
    Data dataobj;
    private SimpleTarget<GlideDrawable> mGlideDrawableSimpleTarget = new SimpleTarget<GlideDrawable>() {
        @Override
        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
            detailsOverviewRow.setImageDrawable(resource);
        }
    };

    public static SearchFragment newInstance() {
        Bundle args = new Bundle();
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        view.setBackgroundResource(R.color.default_color);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.instance().appComponent().inject(this);

        mAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        setSearchResultProvider(this);

        setupSearchRow();
    }

    @Override
    public ObjectAdapter getResultsAdapter() {

        return mAdapter;
    }

    @Override
    public boolean onQueryTextChange(String query) {

        if (query.isEmpty()) {
            return false;
        } else {

            //actor
            theMovieDbAPI.getActorsList(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::bindSearch, e -> {
                        loadImage(actor.getAvatar());
                        bindActorDetails(actorList);
                        performSearch();
                        Timber.e(e, "Error fetching search actor response: %query", e.getMessage());
                    });
            performSearch();

            //data
            theMovieDbAPI.searchUserByName(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::bindPosterSearch, e -> {
                        loadImage(dataobj.getPhoto().getLink());
                        bindPosterDetails(root);
                        performPosterSearch();
                        Timber.e(e, "Error fetching search poster response: %query", e.getMessage());
                    });

            performPosterSearch();

            return true;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        if (query.isEmpty()) {
            return false;
        } else {
            //actor
            theMovieDbAPI.getActorsList(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::bindSearch, e -> {
                        loadImage(actor.getAvatar());
                        bindActorDetails(actorList);
                        performSearch();
                        Timber.e(e, "Error fetching search actor response: %query", e.getMessage());
                    });

            performSearch();


            //data
            theMovieDbAPI.searchUserByName(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::bindPosterSearch, e -> {
                        loadImage(dataobj.getPhoto().getLink());
                        bindPosterDetails(root);
                        performPosterSearch();
                        Timber.e(e, "Error fetching search poster response: %query", e.getMessage());
                    });

            performPosterSearch();

            return true;
        }
    }

    private void bindActorDetails(List<Actor> actorResponse) {

        for (Actor actor1 : actorResponse) {
            this.actor = actor1;
        }
        this.actorList = actorResponse;
    }

    private void bindPosterDetails(Root movieResponse) {

        this.root = movieResponse;
        List<Data> dataList = movieResponse.getNews().getDatas();
        //films les mieux notés (Poster)
        for (Data data1 : dataList) {
            this.dataobj = data1;
        }
    }

    private void setupSearchRow() {
        mAdapter.add(new ListRow(new HeaderItem(0, "" + ""), arrayObjectAdapter));
        mAdapter.add(new ListRow(new HeaderItem(1, "" + ""), arrayObjectAdapterData));
        setOnItemViewClickedListener(this);

    }

    private void bindSearch(List<Actor> responseObj) {
        arrayObjectAdapter.addAll(0, responseObj);
    }

    private void bindPosterSearch(Root responseObj) {
        arrayObjectAdapterData.addAll(0, responseObj.getNews().getDatas());
    }

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
                        return false;
                    }
                })
                .into(mGlideDrawableSimpleTarget);
    }


    private void performSearch() {

        arrayObjectAdapter.clear();
    }

    private void performPosterSearch() {

        arrayObjectAdapterData.clear();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        setSearchQuery(data, true);
    }


    @Override
    public void onItemClicked(Presenter.ViewHolder viewHolder, Object item, RowPresenter.ViewHolder itemViewHolder, Row row) {

        if (item instanceof Actor) {
            Actor actor = (Actor) item;
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(Actor.class.getSimpleName(), actor);

            if (itemViewHolder.view instanceof ActorCardView) {
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        ((ActorCardView) itemViewHolder.view).getPosterIV(),
                        DetailFragment.TRANSITION_NAME).toBundle();
                getActivity().startActivity(intent, bundle);
            } else {
                startActivity(intent);
            }
        }

        if (item instanceof Data) {
            Data poster = (Data) item;
            Intent intent = new Intent(getActivity(), DetailDataActivity.class);
            // Pass the langue to the activity
            intent.putExtra(Data.class.getSimpleName(), poster);

            if (itemViewHolder.view instanceof MovieCardView) {
                // Pass the ImageView to allow a nice transition
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        getActivity(),
                        ((MovieCardView) itemViewHolder.view).getMovie_img(),
                        DetailDataFragment.TRANSITION_NAME).toBundle();
                getActivity().startActivity(intent, bundle);
            } else {
                startActivity(intent);
            }
        }

    }

}
