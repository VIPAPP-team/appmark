package com.vipapp.appmark2.utils;

import com.vipapp.appmark2.utils.wrapper.mContext;

import androidx.annotation.StringRes;

public class Toast extends Utils {

    private static android.widget.Toast previous;

    public static void show(String message) {
        Thread.ui(() -> {

            if(previous != null)
                previous.cancel();

            previous = android.widget.Toast.makeText(mContext.get(),
                    message, android.widget.Toast.LENGTH_SHORT);


            previous.show();
        });
    }

    public static void show(@StringRes int res){
        show(ContextUtils.activity.getString(res));
    }

    public static void error(String place, Throwable throwable){
        show("Some error here: " + place + ";\n" + throwable.toString());
    }

}
