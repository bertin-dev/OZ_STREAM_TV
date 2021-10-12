package com.oz_stream.tv.ui.search;

import android.content.Intent;
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
import com.oz_stream.tv.Config;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.api.TheMovieDbAPI;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.RootFilter;
import com.oz_stream.tv.data.models.SearchResult;
import com.oz_stream.tv.data.models.SearchResultActor;
import com.oz_stream.tv.ui.detail.DetailActivity;
import com.oz_stream.tv.ui.detail.DetailDataActivity;
import com.oz_stream.tv.ui.detail.DetailDataFragment;
import com.oz_stream.tv.ui.detail.DetailFragment;
import com.oz_stream.tv.ui.movie.ActorCardView;
import com.oz_stream.tv.ui.movie.ActorPresenter;
import com.oz_stream.tv.ui.movie.MovieCardView;
import com.oz_stream.tv.ui.movie.SearchActorPresenter;
import com.oz_stream.tv.ui.movie.SearchPresenter;

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

    ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new SearchActorPresenter());

    ArrayObjectAdapter arrayObjectAdapterData = new ArrayObjectAdapter(new SearchPresenter());

    List<SearchResultActor> searchResultActorList;
    SearchResultActor searchResultActor;

    SearchResult searchResult;
    //RootFilter rootFilter;
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
                    .subscribe(this::bindActorSearch, e -> {

                        if(searchResultActor.getAvatarLink() != null){
                            loadImage(Config.GLOBAL_URL + searchResultActor.getAvatarLink());
                        }
                        bindActorDetails(searchResultActorList);
                        performSearch();
                        Timber.e(e, "Error fetching search actor response: %query", e.getMessage());
                    });
            performSearch();

            //data
            theMovieDbAPI.searchUserByName(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::bindDataSearch, e -> {

                        List<SearchResultActor> dataList = searchResultActor.getData();
                        for(SearchResultActor data : dataList){
                            if(data.getAvatarLink() != null){
                                loadImage(Config.GLOBAL_URL + data.getAvatarLink());
                            }
                            bindDataDetails(searchResult);
                        }
                        performDataSearch();
                        Timber.e(e, "Error fetching search poster response: %query", e.getMessage());
                    });

            performDataSearch();

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
                    .subscribe(this::bindActorSearch, e -> {

                        if(searchResultActor.getAvatarLink() != null){
                            loadImage(Config.GLOBAL_URL + searchResultActor.getAvatarLink());
                        }

                        bindActorDetails(searchResultActorList);
                        performSearch();
                        Timber.e(e, "Error fetching search actor response: %query", e.getMessage());
                    });

            performSearch();


            //data
            theMovieDbAPI.searchUserByName(query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::bindDataSearch, e -> {

                        List<SearchResultActor> dataList = searchResultActor.getData();
                        for(SearchResultActor data : dataList){
                            if(data.getAvatarLink() != null){
                                loadImage(Config.GLOBAL_URL + data.getAvatarLink());
                            }
                            bindDataDetails(searchResult);
                        }

                        performDataSearch();
                        Timber.e(e, "Error fetching search poster response: %query", e.getMessage());
                    });

            performDataSearch();

            return true;
        }
    }

    private void bindActorDetails(List<SearchResultActor> actorResponse) {

        for (SearchResultActor data1 : actorResponse) {
            this.searchResultActor = data1;
        }
        this.searchResultActorList = actorResponse;
    }

    private void bindDataDetails(SearchResult movieResponse) {

        this.searchResult = movieResponse;
        this.searchResultActor = searchResult.getSearchResultActor();
    }

    private void setupSearchRow() {
        mAdapter.add(new ListRow(new HeaderItem(0, "" + ""), arrayObjectAdapter));
        mAdapter.add(new ListRow(new HeaderItem(1, "" + ""), arrayObjectAdapterData));
        setOnItemViewClickedListener(this);

    }

    private void bindActorSearch(SearchResult responseObj) {
        SearchResultActor searchResultActor = responseObj.getSearchResultActor();
        arrayObjectAdapter.addAll(0, searchResultActor.getData());
    }

    private void bindDataSearch(SearchResult responseObj) {
        arrayObjectAdapterData.addAll(0, responseObj.getSearchResultActor().getData());
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

    private void performDataSearch() {

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
