package com.flymob.sample.recycler;

import android.content.Context;
import android.content.Intent;

/**
 * Created by a.baskakov on 13/04/16.
 */
public class OpenActivityMenuElement extends MenuElement {
    Class mActivityClass;

    public OpenActivityMenuElement(String title, Class activityClass) {
        super(title);
        this.mActivityClass = activityClass;
    }

    @Override
    public void click(Context context) {
        Intent intent = new Intent(context, mActivityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
