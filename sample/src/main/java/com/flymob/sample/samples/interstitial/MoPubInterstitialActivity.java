package com.flymob.sample.samples.interstitial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.flymob.sample.R;
import com.flymob.sample.utiles.ToastHelper;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;

public class MoPubInterstitialActivity extends AppCompatActivity {

    private static final String AD_UNIT_ID = "a59fbfe8550948f3a65d22c552b98689";
    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;
    private MoPubInterstitial mMoPubInterstitial;

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
                if (mMoPubInterstitial.isReady()) {
                    mMoPubInterstitial.show();
                }
            }
        });
    }

    private void loadInterstitial() {
        mButtonShow.setEnabled(false);
        mMoPubInterstitial.load();
    }

    private void initInterstitial() {
        mMoPubInterstitial = new MoPubInterstitial(this, AD_UNIT_ID);
        mMoPubInterstitial.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
            @Override
            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
                ToastHelper.showMessage(MoPubInterstitialActivity.this, "onInterstitialLoaded");
                mButtonShow.setEnabled(true);
            }

            @Override
            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
                ToastHelper.showMessage(MoPubInterstitialActivity.this, "onInterstitialFailed " + errorCode);
                mButtonShow.setEnabled(false);

            }

            @Override
            public void onInterstitialShown(MoPubInterstitial interstitial) {
                ToastHelper.showMessage(MoPubInterstitialActivity.this, "onInterstitialShown");
            }

            @Override
            public void onInterstitialClicked(MoPubInterstitial interstitial) {
                ToastHelper.showMessage(MoPubInterstitialActivity.this, "onInterstitialClicked");
            }

            @Override
            public void onInterstitialDismissed(MoPubInterstitial interstitial) {
                ToastHelper.showMessage(MoPubInterstitialActivity.this, "onInterstitialDismissed");
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mMoPubInterstitial != null) {
            mMoPubInterstitial.destroy();
            mMoPubInterstitial = null;
        }
        super.onDestroy();
    }
}



