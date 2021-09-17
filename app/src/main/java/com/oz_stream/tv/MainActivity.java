package com.oz_stream.tv;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.oz_stream.tv.ui.base.BaseTVActivity;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
public class MainActivity extends BaseTVActivity {

    public static final String POSTER = "Poster";
    public static final String EPISODE = "Episode";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_browse_fragment, new MainFragment())
                    .commitNow();
        }*/
        addFragment(MainFragment.newInstance());
    }
}