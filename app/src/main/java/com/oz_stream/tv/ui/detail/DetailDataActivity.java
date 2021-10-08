package com.oz_stream.tv.ui.detail;

import android.os.Bundle;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.oz_stream.tv.Config;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.ui.base.BaseTVActivity;
import com.oz_stream.tv.ui.base.GlideBackgroundManager;


public class DetailDataActivity extends BaseTVActivity {

    private static final String TAG = "DetailDataActivity";
    GlideBackgroundManager glideBackgroundManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Data
        Data data = getIntent().getExtras().getParcelable(Data.class.getSimpleName());

        Log.w(TAG, "bindNewsResponse: " + data );
        Log.w(TAG, "bindNewsResponse1: " + data.getBande_anonce().getName() );

        DetailDataFragment detailPosterFragment = DetailDataFragment.newInstance(data);
        addFragment(detailPosterFragment);



        glideBackgroundManager = new GlideBackgroundManager(this);

        if(data != null && data.getPhoto().getLink() != null){
            glideBackgroundManager.loadImage(Config.GLOBAL_URL + data.getPhoto().getLink() );
            //glideBackgroundManager.setBackgroundColors(Color.parseColor("#FF263238"));
        }
        else {
            glideBackgroundManager.setBackground(ContextCompat.getDrawable(this, R.drawable.placeholder));
        }

    }
}
