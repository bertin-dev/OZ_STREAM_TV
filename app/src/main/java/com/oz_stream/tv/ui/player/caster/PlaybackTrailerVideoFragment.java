/**
 * Copyright 2020 Google LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oz_stream.tv.ui.player.caster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.widget.Toast;

import androidx.leanback.app.VideoSupportFragment;
import androidx.leanback.app.VideoSupportFragmentGlueHost;
import androidx.leanback.widget.PlaybackControlsRow;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.leanback.LeanbackPlayerAdapter;
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector;
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.MediaMetadataProvider;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.cast.MediaError.DetailedErrorCode;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.tv.CastReceiverContext;
import com.google.android.gms.cast.tv.media.MediaException;
import com.google.android.gms.cast.tv.media.MediaInfoWriter;
import com.google.android.gms.cast.tv.media.MediaLoadCommandCallback;
import com.google.android.gms.cast.tv.media.MediaManager;
import com.google.android.gms.cast.tv.media.MediaManager.MediaStatusInterceptor;
import com.google.android.gms.cast.tv.media.MediaStatusWriter;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.models.Data;
import com.oz_stream.tv.ui.main.MainActivity;
import com.oz_stream.tv.ui.player.EmbedActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Handles video playback with media controls.
 */
public class PlaybackTrailerVideoFragment extends VideoSupportFragment {

    private static final String TAG = "PlaybackTrailerVideoFrg";
    private static final int UPDATE_DELAY = 16;

    private MediaSessionCompat mMediaSession;
    private MediaSessionConnector mMediaSessionConnector;

    private SimpleExoPlayer mPlayer;
    private LeanbackPlayerAdapter mPlayerAdapter;
    private VideoPlayerGlue mPlayerGlue;
    private PlaylistActionListener mPlaylistActionListener;
    private MyMediaMetadataProvider mMediaMetadataProvider;
    private Data playingMovie;

    private MediaManager mMediaManager;

    private static final String TYPE_HLS = "application/x-mpegurl";
    private static final String TYPE_MP4 = "video/mp4";
    private static String type;

    private Data data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        mMediaSession = new MediaSessionCompat(getContext(), TAG);
        mMediaSessionConnector = new MediaSessionConnector(mMediaSession);
        initializePlayer();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");

        mMediaManager = CastReceiverContext.getInstance().getMediaManager();
        mMediaManager.setSessionCompatToken(mMediaSession.getSessionToken());
        Log.w(TAG, "myFillMediaInfo4444444444444444: " + mMediaSession.getSessionToken() );
        mMediaManager.setMediaLoadCommandCallback(new MyMediaLoadCommandCallback());

