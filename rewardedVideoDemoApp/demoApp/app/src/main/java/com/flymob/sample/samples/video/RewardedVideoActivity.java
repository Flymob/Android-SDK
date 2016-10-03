package com.flymob.sample.samples.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.flymob.sample.R;
import com.flymob.sample.utiles.ToastHelper;
import com.flymob.sdk.common.ads.FailResponse;
import com.flymob.sdk.common.ads.video.FlyMobRewardedVideo;
import com.flymob.sdk.common.ads.video.IFlyMobRewardedVideoListener;

public class RewardedVideoActivity extends AppCompatActivity {
    private static final int ZONE_ID = 759265;
    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;
    FlyMobRewardedVideo mFlyMobRewardedVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mButtonLoad = findViewById(R.id.button_load);
        mButtonShow = findViewById(R.id.button_show);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(String.valueOf(ZONE_ID));

        initRewardedVideo();

        //To ensure a smooth experience, you should pre-fetch the content as soon
        // as your Activity is created, then display it if the fetch was successful.
        loadRewardedVideo();

        mButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRewardedVideo();
            }
        });

        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonShow.setEnabled(false);
                if (mFlyMobRewardedVideo.isLoaded()) {
                    mFlyMobRewardedVideo.show();
                }
            }
        });
    }

    private void loadRewardedVideo() {
        mButtonShow.setEnabled(false);

        mFlyMobRewardedVideo.setZoneId(getZoneId());
        mFlyMobRewardedVideo.load();
    }

    private int getZoneId() {
        String digitsString = String.valueOf(mEditText.getText()).replaceAll("\\D+", "");
        try {
            int zoneId = Integer.parseInt(digitsString);
            return zoneId;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private void initRewardedVideo() {
        mFlyMobRewardedVideo = new FlyMobRewardedVideo(this, getZoneId());
        mFlyMobRewardedVideo.addListener(new IFlyMobRewardedVideoListener() {
            @Override
            public void loaded(FlyMobRewardedVideo video) {
                ToastHelper.showToast(RewardedVideoActivity.this, "loaded");
                mButtonShow.setEnabled(true);
            }

            @Override
            public void failed(FlyMobRewardedVideo video, FailResponse response) {
                ToastHelper.showToast(RewardedVideoActivity.this, "failed " + response.getResponseString());
            }

            @Override
            public void shown(FlyMobRewardedVideo video) {
                ToastHelper.showToast(RewardedVideoActivity.this, "shown");
            }

            @Override
            public void closed(FlyMobRewardedVideo video) {
                ToastHelper.showToast(RewardedVideoActivity.this, "closed");
            }

            @Override
            public void started(FlyMobRewardedVideo video) {
                ToastHelper.showToast(RewardedVideoActivity.this, "started");
            }

            @Override
            public void completed(FlyMobRewardedVideo video) {
                ToastHelper.showToast(RewardedVideoActivity.this, "completed");
            }

            @Override
            public void expired(FlyMobRewardedVideo video) {
                ToastHelper.showToast(RewardedVideoActivity.this, "expired");
                loadRewardedVideo();
            }

        });
    }

    @Override
    protected void onDestroy() {
        if (mFlyMobRewardedVideo != null) {
            mFlyMobRewardedVideo.onDestroy();
            mFlyMobRewardedVideo = null;
        }
        super.onDestroy();
    }
}



