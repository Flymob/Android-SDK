package com.flymob.sample.samples.interstitial.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.flymob.sample.R;
import com.flymob.sdk.common.ads.FailResponse;
import com.flymob.sdk.common.ads.interstitial.IFlyMobInterstitialListener;
import com.flymob.sdk.common.ads.interstitial.FlyMobInterstitial;
import com.flymob.sdk.internal.server.response.impl.ErrorResponse;
import com.flymob.sample.utiles.ToastHelper;

import java.util.Locale;

/**
 * Created by a.baskakov on 15/04/16.
 */
public class InterstitialFragment extends Fragment {
    private static final int ZONE_ID = 934102;
    View mButtonLoad;
    View mButtonShow;
    EditText mEditText;

    FlyMobInterstitial mFlyMobInterstitial;
    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(
                R.layout.fragment_interstitial,
                container,
                false
        );

        mButtonLoad = mRootView.findViewById(R.id.button_load);
        mButtonShow = mRootView.findViewById(R.id.button_show);
        mEditText = (EditText) mRootView.findViewById(R.id.edit_text);
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

        return mRootView;
    }

    private void loadInterstitial() {
        mButtonShow.setEnabled(false);
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
        mFlyMobInterstitial = new FlyMobInterstitial(getActivity(), getZoneId());
        mFlyMobInterstitial.addListener(new IFlyMobInterstitialListener() {
            @Override
            public void loaded(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "loaded");
                mButtonShow.setEnabled(true);
            }

            @Override
            public void failed(FlyMobInterstitial interstitial, FailResponse response) {
                ToastHelper.showToast(getActivity(), String.format(Locale.getDefault(), "failed: %d - %s", response.getStatusCode(), response.getResponseString()));
            }

            @Override
            public void shown(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "shown");
            }

            @Override
            public void clicked(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "clicked");
            }

            @Override
            public void closed(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "closed");
            }

            @Override
            public void expired(FlyMobInterstitial interstitial) {
                ToastHelper.showToast(getActivity(), "expired");
                loadInterstitial();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mFlyMobInterstitial != null) {
            mFlyMobInterstitial.onDestroy();
            mFlyMobInterstitial = null;
        }
    }

}
