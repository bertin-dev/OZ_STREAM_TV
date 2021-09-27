package com.oz_stream.tv.ui.main;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.leanback.app.BrowseFragment;
import androidx.leanback.app.RowsFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
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

import com.oz_stream.tv.App;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.api.TheMovieDbAPI;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.data.models.Frees;
import com.oz_stream.tv.data.models.News;
import com.oz_stream.tv.data.models.Populars;
import com.oz_stream.tv.data.models.Previews;
import com.oz_stream.tv.data.models.Root;
import com.oz_stream.tv.provider.PrefManager;
import com.oz_stream.tv.ui.base.GlideBackgroundManager;
import com.oz_stream.tv.ui.base.IconHeaderItemPresenter;
import com.oz_stream.tv.ui.detail.DetailActivity;
import com.oz_stream.tv.ui.detail.DetailFragment;
import com.oz_stream.tv.ui.detail.DetailPosterActivity;
import com.oz_stream.tv.ui.detail.DetailPosterFragment;
import com.oz_stream.tv.ui.movie.ActorCardView;
import com.oz_stream.tv.ui.movie.ActorPresenter;
import com.oz_stream.tv.ui.movie.MovieCardView;
import com.oz_stream.tv.ui.movie.MoviePresenter;
import com.oz_stream.tv.ui.movie.PopularMoviePresenter;
import com.oz_stream.tv.ui.player.caster.DataListLoader;
import com.oz_stream.tv.ui.search.SearchActivity;

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
    //private static final String HEADER_NAME_4 = "Acteurs";

    private static final long HEADER_ID_5 = 5;
    //private static final String HEADER_NAME_5 = "Paramètres";

    private static final long HEADER_ID_6 = 6;
    //private static final String HEADER_NAME_6 = "Deconnexion";

    private static final long HEADER_ID_7 = 7;
    //private static final String HEADER_NAME_7 = "Contactez nous";
    private static final long HEADER_ID_8 = 8;
    //private static final String HEADER_NAME_8 = "Politique de Confidentialité";
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
        getMainFragmentRegistry().registerFragment(PageRow.class, new PageRowFragmentFactory(glideBackgroundManager));
    }

    private void setupUIElements() {
        glideBackgroundManager = new GlideBackgroundManager(getActivity());
        setBadgeDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.logo_official_thumbnail));
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

        HeaderItem headerItem4 = new HeaderItem(HEADER_ID_4, getString(R.string.acteurs));
        PageRow pageRow4 = new PageRow(headerItem4);
        mRowsAdapter.add(pageRow4);

        HeaderItem headerItem5 = new HeaderItem(HEADER_ID_5, getString(R.string.parametres));
        PageRow pageRow5 = new PageRow(headerItem5);
        mRowsAdapter.add(pageRow5);


        HeaderItem headerItem6 = new HeaderItem(HEADER_ID_6, getString(R.string.deconnexion));
        PageRow pageRow6 = new PageRow(headerItem6);
        mRowsAdapter.add(pageRow6);

        HeaderItem headerItem7 = new HeaderItem(HEADER_ID_7, getString(R.string.contactNous));
        PageRow pageRow7 = new PageRow(headerItem7);
        mRowsAdapter.add(pageRow7);

        HeaderItem headerItem8 = new HeaderItem(HEADER_ID_8, getString(R.string.policy));
        PageRow pageRow8 = new PageRow(headerItem8);
        mRowsAdapter.add(pageRow8);
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

            if (row.getHeaderItem().getId() == HEADER_ID_1) {
                return new Accueil(mBackgroundManager);
            } else if (row.getHeaderItem().getId() == HEADER_ID_6) {
                return new Logout();
            }else if (row.getHeaderItem().getId() == HEADER_ID_7) {
                return new ContactUsWebView();
            }else if (row.getHeaderItem().getId() == HEADER_ID_8) {
                return new PolicyConfidentialityWebView();
            }

            //mBackgroundManager.setDrawable(null);
            /*if (row.getHeaderItem().getId() == HEADER_ID_1) {
                return new Accueil(mBackgroundManager);
            } else if (row.getHeaderItem().getId() == HEADER_ID_2) {
                return new Films();
            } else if (row.getHeaderItem().getId() == HEADER_ID_3) {
                return new Series();
            }else if (row.getHeaderItem().getId() == HEADER_ID_4) {
                return new Actors();
            }else if (row.getHeaderItem().getId() == HEADER_ID_5) {
                return new setting();
            }else if (row.getHeaderItem().getId() == HEADER_ID_6) {
                return new Logout();
            }else if (row.getHeaderItem().getId() == HEADER_ID_7) {
                return new ContactUsWebView();
            }else if (row.getHeaderItem().getId() == HEADER_ID_8) {
                return new PolicyConfidentialityWebView();
            }*/

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

        // rows - 0 - Accueil
        private static final int AVANT_PREMIERE = 0;

        // rows - 1 - top rated
        private static final int MIEUX_NOTE = 1;

        // rows - 2 - popular
        private static final int POPULAIRE = 2;

        // rows - 3 - gratuit
        private static final int VIDEO_GRATUIT = 3;

        // rows - 4 - drame
        private static final int DRAME = 4;

        // rows - 5 - humour
        private static final int HUMOUR = 5;

        // rows - 6 - production cinaf
        private static final int PRODUCTION_CINAF = 6;

        // rows - 7 - action
        private static final int ACTION = 7;

        // rows - 8 - action
        private static final int ROMANCE = 8;

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

            //fetchActorsMovies();
            fetchTopRatedMovies();
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
            //fetchSlideMovies();


            /*fetchDrameMovies();
            fetchHumourMovies();
            fetchProductionCinafMovies();*/
            setupEventListeners1();
        }



        @Override
        public void onResume() {
            super.onResume();
            //Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();

            //fetchActionMovies();
            //fetchRomanceMovies();
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
            movieRowSparseArray.put(AVANT_PREMIERE, new MovieRow()
                    .setId(AVANT_PREMIERE)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.avant_premiere))
                    .setPage(1)
            );

            //row - 1 - create objects
            movieRowSparseArray.put(MIEUX_NOTE, new MovieRow()
                    .setId(MIEUX_NOTE)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.mieuxNote))
                    .setPage(1)
            );

            //row - 2 - create objects
            movieRowSparseArray.put(POPULAIRE, new MovieRow()
                    .setId(POPULAIRE)
                    .setAdapter(new ArrayObjectAdapter(popularMoviePresenter))
                    .setTitle(getString(R.string.plusPopulaire))
                    .setPage(1)
            );

            //row - 3 - create objects
            movieRowSparseArray.put(VIDEO_GRATUIT, new MovieRow()
                    .setId(VIDEO_GRATUIT)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.movie_free))
                    .setPage(1)
            );


            //row - 4 - create objects
            movieRowSparseArray.put(DRAME, new MovieRow()
                    .setId(DRAME)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.drame))
                    .setPage(1)
            );


            //row - 5 - create objects
            movieRowSparseArray.put(HUMOUR, new MovieRow()
                    .setId(HUMOUR)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.humour))
                    .setPage(1)
            );


            //row - 6 - create objects
            movieRowSparseArray.put(PRODUCTION_CINAF, new MovieRow()
                    .setId(PRODUCTION_CINAF)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.productionOZSTREAM))
                    .setPage(1)
            );

            //row - 7 - create objects
            movieRowSparseArray.put(ACTION, new MovieRow()
                    .setId(ACTION)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.action))
                    .setPage(1)
            );

            //row - 8 - create objects
            movieRowSparseArray.put(ROMANCE, new MovieRow()
                    .setId(ROMANCE)
                    .setAdapter(new ArrayObjectAdapter(moviePresenter))
                    .setTitle(getString(R.string.romance))
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
                        bindPreviewResponse(response, AVANT_PREMIERE);
                        //startEntranceTransition();
                    }, e -> {
                        Timber.e(e, "Error fetching popular movies: %s", e.getMessage());
                    });
        }

        // Vidéos populaires
        private void fetchPopularMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindPopularResponse(response, POPULAIRE);
                        //startEntranceTransition();
                    }, e -> {
                        Timber.e(e, "Error fetching popular movies: %s", e.getMessage());
                    });
        }

        //MIEUX NOTE (NOUVEAUTE)
        private void fetchTopRatedMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindTopRatedResponse(response, MIEUX_NOTE);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching Top Rated movies: %s", e.getMessage()));
        }


        private void fetchFreesMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindFreesMoviesResponse(response, VIDEO_GRATUIT);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching upcoming movies: %s", e.getMessage()));
        }


        /*private void fetchActorsMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        //bindActorsResponse(response, ACCUEIL);
                        //startEntranceTransition();
                    }, e -> {
                        Timber.e(e, "Error fetching now Actor movies: %s", e.getMessage());
                    });
        }


        private void fetchDrameMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindDrameResponse(response, DRAME);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching drame movies: %s", e.getMessage()));
        }


        private void fetchHumourMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindHumourResponse(response, HUMOUR);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching Humour movies: %s", e.getMessage()));
        }


        private void fetchProductionCinafMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindProductionCinafResponse(response, PRODUCTION_CINAF);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching production cinaf movies: %s", e.getMessage()));
        }


        private void fetchActionMovies() {
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindActionResponse(response, ACTION);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching action movies: %s", e.getMessage()));
        }

        private void fetchRomanceMovies(){
            theMovieDbAPI.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        bindRomanceResponse(response, ROMANCE);
                        //startEntranceTransition();
                    }, e -> Timber.e(e, "Error fetching romance movies: %s", e.getMessage()));
        }*/


        /*private void bindActorsResponse(Root response, int id) {
            Log.w("ACTOR", "bindActorsResponse: " + response.getActors() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());
            for(Actor actor : response.getActors()){
                if(actor.getImage() != null){
                    movieRow.getAdapter().add(actor);
                }
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }*/


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


        //MIEUX NOTE (NOUVEAUTE)
        private void bindTopRatedResponse(Root response, int id) {
            //Log.w("TOP RATED", "bindTopRatedResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
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
            movieRow.getAdapter().add(dataList);

            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }

        /*
        private void bindDrameResponse(Root response, int id) {
            Log.w("DRAME", "bindDrameResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());
            List<Genre> genres = response.getGenres();

            //films les mieux notés (Poster)
            for(Genre genre : genres){

                if(genre.getTitle().trim().toLowerCase().equalsIgnoreCase("drame")){
                    for (Poster poster : genre.getPosters()){
                        if(poster.getImage() != null){
                            movieRow.getAdapter().add(poster);
                        }
                    }
                }
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }


        private void bindHumourResponse(Root response, int id) {
            Log.w("HUMOUR", "bindHumourResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());
            List<Genre> genres = response.getGenres();

            //films les mieux notés (Poster)
            for(Genre genre : genres){

                if(genre.getTitle().trim().toLowerCase().equalsIgnoreCase("humour")){
                    for (Poster poster : genre.getPosters()){
                        if(poster.getImage() != null){
                            movieRow.getAdapter().add(poster);
                        }
                    }
                }
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }


        private void bindProductionCinafResponse(Root response, int id) {
            Log.w("PRODUCTION CINAF", "bindProductionCinafResponse: " + response.getGenres().get(0).getTitle().toLowerCase() );
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());
            List<Genre> genres = response.getGenres();

            //films les mieux notés (Poster)
            for(Genre genre : genres){

                if(genre.getTitle().trim().toLowerCase().equalsIgnoreCase("production cinaf")){
                    for (Poster poster : genre.getPosters()){
                        if(poster.getImage() != null){
                            movieRow.getAdapter().add(poster);
                        }
                    }
                }
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }


        private void bindActionResponse(Root response, int id) {
            Log.w("ACTION", "bindActionResponse: " + response.getGenres().get(0).getTitle().toLowerCase());
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());
            List<Genre> genres = response.getGenres();

            //films les mieux notés (Poster)
            for(Genre genre : genres){

                if(genre.getTitle().trim().toLowerCase().equalsIgnoreCase("action")){
                    for (Poster poster : genre.getPosters()){
                        if(poster.getImage() != null){
                            movieRow.getAdapter().add(poster);
                        }
                    }
                }
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }


        private void bindRomanceResponse(Root response, int id) {
            Log.w("ACTION", "bindRomancenResponse: " + response.getGenres().get(0).getTitle().toLowerCase());
            MovieRow movieRow = movieRowSparseArray.get(id);
            movieRow.setPage(movieRow.getPage() + 1);
            HeaderItem headerItem = new HeaderItem(movieRow.getTitle());
            List<Genre> genres = response.getGenres();

            //films les mieux notés (Poster)
            for(Genre genre : genres){

                if(genre.getTitle().trim().toLowerCase().equalsIgnoreCase("romance")){
                    for (Poster poster : genre.getPosters()){
                        if(poster.getImage() != null){
                            movieRow.getAdapter().add(poster);
                        }
                    }
                }
            }
            rowsAdapter.add(new CardListRow(headerItem, movieRow.getAdapter(),response));
        }*/

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
                    glideBackgroundManager.loadImage(data.getPhoto().getLink());
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
                Intent intent = new Intent(getActivity(), DetailPosterActivity.class);
                // Pass the langue to the activity
                intent.putExtra(Data.class.getSimpleName(), data);

                if (itemViewHolder.view instanceof MovieCardView) {
                    // Pass the ImageView to allow a nice transition
                    Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            getActivity(),
                            ((MovieCardView) itemViewHolder.view).getMovie_img(),
                            DetailPosterFragment.TRANSITION_NAME).toBundle();
                    getActivity().startActivity(intent, bundle);
                } else {
                    startActivity(intent);
                }
            }
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
            mWebview.loadUrl("https://cinaf.fr/tv/contact.html");
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
        }
    }
    public static class PolicyConfidentialityWebView extends Fragment implements MainFragmentAdapterProvider {
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
            mWebview.loadUrl("https://cinaf.fr/tv/police.html");
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
        }
    }
}
