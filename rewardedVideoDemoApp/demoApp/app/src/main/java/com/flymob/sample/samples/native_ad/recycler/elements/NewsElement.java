/*
 * *****************************************
 *  * Copyright (c) 2017
 *  * Alexey Baskakov
 *  *
 *  * FlyMob
 *  * http://flymob.com
 *  *****************************************
 */

package com.flymob.sample.samples.native_ad.recycler.elements;

/**
 * Created by a.baskakov on 04/05/16.
 */
public class NewsElement extends ElementBase {
    private String mTitle;

    public NewsElement(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitle() {
        return mTitle;
    }
}
