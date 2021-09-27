package com.oz_stream.tv.ui.player;

import android.os.Bundle;

import com.oz_stream.tv.R;
import com.oz_stream.tv.translate.LocaleHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.Locale;

import fr.bmartel.youtubetv.YoutubeTvView;

public class PlayerActivity extends Activity {


    YoutubeTvView youtubeTvView;

    String currentLanguage = (Locale.getDefault().getLanguage().contentEquals("fr")) ? "fr" : "en", currentLang;

    @Override
    protected void onStart() {
        super.onStart();
        //langue par défaut
        currentLanguage = getIntent().getStringExtra(currentLang);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        youtubeTvView = findViewById(R.id.player);

        Intent intent = getIntent();

        // videoId for playing video
        youtubeTvView.playVideo(intent.getStringExtra("videoId"));

    }


    /**
     * attachBaseContext(Context newBase) methode callback permet de verifier la langue au demarrage
     * @param newBase
     * @since 2021
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }


}
