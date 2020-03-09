package com.vipapp.appmark2.item.editviewdialogitem.choose.bool;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.choose.BooleanEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class Enabled extends BooleanEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.enabled_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.enabled);
    }

    @Override
    public String getAttrName() {
        return "android:enabled";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }
}