        mMediaManager.setMediaStatusInterceptor(new MediaStatusInterceptor() {
            @Override
            public void intercept(MediaStatusWriter mediaStatusWriter) {
                try {
                    mediaStatusWriter.setCustomData(new JSONObject("{data: 'CustomData'}"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        initializePlayer();
        mMediaSessionConnector.setPlayer(mPlayer);
        mMediaSessionConnector.setMediaMetadataProvider(mMediaMetadataProvider);
        mMediaSession.setActive(true);

        if (mMediaManager.onNewIntent(getActivity().getIntent())) {
            // If the SDK recognizes the intent, you should early return.
            return;
        }

        // If the SDK doesn't recognize the intent, you can handle the intent with
        // your own logic.
        processIntent(getActivity().getIntent());

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        if (mPlayerGlue != null && mPlayerGlue.isPlaying()) {
            mPlayerGlue.pause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");

        mMediaSessionConnector.setPlayer(null);
        mMediaSession.setActive(false);
        mMediaSession.release();
        mMediaManager.setSessionCompatToken(null);
        releasePlayer();
        getActivity().finish();
    }

    @Override
    public void onError(int errorCode, CharSequence errorMessage) {
        Log.d(TAG, "onError");
        logAndDisplay(errorMessage.toString());
        getActivity().finish();

        //En cas d'erreur rencontré, ouvrir le second lecteur
        Intent intent = new Intent(getActivity(), EmbedActivity.class);
        intent.putExtra("url", data.getBande_anonce().getLink());
        startActivity(intent);
    }

    void processIntent(Intent intent) {
        Log.d(TAG, "processIntent()");

        //2ème methode
        if (intent.hasExtra(MainActivity.DATA)) {
            // Intent came from MainActivity (User chose an item inside ATV app).
             data = (Data) intent.getParcelableExtra(MainActivity.DATA);
            Log.w(TAG, "OBOUN: " +data );
            type = TYPE_HLS;
            startPlayback(data, 0);
        } else {
            logAndDisplay("Null or unrecognized intent action");
            getActivity().finish();
        }
    }

    private static Data convertEntityToMovie(String entity) {
        return DataList.getList().get(0);
    }

    private static Data convertLoadRequestToMovie(MediaLoadRequestData loadRequestData) {
        if (loadRequestData == null) {
            return null;
        }
        MediaInfo mediaInfo = loadRequestData.getMediaInfo();
        if (mediaInfo == null) {
            return null;
        }

        type = mediaInfo.getContentType();

        String videoUrl = mediaInfo.getContentId();
        if (mediaInfo.getContentUrl() != null) {
            videoUrl = mediaInfo.getContentUrl();
        }

        Log.w(TAG, "88888888888888888888888888888: " + videoUrl );
        MediaMetadata metadata = mediaInfo.getMetadata();
        Data data1 = new Data();
        data1.setBande_anonce(data1.getBande_anonce());

        // A décommenté si nécessesaire

        /*Source source = new Source();
        source.setUrl(videoUrl);*/

        if (metadata != null) {
            data1.setTitle(metadata.getString(MediaMetadata.KEY_TITLE));
            data1.setDescription(metadata.getString(MediaMetadata.KEY_SUBTITLE));
            data1.getPhoto().setLink(metadata.getImages().get(0).getUrl().toString());
        }
        return data1;
    }

    private void initializePlayer() {
        if (mPlayer == null) {
            Log.d(TAG, "initializePlayer");
            VideoSupportFragmentGlueHost glueHost =
                    new VideoSupportFragmentGlueHost(PlaybackTrailerVideoFragment.this);

            mPlayer = ExoPlayerFactory.newSimpleInstance(getContext());
            mPlayerAdapter = new LeanbackPlayerAdapter(getContext(), mPlayer, UPDATE_DELAY);
            mPlayerAdapter.setRepeatAction(PlaybackControlsRow.RepeatAction.INDEX_NONE);
            mPlaylistActionListener = new PlaylistActionListener();
            mMediaMetadataProvider = new MyMediaMetadataProvider();
            mPlayerGlue = new VideoPlayerGlue(getContext(), mPlayerAdapter, mPlaylistActionListener);
            mPlayerGlue.setHost(glueHost);
            mPlayerGlue.setSeekEnabled(true);
        }
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            Log.d(TAG, "releasePlayer");
            mPlayer.release();
            mPlayer = null;
            mPlayerAdapter = null;
        }
    }

    private void startPlayback(Data data_poster, long startPosition) {
        playingMovie = data_poster;
        mPlayerGlue.setTitle(data_poster.getTitle());
        mPlayerGlue.setSubtitle(data_poster.getDescription());


        if(data_poster.getBande_anonce() != null){
            Log.w(TAG, "MOUNOK2222: " + data_poster.getBande_anonce().getLink() );
            prepareMediaForPlaying(Uri.parse(data_poster.getBande_anonce().getLink()));
            /*for(Source source: data_poster.getSources()){
                prepareMediaForPlaying(Uri.parse(source.getUrl()));
            }*/
        } else {
            Toast.makeText(getContext(), "Vidéo Indisponible", Toast.LENGTH_SHORT).show();
        }
        //prepareMediaForPlaying(Uri.parse("https://player.vimeo.com/external/453649653.m3u8?s=9d77c43a445dbddb0b9585150e3cea160b28f6fd"));
        mPlayerGlue.playWhenPrepared();
        mMediaManager.getMediaStatusModifier().clear();
    }

    private void prepareMediaForPlaying(Uri mediaSourceUri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
                getContext(), Util.getUserAgent(getContext(), getString(R.string.app_name)));

        MediaSource mediaSource;
        switch (type) {
            case TYPE_HLS:
                Log.w(TAG, "TYPE_HLS" );
                mediaSource = new HlsMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(mediaSourceUri);
                break;

            case TYPE_MP4:
                Log.w(TAG, "TYPE_MP4" );
                mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(mediaSourceUri);
                break;

            default:
                Log.w(TAG, "DEFAULT" );
                mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(mediaSourceUri);
                Log.d(TAG, "Unrecognized MediaSource");
        }
        mPlayer.prepare(mediaSource);
    }

    private void logAndDisplay(String error) {
        Log.d(TAG, error);
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    class PlaylistActionListener implements VideoPlayerGlue.OnActionClickedListener {

        private List<Data> mPlaylist;

        PlaylistActionListener() {
            this.mPlaylist = DataList.getList();
        }

        @Override
        public void onPrevious() {
            int currentIndex = playingMovie.getCount();
            if (currentIndex - 1 >= 0) {
                startPlayback(mPlaylist.get(currentIndex - 1),0);
            }
        }

        @Override
        public void onNext() {
            int currentIndex = playingMovie.getCount();
            if (currentIndex + 1 < mPlaylist.size()) {
                startPlayback(mPlaylist.get(currentIndex + 1), 0);
            }
        }
    }

    class MyMediaMetadataProvider implements MediaMetadataProvider {
        @Override
        public MediaMetadataCompat getMetadata(Player player) {
            MediaMetadataCompat.Builder mediaMetadata = new MediaMetadataCompat.Builder();
            if (playingMovie != null) {
                mediaMetadata.putString(
                        MediaMetadataCompat.METADATA_KEY_TITLE, playingMovie.getTitle());
                mediaMetadata.putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, playingMovie.getTitle());
                mediaMetadata.putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE,
                        playingMovie.getDescription());

                mediaMetadata.putString(
                        MediaMetadataCompat.METADATA_KEY_MEDIA_URI, playingMovie.getBande_anonce().getLink());
                /*for(Source source: playingMovie.getSources()){
                    mediaMetadata.putString(
                            MediaMetadataCompat.METADATA_KEY_MEDIA_URI, source.getUrl());
                }*/

                mediaMetadata.putString(
                        MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI,
                        playingMovie.getPhoto().getLink());
            }
            mediaMetadata.putLong(
                    MediaMetadataCompat.METADATA_KEY_DURATION, mPlayerGlue.getDuration());

            return mediaMetadata.build();
        }
    }

    private void myFillMediaInfo(MediaInfoWriter mediaInfoWriter) {
        MediaInfo mediaInfo = mediaInfoWriter.getMediaInfo();
        Log.d(TAG,"***Type:"+mediaInfo.getContentType());
        Log.w(TAG, "myFillMediaInfo00000000000000000: " );
        if (mediaInfo.getContentUrl() == null && mediaInfo.getEntity() != null) {
            Log.w(TAG, "myFillMediaInfo1111111111111111111111: " );
            // Load By Entity
            String entity = mediaInfo.getEntity();
            Data data1 = convertEntityToMovie(entity);

            MediaMetadata movieMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);
            movieMetadata.putString(MediaMetadata.KEY_TITLE, data1.getTitle());
            movieMetadata.putString(MediaMetadata.KEY_SUBTITLE, data1.getDescription());
            movieMetadata.putString(MediaMetadata.KEY_STUDIO, data1.getType());
            movieMetadata.addImage(new WebImage(Uri.parse(data1.getPhoto().getLink())));
            // A remplacer (j' ai mi la coveur du diffuseur)
            movieMetadata.addImage(new WebImage(Uri.parse(data1.getDiffuser().getCover())));

            Log.w(TAG, "myFillMediaInfo: " + data1.getPhoto().getLink());
            Log.w(TAG, "myFillMediaInfo11: " + data1.getDiffuser().getCover() );
            mediaInfoWriter.setContentUrl(data1.getBande_anonce().getLink()).setMetadata(movieMetadata);
            /*for(Source source: playingMovie.getSources()){
                mediaInfoWriter.setContentUrl(source.getUrl()).setMetadata(movieMetadata);
            }*/
        }
    }

    class MyMediaLoadCommandCallback extends MediaLoadCommandCallback {
        @Override
        public Task<MediaLoadRequestData> onLoad(String senderId, MediaLoadRequestData loadRequestData) {
            Log.w(TAG, "myFillMediaInfo55555555555555: " + mMediaSession.getSessionToken() );
            Toast.makeText(getActivity(), "onLoad()", Toast.LENGTH_SHORT).show();

            if (loadRequestData == null) {
                // Throw MediaException to indicate load failure.
                return Tasks.forException(new MediaException(
                        new MediaError.Builder()
                                .setDetailedErrorCode(DetailedErrorCode.LOAD_FAILED)
                                .setReason(MediaError.ERROR_REASON_INVALID_REQUEST)
                                .build()));
            }

            return Tasks.call(() -> {
                // Resolve the entity into your data structure and load media.
                Log.w(TAG, "myFillMediaInfo33333333333333: " + loadRequestData.getMediaInfo() );
                myFillMediaInfo(new MediaInfoWriter(loadRequestData.getMediaInfo()));
                startPlayback(convertLoadRequestToMovie(loadRequestData), 0);

                // Update media metadata and state (this clears all previous status
                // overrides).
                mMediaManager.setDataFromLoad(loadRequestData);
                mMediaManager.broadcastMediaStatus();

                return loadRequestData;
            });
        }
    }
}
