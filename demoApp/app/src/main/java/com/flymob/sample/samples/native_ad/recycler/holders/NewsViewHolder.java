/*
 * *****************************************
 *  * Copyright (c) 2017
 *  * Alexey Baskakov
 *  *
 *  * FlyMob
 *  * http://flymob.com
 *  *****************************************
 */

package com.flymob.sample.samples.native_ad.recycler.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.flymob.sample.R;
import com.flymob.sample.samples.native_ad.recycler.elements.NewsElement;

/**
 * Created by alexey on 06/11/15.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder {
    NewsElement mElement;
    TextView mTitle;

    public NewsViewHolder(View view) {
        super(view);
        mTitle = (TextView) view.findViewById(R.id.title);
    }

    public void setItem(NewsElement element) {
        mElement = element;
        mTitle.setText(element.getTitle());
    }

}
