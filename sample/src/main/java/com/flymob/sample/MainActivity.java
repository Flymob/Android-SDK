package com.flymob.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.flymob.sample.recycler.MenuAdapter;
import com.flymob.sample.recycler.MenuElement;
import com.flymob.sample.recycler.OpenActivityMenuElement;
import com.flymob.sample.samples.interstitial.InterstitialActivity;
import com.flymob.sample.samples.interstitial.MoPubInterstitialActivity;
import com.flymob.sample.samples.interstitial.fragment.FragmentInterstitialActivity;
import com.flymob.sample.samples.native_ad.NativeAdActivity;
import com.flymob.sample.samples.native_ad.NativeAdRecyclerViewActivity;
import com.flymob.sample.utiles.recycler_view.ItemClickSupport;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolBar;
    RecyclerView mRecyclerView;
    MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);

        List<MenuElement> menuElements = new LinkedList<>();
        menuElements.add(new OpenActivityMenuElement(getString(R.string.interstitial_sample), InterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(getString(R.string.fragment_interstitial_sample), FragmentInterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(getString(R.string.mo_pub_interstitial_sample), MoPubInterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(getString(R.string.native_ad_sample), NativeAdActivity.class));
        menuElements.add(new OpenActivityMenuElement(getString(R.string.native_ad_recycler_view_sample), NativeAdRecyclerViewActivity.class));

        mMenuAdapter = new MenuAdapter(this, menuElements);
        mRecyclerView.setAdapter(mMenuAdapter);

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position, long id) {
                if (position != -1) {
                    MenuElement menuElement = mMenuAdapter.getItem(position);
                    menuElement.click(MainActivity.this);
                }
            }
        });

    }
}



