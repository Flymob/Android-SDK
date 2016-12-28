package com.flymob.sample.samples.native_ad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.flymob.sample.R;
import com.flymob.sample.utiles.ToastHelper;
import com.flymob.sdk.common.ads.FailResponse;
import com.flymob.sdk.common.ads.native_ad.FlyMobNativeAd;
import com.flymob.sdk.common.ads.native_ad.IFlyMobNativeAdListener;

import java.util.Locale;

public class NativeAdActivity extends AppCompatActivity {
    private static final int ZONE_ID = 841897;
    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;
    CheckBox mPreloadIcon;
    CheckBox mPreloadImage;
    ViewGroup mNativeAdPlace;
    FlyMobNativeAd mFlyMobNativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);


        mButtonLoad = findViewById(R.id.button_load);
        mButtonShow = findViewById(R.id.button_show);
        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setText(String.valueOf(ZONE_ID));
        mPreloadIcon = (CheckBox) findViewById(R.id.preload_icon);
        mPreloadImage = (CheckBox) findViewById(R.id.preload_image);
        mNativeAdPlace = (ViewGroup) findViewById(R.id.native_ad_place);

        initNativeAd();

        mPreloadIcon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFlyMobNativeAd.preloadIcon(mPreloadIcon.isChecked());
            }
        });

        mPreloadImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mFlyMobNativeAd.preloadImage(mPreloadImage.isChecked());
            }
        });

        loadNativeAd();

        mButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadNativeAd();
            }
        });

        mButtonShow.setVisibility(View.GONE);
    }

    private void loadNativeAd() {
        mFlyMobNativeAd.setZoneId(getZoneId());
        mFlyMobNativeAd.load();
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

    private void initNativeAd() {
        mFlyMobNativeAd = new FlyMobNativeAd(this, getZoneId());

        mFlyMobNativeAd.addListener(new IFlyMobNativeAdListener() {

            @Override
            public void loaded(FlyMobNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdActivity.this, "loaded");

                LayoutInflater layoutInflater = LayoutInflater.from(NativeAdActivity.this);
                ViewGroup nativeAdView = (ViewGroup) layoutInflater.inflate(R.layout.native_ad, null);
                TextView title = (TextView) nativeAdView.findViewById(R.id.title);
                TextView text = (TextView) nativeAdView.findViewById(R.id.text);
                TextView rating = (TextView) nativeAdView.findViewById(R.id.rating);
                Button openUrlButton = (Button) nativeAdView.findViewById(R.id.button_open_url);
                ImageView icon = (ImageView) nativeAdView.findViewById(R.id.icon);
                ImageView image = (ImageView) nativeAdView.findViewById(R.id.image);

                title.setText(mFlyMobNativeAd.getTitle());
                text.setText(mFlyMobNativeAd.getText());
                rating.setText(String.format(getString(R.string.rating), mFlyMobNativeAd.getRating()));
                openUrlButton.setText(mFlyMobNativeAd.getCta());

                mFlyMobNativeAd.displayIcon(icon);
                mFlyMobNativeAd.displayImage(image);

                //If you don't call registerView, impression and click will not work!
                mFlyMobNativeAd.registerView(openUrlButton);

                mNativeAdPlace.removeAllViews();
                mNativeAdPlace.addView(nativeAdView, new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

            }

            @Override
            public void failed(FlyMobNativeAd nativeAd, FailResponse response) {
                ToastHelper.showToast(NativeAdActivity.this, String.format(Locale.getDefault(), "failed: %d - %s", response.getStatusCode(), response.getResponseString()));
            }

            @Override
            public void shown(FlyMobNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdActivity.this, "shown");
            }

            @Override
            public void clickUrlOpened(FlyMobNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdActivity.this, "clickUrlOpened");
            }

            @Override
            public void expired(FlyMobNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdActivity.this, "expired");
                loadNativeAd();
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (mFlyMobNativeAd != null) {
            mFlyMobNativeAd.onDestroy();
            mFlyMobNativeAd = null;
        }
        super.onDestroy();
    }
}