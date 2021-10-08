package com.oz_stream.tv.ui.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.leanback.app.BrowseFragment;
import androidx.leanback.app.RowsFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.FocusHighlight;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.PageRow;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;

import com.google.gson.Gson;
import com.oz_stream.tv.App;
import com.oz_stream.tv.BuildConfig;
import com.oz_stream.tv.Config;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.api.TheMovieDbAPI;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.Frees;
import com.oz_stream.tv.data.models.Gender;
import com.oz_stream.tv.data.models.News;
import com.oz_stream.tv.data.models.Populars;
import com.oz_stream.tv.data.models.Previews;
import com.oz_stream.tv.data.models.Root;
import com.oz_stream.tv.data.models.RootFilter;
import com.oz_stream.tv.provider.PrefManager;
import com.oz_stream.tv.ui.base.GlideBackgroundManager;
import com.oz_stream.tv.ui.base.IconHeaderItemPresenter;
import com.oz_stream.tv.ui.detail.DetailDataActivity;
import com.oz_stream.tv.ui.detail.DetailDataFragment;
import com.oz_stream.tv.ui.movie.ActorPresenter;
import com.oz_stream.tv.ui.movie.MovieCardView;
import com.oz_stream.tv.ui.movie.MoviePresenter;
import com.oz_stream.tv.ui.movie.PopularMoviePresenter;
import com.oz_stream.tv.ui.player.caster.DataListLoader;
import com.oz_stream.tv.ui.publish_channel.ChannelPublishActivity;
import com.oz_stream.tv.ui.search.SearchActivity;
import com.oz_stream.tv.ui.setting.Card;
import com.oz_stream.tv.ui.setting.CardRow;
import com.oz_stream.tv.ui.setting.SettingsIconPresenter;
import com.oz_stream.tv.ui.setting.utils.CardListRowSetting;
import com.oz_stream.tv.ui.setting.utils.Utils;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;


/**
 * Sample {@link BrowseFragment} implementation showcasing the use of {@link PageRow} and
 * {@link ListRow}.
 */
public class MainFragment extends BrowseFragment implements LoaderManager.LoaderCallbacks<List<Data>>{

    @Inject
    TheMovieDbAPI theMovieDbAPI;

    private static final long HEADER_ID_1 = 1;
    //private static final String HEADER_NAME_1 = "Accueil";
    private static final long HEADER_ID_2 = 2;
    //private static final String HEADER_NAME_2 = "Films";
    private static final long HEADER_ID_3 = 3;
    //private static final String HEADER_NAME_3 = "Séries";

    private static final long HEADER_ID_4 = 4;
    //private static final String HEADER_NAME_4 = "Live";

    private static final long HEADER_ID_5 = 5;
    //private static final String HEADER_NAME_7 = "Contactez nous";

    private static final long HEADER_ID_6 = 6;
    //private static final String HEADER_NAME_6 = "Paramètres";

    private static final long HEADER_ID_7 = 7;
    //private static final String HEADER_NAME_7 = "Deconnexion";


    public GlideBackgroundManager glideBackgroundManager;
    private ArrayObjectAdapter mRowsAdapter;
    private static final String TAG = "MainFragment";

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.instance().appComponent().inject(this);

