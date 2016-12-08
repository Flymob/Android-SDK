package com.flymob.sample.samples.native_ad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.flymob.sample.R;
import com.flymob.sample.samples.native_ad.recycler.NewsAdapter;
import com.flymob.sample.samples.native_ad.recycler.elements.ElementBase;
import com.flymob.sample.samples.native_ad.recycler.elements.NativeAdElement;
import com.flymob.sample.samples.native_ad.recycler.elements.NewsElement;
import com.flymob.sample.utiles.ToastHelper;
import com.flymob.sdk.common.ads.FailResponse;
import com.flymob.sdk.common.ads.native_ad.IFlyMobNativeAdListener;
import com.flymob.sdk.common.ads.native_ad.FlyMobNativeAd;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class NativeAdRecyclerViewActivity extends AppCompatActivity {
    private static final int ITEM_COUNT = 10;
    private static final int AD_POSITION = 5;
    private static final int ZONE_ID = 613296;
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private List<ElementBase> mNews;
    private List<FlyMobNativeAd> mFlyMobNativeAds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_ad_recycler_view);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);

        mNews = new LinkedList<>();
        for (int i = 0; i < ITEM_COUNT; i++) {
            mNews.add(new NewsElement(String.format("%s news item", i + 1)));
        }

        mNewsAdapter = new NewsAdapter(this, mNews);

        mRecyclerView.setAdapter(mNewsAdapter);

        initNativeAd();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void initNativeAd() {
        int count = ITEM_COUNT / AD_POSITION;
        for (int i = 0; i < count; i++) {
            final int position = AD_POSITION * i;

            final FlyMobNativeAd flyMobNativeAd = new FlyMobNativeAd(this, ZONE_ID);
            mFlyMobNativeAds.add(flyMobNativeAd);
            flyMobNativeAd.addListener(new IFlyMobNativeAdListener() {
                @Override
                public void loaded(FlyMobNativeAd nativeAd) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "loaded");
                    mNews.add(position, new NativeAdElement(flyMobNativeAd));
                    mNewsAdapter.notifyItemInserted(position);
                }

                @Override
                public void failed(FlyMobNativeAd nativeAd, FailResponse response) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, String.format(Locale.getDefault(), "failed: %d - %s", response.getStatusCode(), response.getResponseString()));
                }

                @Override
                public void shown(FlyMobNativeAd nativeAd) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "shown");
                }

                @Override
                public void clickUrlOpened(FlyMobNativeAd nativeAd) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "clickUrlOpened");
                }

                @Override
                public void expired(FlyMobNativeAd nativeAd) {
                    ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "expired");
                }
            });
            flyMobNativeAd.load();
        }
    }

    @Override
    protected void onDestroy() {
        for (FlyMobNativeAd flyMobNativeAd : mFlyMobNativeAds) {
            flyMobNativeAd.onDestroy();
        }
        super.onDestroy();
    }
}