package com.oz_stream.tv.dagger.components;

import com.oz_stream.tv.App;
import com.oz_stream.tv.dagger.AppScope;
import com.oz_stream.tv.dagger.modules.ApplicationModule;
import com.oz_stream.tv.dagger.modules.HttpClientModule;
import com.oz_stream.tv.ui.detail.DetailFragment;
import com.oz_stream.tv.ui.detail.DetailPosterFragment;
import com.oz_stream.tv.ui.dialog.DialogFragment;
import com.oz_stream.tv.ui.main.IDCode;
import com.oz_stream.tv.ui.main.MainFragment;
import com.oz_stream.tv.ui.main.SplashScreen;
import com.oz_stream.tv.ui.search.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

@AppScope
@Singleton
@Component(modules = {
        ApplicationModule.class,
        HttpClientModule.class,
})
public interface ApplicationComponent {

    void inject(App app);
    void inject(IDCode idCode);
    void inject(SplashScreen splashScreen);
    void inject(DialogFragment dialogFragment);
    void inject(MainFragment mainFragment);
    void inject(MainFragment.Accueil homeMovie);
    /*void inject(MainFragment.Films films);
    void inject(MainFragment.Series series);
    void inject(MainFragment.Actors actors);*/
    void inject(DetailFragment movieDetailsFragment);
    void inject(DetailPosterFragment detailPosterFragment);
    void inject(SearchFragment searchFragment);
}
