package com.oz_stream.tv.ui.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;

import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Actor;
import com.oz_stream.tv.ui.base.BaseTVActivity;
import com.oz_stream.tv.ui.base.GlideBackgroundManager;

public class DetailActivity extends BaseTVActivity {

    GlideBackgroundManager glideBackgroundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Actor
        Actor actor = getIntent().getExtras().getParcelable(Actor.class.getSimpleName());

        DetailFragment detailsFragment = DetailFragment.newInstance(actor);
        addFragment(detailsFragment);

        glideBackgroundManager = new GlideBackgroundManager(this);

        if(actor.getAvatar() != null){
            glideBackgroundManager.loadImage(actor.getAvatar());
            //glideBackgroundManager.setBackgroundColors(Color.parseColor("#FF263238"));
        } else {
            glideBackgroundManager.setBackground(ContextCompat.getDrawable(this, R.drawable.placeholder));
        }

    }
}
