package com.flymob.sample.samples.interstitial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.flymob.sample.R;
import com.flymob.sample.utiles.ToastHelper;
import com.flymob.sdk.common.FlyMob;
import com.flymob.sdk.common.ads.FailResponse;
import com.flymob.sdk.common.ads.interstitial.IFlyMobInterstitialListener;
import com.flymob.sdk.common.ads.interstitial.FlyMobInterstitial;

public class InterstitialActivity extends AppCompatActivity {
    private static final int ZONE_ID = 605778;
    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;
    FlyMobInterstitial mFlyMobInterstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mButtonLoad = findViewById(R.id.button_load);
        mButtonShow = findViewById(R.id.button_show);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(String.valueOf(ZONE_ID));

        initInterstitial();

        //To ensure a smooth experience, you should pre-fetch the content as soon
        // as your Activity is created, then display it if the fetch was successful.
        loadInterstitial();

        mButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInterstitial();
            }
        });

        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonShow.setEnabled(false);
                if (mFlyMobInterstitial.isLoaded()) {
                    mFlyMobInterstitial.show();
                }
            }
        });
    }

    private void loadInterstitial() {
        mButtonShow.setEnabled(false);

        mFlyMobInterstitial.setZoneId(getZoneId());
        mFlyMobInterstitial.load();
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

    private void initInterstitial() {
        mFlyMobInterstitial = new FlyMobInterstitial(this, getZoneId());
        mFlyMobInterstitial.addListener(new IFlyMobInterstitialListener() {
            @Override
            public void loaded(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "loaded");
                mButtonShow.setEnabled(true);
            }

            @Override
            public void failed(FlyMobInterstitial interstitial, FailResponse response) {
                ToastHelper.showToast(InterstitialActivity.this, "failed " + response.getResponseString());
            }

            @Override
            public void shown(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "shown");
            }

            @Override
            public void clicked(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "clicked");
            }

            @Override
            public void closed(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "closed");
            }

            @Override
            public void expired(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(InterstitialActivity.this, "expired");
                loadInterstitial();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mFlyMobInterstitial != null) {
            mFlyMobInterstitial.onDestroy();
            mFlyMobInterstitial = null;
        }
        super.onDestroy();
    }
}



