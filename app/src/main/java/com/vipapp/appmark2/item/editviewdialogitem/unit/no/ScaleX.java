package com.vipapp.appmark2.item.editviewdialogitem.unit.no;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.unit.NumberEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class ScaleX extends NumberEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.scale_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.scale_x);
    }

    @Override
    public String getAttrName() {
        return "android:scaleX";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }
}
