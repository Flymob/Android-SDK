/*
 * *****************************************
 *  * Copyright (c) 2017
 *  * Alexey Baskakov
 *  *
 *  * FlyMob
 *  * http://flymob.com
 *  *****************************************
 */

package com.flymob.sample.recycler;

import android.content.Context;

/**
 * Created by a.baskakov on 13/04/16.
 */
public abstract class MenuElement {

    String mTitle;

    public MenuElement(String title) {
        this.mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public abstract void click(Context context);

}
