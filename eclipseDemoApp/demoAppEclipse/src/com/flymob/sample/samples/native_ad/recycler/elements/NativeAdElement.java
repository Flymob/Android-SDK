package com.flymob.sample.samples.native_ad.recycler.elements;

import com.flymob.sdk.common.ads.native_ad.FlyMobNativeAd;

/**
 * Created by a.baskakov on 04/05/16.
 */
public class NativeAdElement extends ElementBase {
    FlyMobNativeAd mFlyMobNativeAd;

    public NativeAdElement(FlyMobNativeAd flymobNativeAd) {
        this.mFlyMobNativeAd = flymobNativeAd;
    }

    public FlyMobNativeAd getFlyMobNativeAd() {
        return mFlyMobNativeAd;
    }
}
