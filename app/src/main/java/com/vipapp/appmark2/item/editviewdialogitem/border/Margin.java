package com.vipapp.appmark2.item.editviewdialogitem.border;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.BorderEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class Margin extends BorderEditViewDialogItem {
    @Override
    public String getAllAttrName() {
        return "android:layout_margin";
    }

    @Override
    public String getLeftAttrName() {
        return "android:layout_marginLeft";
    }

    @Override
    public String getRightAttrName() {
        return "android:layout_marginRight";
    }

    @Override
    public String getTopAttrName() {
        return "android:layout_marginTop";
    }

    @Override
    public String getBottomAttrName() {
        return "android:layout_marginBottom";
    }

    @Override
    public int getImage() {
        return R.drawable.margin_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.margin);
    }

    @Override
    public String getAttrName() {
        return "android:layout_margin";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }
}