        setupUIElements();
        prepareEntranceTransition();
        loadData();
        setupEventListeners();
        getLoaderManager().initLoader(0, null, this);
        glideBackgroundManager.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.placeholder));
        //glideBackgroundManager.setBackgroundColors(Color.parseColor("#FF263238"));
        //getMainFragmentRegistry().registerFragment(PageRow.class, new PageRowFragmentFactory());
        getMainFragmentRegistry().registerFragment(PageRow.class, new PageRowFragmentFactory(glideBackgroundManager));
    }

    private void setupUIElements() {
        glideBackgroundManager = new GlideBackgroundManager(getActivity());
        setBadgeDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.icon));
        // Badge, when set, takes precedent over title
        setTitle(getString(R.string.accueil));
        //set headers background color
        setBrandColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        //search icon background color
        setSearchAffordanceColor(getResources().getColor(R.color.default_color));

        setHeaderPresenterSelector(new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object o) {
                return new IconHeaderItemPresenter();
            }
        });
    }

    private void setupEventListeners() {
        // search icon clicked
        setOnSearchClickedListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });
    }


    private void loadData() {
        mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setAdapter(mRowsAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createOwnerRows();
                startEntranceTransition();
            }
        }, 2000);
    }


    private void createOwnerRows() {
        HeaderItem headerItem1 = new HeaderItem(HEADER_ID_1, getString(R.string.accueil));
        PageRow pageRow1 = new PageRow(headerItem1);
        mRowsAdapter.add(pageRow1);

        HeaderItem headerItem2 = new HeaderItem(HEADER_ID_2, getString(R.string.films));
        PageRow pageRow2 = new PageRow(headerItem2);
        mRowsAdapter.add(pageRow2);

        HeaderItem headerItem3 = new HeaderItem(HEADER_ID_3, getString(R.string.serie));
        PageRow pageRow3 = new PageRow(headerItem3);
        mRowsAdapter.add(pageRow3);

        HeaderItem headerItem4 = new HeaderItem(HEADER_ID_4, getString(R.string.live));
        PageRow pageRow4 = new PageRow(headerItem4);
        mRowsAdapter.add(pageRow4);

        HeaderItem headerItem5 = new HeaderItem(HEADER_ID_5, getString(R.string.contactNous));
        PageRow pageRow5 = new PageRow(headerItem5);
        mRowsAdapter.add(pageRow5);

        HeaderItem headerItem6 = new HeaderItem(HEADER_ID_6, getString(R.string.parametres));
        PageRow pageRow6 = new PageRow(headerItem6);
        mRowsAdapter.add(pageRow6);

        HeaderItem headerItem7 = new HeaderItem(HEADER_ID_7, getString(R.string.deconnexion));
        PageRow pageRow7 = new PageRow(headerItem7);
        mRowsAdapter.add(pageRow7);

    }


    @Override
    public Loader<List<Data>> onCreateLoader(int id, Bundle args) {
        return new DataListLoader(getActivity(), getString(R.string.url_principal));
    }

    @Override
    public void onLoadFinished(Loader<List<Data>> loader, List<Data> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Data>> loader) {

    }


    public static class PageRowFragmentFactory extends FragmentFactory {
        private final GlideBackgroundManager mBackgroundManager;

        PageRowFragmentFactory(GlideBackgroundManager backgroundManager) {
            this.mBackgroundManager = backgroundManager;
        }

        @Override
        public Fragment createFragment(Object rowObj) {
            Row row = (Row)rowObj;

            //mBackgroundManager.setDrawable(null);
            if (row.getHeaderItem().getId() == HEADER_ID_1) {
                return new Accueil(mBackgroundManager);
            } else if (row.getHeaderItem().getId() == HEADER_ID_2) {
                return new Films(mBackgroundManager);
            } else if (row.getHeaderItem().getId() == HEADER_ID_3) {
                return new Series(mBackgroundManager);
            } else if (row.getHeaderItem().getId() == HEADER_ID_4) {
                return new Live();
            } else if (row.getHeaderItem().getId() == HEADER_ID_5) {
                return new ContactUsWebView();
            }else if (row.getHeaderItem().getId() == HEADER_ID_6) {
                return new setting();
            }else if (row.getHeaderItem().getId() == HEADER_ID_7) {
                return new Logout();
            }

            throw new IllegalArgumentException(String.format("Invalid row %s", rowObj));
        }
    }

    /**
     * Page fragment embeds a rows fragment.
     */
    public static class Accueil extends RowsFragment implements OnItemViewSelectedListener, OnItemViewClickedListener {

        @Inject
        TheMovieDbAPI theMovieDbAPI;
        private static final String TAG = "Accueil";

        // rows - 0 - Avant premiere
        private static final int PREVIEWS = 0;

        // rows - 1 - Nouveauté
        private static final int NEWS = 1;

        // rows - 2 - populaire
        private static final int POPULAR = 2;

        // rows - 3 - videos gratuits
        private static final int FREES = 3;

        // rows - 4 - Liste des genres
        private static final int GENDERS = 4;


        SparseArray<MovieRow> movieRowSparseArray;

        ArrayObjectAdapter rowsAdapter;
        GlideBackgroundManager glideBackgroundManager;

        public Accueil(GlideBackgroundManager glideBackgroundManager) {
            this.glideBackgroundManager = glideBackgroundManager;
        }

        public Accueil() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
            App.instance().appComponent().inject(this);
            createDataRows();
            createRows();
            fetchNewsMovies();
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            fetchPreviewMovies();
            fetchPopularMovies();
            fetchFreesMovies();
            //Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            //Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
            //prepareEntranceTransition();

            //fetchGendersMovies();
            setupEventListeners1();
        }



        @Override
        public void onResume() {
            super.onResume();
            //Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
        }

        private void setupEventListeners1() {
            setOnItemViewSelectedListener(this);
            setOnItemViewClickedListener(this);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
        }

        // Creates the data rows objects
        private void createDataRows() {

            movieRowSparseArray = new SparseArray<>();

            //The CardPresenter defines the UI of the items in the row
            ActorPresenter actorPresenter = new ActorPresenter();
            MoviePresenter moviePresenter = new MoviePresenter();
            PopularMoviePresenter popularMoviePresenter = new PopularMoviePresenter();

            //row - 0 - create objects
            movieRowSparseArray.put(PREVIEWS, new MovieRow()
                    .setId(PREVIEWS)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.avant_premiere))
                    .setPage(1)
            );

            //row - 1 - create objects
            movieRowSparseArray.put(NEWS, new MovieRow()
                    .setId(NEWS)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.news))
                    .setPage(1)
            );

            //row - 2 - create objects
            movieRowSparseArray.put(POPULAR, new MovieRow()
                    .setId(POPULAR)
                    .setAdapter(new ArrayObjectAdapter(popularMoviePresenter))
                    .setTitle(getString(R.string.plusPopulaire))
                    .setPage(1)
            );

            //row - 3 - create objects
            movieRowSparseArray.put(FREES, new MovieRow()
                    .setId(FREES)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.movie_free))
                    .setPage(1)
            );


            //row - 4 - create objects
            movieRowSparseArray.put(GENDERS, new MovieRow()
                    .setId(GENDERS)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.movie_gender))
                    .setPage(1)
            );

        }

        // Creates the rows and sets up the adapter of the fragment
        private void createRows() {
            // Creates the RowsAdapter for the Fragment
            // The ListRowPresenter tells to render ListRow objects
            rowsAdapter = new ArrayObjectAdapter(new ShadowRowPresenterSelector());
            setAdapter(rowsAdapter);
        }


        //Avant première
        private void fetchPreviewMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindPreviewResponse(response, PREVIEWS);
                        //startEntranceTransition();
                    }, e -> {
                        Timber.e(e, "Error fetching preview movies: %s", e.getMessage());
                    });
        }

        // Vidéos populaires
        private void fetchPopularMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindPopularResponse(response, POPULAR);
                        //startEntranceTransition();
                    }, e -> {
                        Timber.e(e, "Error fetching popular movies: %s", e.getMessage());
                    });
        }

        //(NOUVEAUTE)
        private void fetchNewsMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindNewsResponse(response, NEWS);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching News movies: %s", e.getMessage()));
        }


        private void fetchFreesMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindFreesMoviesResponse(response, FREES);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching upcoming movies: %s", e.getMessage()));
        }


        private void fetchGendersMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindGendersResponse(response, GENDERS);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching drame movies: %s", e.getMessage()));
        }

        //Avant Première
        private void bindPreviewResponse(Root response, int id) {
            //Log.w("POPULAR", "bindPreviewResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());

            Previews previews = response.getPreviews();
            List<Data> dataList = previews.getDatas();

            for(Data data : dataList){
                movieRow.getAdapter().add(data);
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }


        //Populaires
        private void bindPopularResponse(Root response, int id) {
            //Log.w("POPULAR", "bindPopularResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());

            Populars populars = response.getPopulars();
            List<Data> dataList = populars.getDatas();

            for(Data data : dataList){
                movieRow.getAdapter().add(data);
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }


        //(NOUVEAUTE)
        private void bindNewsResponse(Root response, int id) {
            //Log.w("TOP RATED", "bindNewsResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());

            News news = response.getNews();
            List<Data> dataList = news.getDatas();

            for(Data data : dataList){
                movieRow.getAdapter().add(data);
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }

        //vidéos gratuite
        private void bindFreesMoviesResponse(Root response, int id) {
            //Log.w("UP COMING", "bindFreesMoviesResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());

            Frees frees = response.getFrees();
            List<Data> dataList = frees.getDatas();

            for(Data data : dataList){
                movieRow.getAdapter().add(data);
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }


        private void bindGendersResponse(Root response, int id) {
            //Log.w("VIDEO_BANDE_ANNONCE", "bindGendersResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());

            List<Gender> genderList = response.getGenders();
            /*List<Data> dataList = willbePostes.getDatas();
               for(Data data : dataList){
                movieRow.getAdapter().add(data);
            }*/
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }

        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            // Check if the item is a Data
            if (item instanceof Data) {
                Data data = (Data) item;
                // Check if the langue has a backdrop
                if(data.getPhoto().getLink() != null) {
                    glideBackgroundManager.loadImage(Config.GLOBAL_URL + data.getPhoto().getLink());
                } else {
                    // If there is no backdrop for the langue we just use a default one
                    glideBackgroundManager.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.place_holder_channel));
                }
            }
        }

        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if( item instanceof Data){
                Data data = (Data) item;
                Intent intent = new Intent(getActivity(), DetailDataActivity.class);
                // Pass the langue to the activity
                intent.putExtra(Data.class.getSimpleName(), data);

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



    /**
     * Simple page Films implementation.
     */
    public static class Films extends GridFragment implements OnItemViewSelectedListener, OnItemViewClickedListener {

        @Inject
        TheMovieDbAPI theMovieDbAPI;

        private static final int COLUMNS = 6;
        private final int ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_SMALL;
        private ArrayObjectAdapter mAdapter;
        private SparseArray<MovieRow> movieRowSparseArrayFilms;
        // rows - 0 - MOVIE
        private static final int MOVIE = 0;
        private GlideBackgroundManager glideBackgroundManager;
        private static String TAG= "Films";

        public Films() {
        }

        public Films(GlideBackgroundManager glideBackgroundManager) {
            this.glideBackgroundManager = glideBackgroundManager;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            App.instance().appComponent().inject(this);

            setupAdapter();
            loadData("film");

            setupEventListeners2();
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
        }

        private void setupEventListeners2() {
            setOnItemViewSelectedListener(this);
            setOnItemViewClickedListener(this);
        }

        // Creates the data rows objects
        private void createDataRows() {


            movieRowSparseArrayFilms = new SparseArray<>();

            //The CardPresenter defines the UI of the items in the row
            MoviePresenter moviePresenter = new MoviePresenter();

            //row - 1 - create objects
            movieRowSparseArrayFilms.put(MOVIE, new MovieRow()
                    .setId(MOVIE)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.films))
                    .setPage(1)
            );
        }


        private void setupAdapter() {
            VerticalGridPresenter presenter = new VerticalGridPresenter(ZOOM_FACTOR);
            presenter.setNumberOfColumns(COLUMNS);
            setGridPresenter(presenter);

            createDataRows();

            MovieRow movieRow = movieRowSparseArrayFilms.get(MOVIE);

            mAdapter = movieRow.getAdapter();
            setAdapter(mAdapter);
        }

        private void loadData(String category) {

            theMovieDbAPI.getFilterByCategory(category)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindMovieResponse(response, MOVIE);
                        //startEntranceTransition();
                    }, e -> {
                        Timber.e(e, "Error fetching now playing movies: %s", e.getMessage());
                    });
        }


        private void bindMovieResponse(RootFilter response, int id) {
            Log.w(TAG, "bindMovieResponse: "+ response );

            List<Data> dataList = response.getData();

            if(dataList.size()>0){
                MovieRow movieRow = movieRowSparseArrayFilms.get(id);
                movieRow.setPage(movieRow.getPage() + 1);

                for(Data data : dataList){
                    if(data.getPhoto().getLink() != null){
                        movieRow.getAdapter().add(data);
                    }
                }
            }
        }

        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            // Check if the item is a langue
            /*if (item instanceof Actor) {
                Actor actor = (Actor) item;
                // Check if the langue has a backdrop
                if(actor.getImage() != null) {
                    glideBackgroundManager.loadImage(actor.getImage());
                } else {
                    // If there is no backdrop for the langue we just use a default one
                    glideBackgroundManager.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.place_holder_channel));
                }
            }*/


            // Check if the item is a langue
            if (item instanceof Data) {
                Data data = (Data) item;
                // Check if the langue has a backdrop
                if(data.getPhoto().getLink() != null) {
                    glideBackgroundManager.loadImage(Config.GLOBAL_URL + data.getPhoto().getLink());
                } else {
                    // If there is no backdrop for the langue we just use a default one
                    glideBackgroundManager.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.place_holder_channel));
                }
            }
        }

        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            /*if (item instanceof Actor) {
                Actor actor = (Actor) item;
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                // Pass the langue to the activity
                intent.putExtra(Actor.class.getSimpleName(), actor);

                if (itemViewHolder.view instanceof ActorCardView) {
                    // Pass the ImageView to allow a nice transition
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(),
                            ((ActorCardView) itemViewHolder.view).getPosterIV(),
                            DetailFragment.TRANSITION_NAME).toBundle();
                    getActivity().startActivity(intent, bundle);
                } else {
                    startActivity(intent);
                }
            }*/
            if( item instanceof Data){
                Data data = (Data) item;
                Intent intent = new Intent(getActivity(), DetailDataActivity.class);
                // Pass the langue to the activity
                intent.putExtra(Data.class.getSimpleName(), data);

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

        @Override
        public void onResume() {
            super.onResume();
            //loadData("film");
        }


    }



    /**
     * Simple page Serie implementation.
     */
    public static class Series extends GridFragment implements OnItemViewSelectedListener, OnItemViewClickedListener {

        @Inject
        TheMovieDbAPI theMovieDbAPI;

        private static final String TAG = "Series";
        private static final int COLUMNS = 6;
        private final int ZOOM_FACTOR = FocusHighlight.ZOOM_FACTOR_LARGE;
        private ArrayObjectAdapter mAdapter;
        private SparseArray<MovieRow> movieRowSparseArraySerie;
        // rows - 0 - MOVIE
        private static final int SERIE = 0;
        private GlideBackgroundManager glideBackgroundManager;


        public Series(GlideBackgroundManager glideBackgroundManager) {
            this.glideBackgroundManager = glideBackgroundManager;
        }

        public Series() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            App.instance().appComponent().inject(this);
            setupAdapter();
            loadData("serie Tv");
            setupEventListeners3();
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
        }


        private void setupEventListeners3() {
            setOnItemViewSelectedListener(this);
            setOnItemViewClickedListener(this);
        }
        // Creates the data rows objects
        private void createDataRows() {

            movieRowSparseArraySerie = new SparseArray<>();

            //The CardPresenter defines the UI of the items in the row
            MoviePresenter moviePresenter = new MoviePresenter();

            //row - 1 - create objects
            movieRowSparseArraySerie.put(SERIE, new MovieRow()
                    .setId(SERIE)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.serie))
                    .setPage(1)
            );
        }


        private void setupAdapter() {
            VerticalGridPresenter presenter = new VerticalGridPresenter(ZOOM_FACTOR);
            presenter.setNumberOfColumns(COLUMNS);
            setGridPresenter(presenter);

            createDataRows();

            MovieRow movieRow = movieRowSparseArraySerie.get(SERIE);

            mAdapter = movieRow.getAdapter();
            setAdapter(mAdapter);
        }

        private void loadData(String category) {

            theMovieDbAPI.getFilterByCategory(category)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindSeriesResponse(response, SERIE);
                        //startEntranceTransition();
                    }, e -> {
                        Timber.e(e, "Error fetching now playing serie: %s", e.getMessage());
                    });
        }


        private void bindSeriesResponse(RootFilter response, int id) {

            List<Data> dataList = response.getData();

            if(dataList.size()>0){
                MovieRow movieRow = movieRowSparseArraySerie.get(id);
                movieRow.setPage(movieRow.getPage() + 1);

                for(Data data : dataList){
                    if(data.getPhoto().getLink() != null){
                        movieRow.getAdapter().add(data);
                    }
                }
            }
        }


        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            // Check if the item is a actor
            /*if (item instanceof Actor) {
                Actor actor = (Actor) item;
                // Check if the langue has a backdrop
                if(actor.getImage() != null) {
                    glideBackgroundManager.loadImage(actor.getImage());
                } else {
                    // If there is no backdrop for the langue we just use a default one
                    glideBackgroundManager.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.place_holder_channel));
                }
            }*/

            // Check if the item is a poster
            if (item instanceof Data) {
                Data data = (Data) item;
                // Check if the langue has a backdrop
                 if(data.getPhoto().getLink() != null) {
                    glideBackgroundManager.loadImage(Config.GLOBAL_URL + data.getPhoto().getLink());
                } else {
                    // If there is no backdrop for the langue we just use a default one
                    glideBackgroundManager.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.place_holder_channel));
                }
            }
        }

        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            /*if (item instanceof Actor) {
                Actor actor = (Actor) item;
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                // Pass the langue to the activity
                intent.putExtra(Actor.class.getSimpleName(), actor);

                if (itemViewHolder.view instanceof ActorCardView) {
                    // Pass the ImageView to allow a nice transition
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(),
                            ((ActorCardView) itemViewHolder.view).getPosterIV(),
                            DetailFragment.TRANSITION_NAME).toBundle();
                    getActivity().startActivity(intent, bundle);
                } else {
                    startActivity(intent);
                }
            }*/
            if( item instanceof Data){
                Data data = (Data) item;
                Intent intent = new Intent(getActivity(), DetailDataActivity.class);
                // Pass the langue to the activity
                intent.putExtra(Data.class.getSimpleName(), data);

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

        @Override
        public void onResume() {
            super.onResume();
            //loadData("serie Tv", 1);
        }
    }


    public static class Live extends Fragment implements MainFragmentAdapterProvider {
        private MainFragmentAdapter mMainFragmentAdapter = new MainFragmentAdapter(this);
        private WebView mWebview;

        @Override
        public MainFragmentAdapter getMainFragmentAdapter() {
            return mMainFragmentAdapter;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getMainFragmentAdapter().getFragmentHost().showTitleView(false);
        }

        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            FrameLayout root = new FrameLayout(getActivity());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            lp.setMarginStart(32);
            mWebview = new WebView(getActivity());
            mWebview.setWebViewClient(new WebViewClient());
            mWebview.getSettings().setJavaScriptEnabled(true);
            root.addView(mWebview, lp);
            return root;
        }

        @Override
        public void onResume() {
            super.onResume();
            mWebview.loadUrl("https://ozstream.tv");
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
        }
    }

    public static class ContactUsWebView extends Fragment implements MainFragmentAdapterProvider {
        private MainFragmentAdapter mMainFragmentAdapter = new MainFragmentAdapter(this);
        private WebView mWebview;

        @Override
        public MainFragmentAdapter getMainFragmentAdapter() {
            return mMainFragmentAdapter;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getMainFragmentAdapter().getFragmentHost().showTitleView(false);
        }

        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            FrameLayout root = new FrameLayout(getActivity());
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT);
            lp.setMarginStart(32);
            mWebview = new WebView(getActivity());
            mWebview.setWebViewClient(new WebViewClient());
            mWebview.getSettings().setJavaScriptEnabled(true);
            root.addView(mWebview, lp);
            return root;
        }

        @Override
        public void onResume() {
            super.onResume();
            mWebview.loadUrl("https://ozstream.tv");
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
        }
    }


    public static class setting extends RowsFragment implements OnItemViewClickedListener{

        private final ArrayObjectAdapter mRowsAdapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setupEventListeners4();
        }

        private void setupEventListeners4() {
            setOnItemViewClickedListener(this);
        }

        public setting() {
            ListRowPresenter selector = new ListRowPresenter();
            selector.setNumRows(1);
            mRowsAdapter = new ArrayObjectAdapter(selector);
            setAdapter(mRowsAdapter);
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadData();
                }
            }, 200);
        }

        private void loadData() {
            if (isAdded()) {
                String json = Utils.inputStreamToString(getResources().openRawResource(
                        R.raw.menu_setting));
                CardRow cardRow = new Gson().fromJson(json, CardRow.class);
                mRowsAdapter.add(createCardRow(cardRow));
                getMainFragmentAdapter().getFragmentHost().notifyDataReady(
                        getMainFragmentAdapter());
            }
        }

        private ListRow createCardRow(CardRow cardRow) {
            SettingsIconPresenter iconCardPresenter = new SettingsIconPresenter(getActivity());
            ArrayObjectAdapter adapter = new ArrayObjectAdapter(iconCardPresenter);
            for(Card card : cardRow.getCards()) {
                adapter.add(card);
            }

            HeaderItem headerItem = new HeaderItem(cardRow.getTitle());
            return new CardListRowSetting(headerItem, adapter, cardRow);
        }

        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof Card) {
                Card card = (Card) item;

                if(card.getTitle().equalsIgnoreCase("Langue")){



                    /**
                     * Add to home screen feature is not allowed in the system less than android O
                     * a toast will be popped up as notification
                     */
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                        Toast.makeText(getActivity(), "Add to home screen not supported", Toast.LENGTH_SHORT)
                                .show();
                        return;
                    }
                    Intent intent = new Intent(getActivity(), ChannelPublishActivity.class);
                    startActivity(intent);



                }else {
                    showSourcesPlayDialog();
                }
            }
        }

        @SuppressLint("SetTextI18n")
        private void showSourcesPlayDialog() {
            Dialog play_source_dialog = new Dialog(getActivity(),
                    R.style.Theme_Dialog);
            play_source_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            play_source_dialog.setCancelable(true);
            play_source_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            Window window = play_source_dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            getActivity().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            wlp.gravity = Gravity.BOTTOM;
            window.setAttributes(wlp);
            play_source_dialog.setContentView(R.layout.dialog_sources);

            RelativeLayout relative_layout_dialog_source_close =  play_source_dialog.findViewById(R.id.relative_layout_dialog_source_close);

            TextView version = play_source_dialog.findViewById(R.id.version);
            version.setText("Android TV Version " + BuildConfig.VERSION_NAME);

            TextView copy = (TextView) play_source_dialog.findViewById(R.id.copy);
            copy.setText("© " + Calendar.getInstance().get(Calendar.YEAR) + " " + getString(R.string.sloganCinaf));

            relative_layout_dialog_source_close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    play_source_dialog.dismiss();
                }
            });
            play_source_dialog.setOnKeyListener(new Dialog.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface arg0, int keyCode,
                                     KeyEvent event) {
                    // TODO Auto-generated method stub
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        play_source_dialog.dismiss();
                    }
                    return true;
                }
            });
            play_source_dialog.show();
        }


    }



    public static class Logout extends Fragment implements MainFragmentAdapterProvider{

        private static String TAG= "Logout";
        private MainFragmentAdapter mMainFragmentAdapter = new MainFragmentAdapter(this);

        public Logout() {
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Deconnexion();
        }


        @Override
        public MainFragmentAdapter getMainFragmentAdapter() {
            return mMainFragmentAdapter;
        }

        private void Deconnexion() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle(getString(R.string.confirmExit));
            alertDialogBuilder.setIcon(R.drawable.ic_exit_to_app_24);
            alertDialogBuilder.setMessage(getString(R.string.confirmExitMsg));
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setPositiveButton(getString(R.string.oui), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ProgressDialog dialog2 = ProgressDialog.show(getActivity(), getString(R.string.deconnexion), getString(R.string.deconnexion), true);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            PrefManager prf = new PrefManager(getActivity());
                            prf.remove("CODE_TV");
                            dialog.dismiss();
                            dialog2.dismiss();
                            getActivity().finish();
                        }
                    }, 2000); // 2000 milliseconds delay
                }
            });
            alertDialogBuilder.setNegativeButton(getString(R.string.non), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }
}
