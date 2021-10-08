package com.oz_stream.tv.ui.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.greenfrvr.rubberloader.RubberLoaderView;
import com.oz_stream.tv.App;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.api.TheMovieDbAPI;
import com.oz_stream.tv.provider.PrefManager;
import com.oz_stream.tv.translate.LocaleHelper;

import javax.inject.Inject;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;
    private ProgressDialog register_progress;
    private AlertDialog.Builder build_error;
    private Context context;
    @Inject
    TheMovieDbAPI theMovieDbAPI;
    private PrefManager prf;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        App.instance().appComponent().inject(this);
        prf = new PrefManager(getApplicationContext());
        build_error = new AlertDialog.Builder(this);
        context = this;
        ( (RubberLoaderView) findViewById(R.id.loader1)).startLoading();
        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                if (prf.getString("CODE_TV").isEmpty()) {
                    Intent i = new Intent(context, IDCode.class);
                    startActivity(i);
                    finish();
                } else {
                   /* submitIDCode(prf.getString("CODE_TV"));*/
                }
            }
        }, SPLASH_TIME_OUT);
    }


    /**
     * attachBaseContext(Context newBase) methode callback permet de verifier la langue au demarrage
     *
     * @param newBase
     * @since 2021
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }
}