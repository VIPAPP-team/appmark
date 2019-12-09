package com.vipapp.appmark2.util;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

import com.vipapp.appmark2.util.wrapper.mContext;

public class FontUtils {
    public static void setFont(String font_name, TextView widget) {
        AssetManager assets = mContext.get().getAssets();
        String stringBuilder = "fonts/" + font_name;
        widget.setTypeface(Typeface.createFromAsset(assets, stringBuilder), widget.getTypeface().getStyle());
    }
}