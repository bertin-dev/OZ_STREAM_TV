package com.oz_stream.tv.ui.search;

import androidx.leanback.app.SearchFragment;

import android.content.Intent;
import android.os.Bundle;

import com.oz_stream.tv.R;
import com.oz_stream.tv.ui.base.BaseTVActivity;

public class SearchActivity extends BaseTVActivity {

    androidx.leanback.app.SearchFragment searchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(com.oz_stream.tv.ui.search.SearchFragment.newInstance());

        searchFragment = (SearchFragment) getFragmentManager().findFragmentById(R.id.search_fragment);

    }


    @Override
    public boolean onSearchRequested() {
        startActivity(new Intent(this, SearchActivity.class));
        return true;
    }
}