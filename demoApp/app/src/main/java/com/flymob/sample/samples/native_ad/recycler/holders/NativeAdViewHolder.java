package com.flymob.sample.samples.native_ad.recycler.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flymob.sample.R;
import com.flymob.sample.samples.native_ad.recycler.elements.NativeAdElement;
import com.flymob.sdk.common.ads.native_ad.FlyMobNativeAd;

/**
 * Created by alexey on 06/11/15.
 */
public class NativeAdViewHolder extends RecyclerView.ViewHolder {
    NativeAdElement mElement;

    TextView mTitle;
    TextView mText;
    ImageView mIcon;

    public NativeAdViewHolder(View view) {
        super(view);
        mTitle = (TextView) view.findViewById(R.id.title);
        mText = (TextView) view.findViewById(R.id.text);
        mIcon = (ImageView) view.findViewById(R.id.icon);
    }

    public void setItem(NativeAdElement element) {
        //If you you re-use the view to show different ads, make sure to call unregisterView
        //before registering the same view with a different instance of FlyMobNativeAd
        if(mElement!=null){
            FlyMobNativeAd nativeAd = mElement.getFlyMobNativeAd();
            if(nativeAd!=null){
                nativeAd.unregisterView(itemView);
            }
        }

        mElement = element;
        FlyMobNativeAd flymobNativeAd = element.getFlyMobNativeAd();
        mTitle.setText(flymobNativeAd.getTitle());
        mText.setText(flymobNativeAd.getText());
        flymobNativeAd.displayIcon(mIcon);
        //If you don't call registerView, impression and click will not work!
        flymobNativeAd.registerView(itemView);
    }
}
