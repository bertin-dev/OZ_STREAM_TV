package com.oz_stream.tv.ui.player.caster;


import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.oz_stream.tv.data.models.Data;

import java.util.List;

public class DataListLoader extends AsyncTaskLoader<List<Data>> {

    private static final String TAG = "DataListLoader";
    private final String mUrl;

    public DataListLoader(Context context, String url ) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public List<Data> loadInBackground() {
        Log.w("TAG", "DADI: ");
        try {

            Log.w("TAG", "MovieListLoader11111111111: "+ DataList.setupMovies(mUrl) );
            return DataList.setupMovies(mUrl);
        } catch (Exception e) {
            Log.w("TAG", "PAPOU: "+ e.getMessage());
            Log.e(TAG, "Failed to fetch media data", e);
            return null;
        }
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

}
