package com.oz_stream.tv.ui.detail;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;

import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.ui.base.BaseTVActivity;
import com.oz_stream.tv.ui.base.GlideBackgroundManager;


public class DetailPosterActivity extends BaseTVActivity {

    GlideBackgroundManager glideBackgroundManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Data
        Data data = getIntent().getExtras().getParcelable(Data.class.getSimpleName());

        DetailPosterFragment detailPosterFragment = DetailPosterFragment.newInstance(data);
        addFragment(detailPosterFragment);



        glideBackgroundManager = new GlideBackgroundManager(this);

        if(data != null && data.getPhoto().getLink() != null){
            glideBackgroundManager.loadImage(data.getPhoto().getLink() );
            //glideBackgroundManager.setBackgroundColors(Color.parseColor("#FF263238"));
        }
        else {
            glideBackgroundManager.setBackground(ContextCompat.getDrawable(this, R.drawable.placeholder));
        }

    }
}
