/*
 * *****************************************
 *  * Copyright (c) 2017
 *  * Alexey Baskakov
 *  *
 *  * FlyMob
 *  * http://flymob.com
 *  *****************************************
 */

package com.flymob.sample.samples.video;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.flymob.sample.R;
import com.flymob.sample.utiles.ToastHelper;
import com.flymob.sdk.common.ads.FailResponse;
import com.flymob.sdk.common.ads.video.FlyMobRewardedVideo;
import com.flymob.sdk.common.ads.video.IFlyMobRewardedVideoListener;

public class RewardedVideoActivity extends AppCompatActivity {
    private static final int ZONE_ID = 759265;
    Toolbar mToolBar;
    Button mButtonInit;
    View mButtonShow;
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mButtonInit = (Button) findViewById(R.id.button_load);
        mButtonInit.setText(getString(R.string.init));
        mButtonShow = findViewById(R.id.button_show);
        mButtonShow.setEnabled(false);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(String.valueOf(ZONE_ID));

        mButtonInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonInit.setEnabled(false);
                initRewardedVideo();
            }
        });

        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FlyMobRewardedVideo.isLoaded()) {
                    FlyMobRewardedVideo.show();
                }
            }
        });
    }

    private int getZoneId() {
        String digitsString = String.valueOf(mEditText.getText()).replaceAll("\\D+", "");
        try {
            return Integer.parseInt(digitsString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        mButtonShow.setEnabled(FlyMobRewardedVideo.isLoaded());
        mButtonInit.setEnabled(!FlyMobRewardedVideo.isLoaded());
    }

    private void initRewardedVideo() {
        FlyMobRewardedVideo.initialize(this, getZoneId());
        FlyMobRewardedVideo.setListener(new IFlyMobRewardedVideoListener() {
            @Override
            public void loaded() {
                ToastHelper.showToast(RewardedVideoActivity.this, "loaded");
                mButtonShow.setEnabled(true);
            }

            @Override
            public void shown() {
                ToastHelper.showToast(RewardedVideoActivity.this, "shown");
            }

            @Override
            public void closed() {
                ToastHelper.showToast(RewardedVideoActivity.this, "closed");
                updateUI();
            }

            @Override
            public void started() {
                ToastHelper.showToast(RewardedVideoActivity.this, "started");
            }

            @Override
            public void completed() {
                //reward user here
                ToastHelper.showToast(RewardedVideoActivity.this, "completed");
            }
        });
    }
}



