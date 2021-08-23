package com.oz_stream.tv.dagger.components;

import com.oz_stream.tv.App;
import com.oz_stream.tv.dagger.AppScope;
import com.oz_stream.tv.dagger.modules.ApplicationModule;
import com.oz_stream.tv.dagger.modules.HttpClientModule;
import com.oz_stream.tv.ui.main.IDCode;

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

    /*void inject(MainFragment mainFragment);
    void inject(DetailFragment movieDetailsFragment);
    void inject(DetailPosterFragment detailPosterFragment);
    void inject(SearchFragment searchFragment);
    void inject(MainFragment.Accueil homeMovie);
    void inject(MainFragment.Films films);
    void inject(MainFragment.Series series);
    void inject(DialogFragment dialogFragment);
    void inject(SplashActivity splashActivity);
    void inject(MainFragment.Actors actors);*/
}
