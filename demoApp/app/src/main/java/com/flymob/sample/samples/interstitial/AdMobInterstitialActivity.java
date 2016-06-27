package com.flymob.sample.samples.interstitial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.flymob.sample.R;
import com.flymob.sample.utiles.ToastHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class AdMobInterstitialActivity extends AppCompatActivity {

    private static final String AD_UNIT_ID = "ca-app-pub-9334091054678914/7594988387";
    private static final String APP_ID = "ca-app-pub-9334091054678914~6118255181";

    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mButtonLoad = findViewById(R.id.button_load);
        mButtonShow = findViewById(R.id.button_show);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        mEditText.setEnabled(false);
        mEditText.setText(String.valueOf(AD_UNIT_ID));

        MobileAds.initialize(getApplicationContext(), APP_ID);

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
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
            }
        });
    }

    private void loadInterstitial() {
        mButtonShow.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void initInterstitial() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(AD_UNIT_ID);

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                ToastHelper.showToast(AdMobInterstitialActivity.this, "onAdClosed");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
                mButtonShow.setEnabled(false);
                ToastHelper.showToast(AdMobInterstitialActivity.this, "onAdFailedToLoad");
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                ToastHelper.showToast(AdMobInterstitialActivity.this, "onAdLeftApplication");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                ToastHelper.showToast(AdMobInterstitialActivity.this, "onAdOpened");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mButtonShow.setEnabled(true);
                ToastHelper.showToast(AdMobInterstitialActivity.this, "onAdLoaded");
            }
        });
    }
}



