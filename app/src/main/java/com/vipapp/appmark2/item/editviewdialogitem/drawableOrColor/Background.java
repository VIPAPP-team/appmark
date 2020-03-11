package com.vipapp.appmark2.item.editviewdialogitem.drawableOrColor;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.DrawableOrColorEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class Background extends DrawableOrColorEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.background_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.background);
    }

    @Override
    public String getAttrName() {
        return "android:background";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }
}
