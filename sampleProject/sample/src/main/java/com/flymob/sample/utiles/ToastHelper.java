package com.flymob.sample.utiles;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by a.baskakov on 28/04/16.
 */
public class ToastHelper {
    public static void showMessage(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 70);
        toast.show();
    }
}
