package com.flymob.sample.samples.native_ad;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.flymob.sample.R;
import com.flymob.sample.samples.native_ad.recycler.NewsAdapter;
import com.flymob.sample.samples.native_ad.recycler.elements.ElementBase;
import com.flymob.sample.samples.native_ad.recycler.elements.NativeAdElement;
import com.flymob.sample.samples.native_ad.recycler.elements.NewsElement;
import com.flymob.sample.utiles.ToastHelper;
import com.flymob.sample.utiles.recycler_view.ItemClickSupport;
import com.flymob.sdk.common.ads.FailResponse;
import com.flymob.sdk.common.ads.native_ad.IFlyMobNativeAdListener;
import com.flymob.sdk.common.ads.native_ad.FlyMobNativeAd;

import java.util.LinkedList;
import java.util.List;

public class NativeAdRecyclerViewActivity extends AppCompatActivity {
    private static final int ITEM_COUNT = 35;
    private static final int AD_POSITION_INTERVAL = 8;
    private static final int ZONE_ID = 613296;
    private Toolbar mToolBar;
    private RecyclerView mRecyclerView;
    private NewsAdapter mNewsAdapter;
    private FlyMobNativeAd mFlyMobNativeAd;
    private List<ElementBase> mNews;
    private boolean mFirstLoad = true;

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

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                if (position != -1) {
                    ElementBase elementBase = mNewsAdapter.getItem(position);
                    if (elementBase instanceof NativeAdElement) {
                        NativeAdElement nativeAdElement = (NativeAdElement) elementBase;
                        FlyMobNativeAd nativeAd = nativeAdElement.getFlyMobNativeAd();
                        if (nativeAd.canOpenClickUrl()) {
                            nativeAd.openClickUrl();
                        }
                    }
                }
            }
        });

        initNativeAd();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadNativeAd();
    }

    private void loadNativeAd() {
        mFlyMobNativeAd.load();
    }

    private void initNativeAd() {
        mFlyMobNativeAd = new FlyMobNativeAd(this, ZONE_ID);
        mFlyMobNativeAd.preloadImage(false);

        mFlyMobNativeAd.addListener(new IFlyMobNativeAdListener() {
            @Override
            public void loaded(FlyMobNativeAd nativeAd) {
                ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "loaded");
                int i = AD_POSITION_INTERVAL - 1;
                while (i < mNews.size()) {
                    if (mFirstLoad) {
                        mNews.add(i, new NativeAdElement(mFlyMobNativeAd));
                        mNewsAdapter.notifyItemInserted(i);
                    } else {
                        mNews.set(i, new NativeAdElement(mFlyMobNativeAd));
                        mNewsAdapter.notifyItemChanged(i);
                    }
                    i += AD_POSITION_INTERVAL;
                }
                if (mFirstLoad) {
                    mFirstLoad = false;
                }
            }

            @Override
            public void failed(FlyMobNativeAd nativeAd, FailResponse response) {
                ToastHelper.showToast(NativeAdRecyclerViewActivity.this, "failed " + response.getResponseString());
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



