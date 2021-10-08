package com.oz_stream.tv.ui.publish_channel;

import androidx.leanback.app.GuidedStepFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.oz_stream.tv.R;
import com.oz_stream.tv.translate.LocaleHelper;

/**
 * Activity that showcases different aspects of GuidedStepFragments.
 */
public class ChannelPublishActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == savedInstanceState) {
            GuidedStepFragment.addAsRoot(this, new PublishChannelFragment(), android.R.id.content);
        }
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