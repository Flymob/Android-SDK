/*
 * *****************************************
 *  * Copyright (c) 2017
 *  * Alexey Baskakov
 *  *
 *  * FlyMob
 *  * http://flymob.com
 *  *****************************************
 */

package com.flymob.sample;

import android.content.Context;

import com.flymob.sample.recycler.MenuElement;
import com.flymob.sample.recycler.OpenActivityMenuElement;
import com.flymob.sample.samples.interstitial.AdMobInterstitialActivity;
import com.flymob.sample.samples.interstitial.InterstitialActivity;
import com.flymob.sample.samples.interstitial.MoPubInterstitialActivity;
import com.flymob.sample.samples.interstitial.fragment.FragmentInterstitialActivity;
import com.flymob.sample.samples.native_ad.NativeAdActivity;
import com.flymob.sample.samples.native_ad.NativeAdRecyclerViewActivity;
import com.flymob.sample.samples.video.RewardedVideoActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by a.baskakov on 08/08/16.
 */
public class MenuHelper {
    public static List<MenuElement> getMenu(Context context) {
        List<MenuElement> menuElements = new LinkedList<>();
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.interstitial_sample), InterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.fragment_interstitial_sample), FragmentInterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.rewarded_video_sample), RewardedVideoActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.ad_mob_interstitial_sample), AdMobInterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.mo_pub_interstitial_sample), MoPubInterstitialActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.native_ad_sample), NativeAdActivity.class));
        menuElements.add(new OpenActivityMenuElement(context.getString(R.string.native_ad_recycler_view_sample), NativeAdRecyclerViewActivity.class));
        return menuElements;
    }

}
