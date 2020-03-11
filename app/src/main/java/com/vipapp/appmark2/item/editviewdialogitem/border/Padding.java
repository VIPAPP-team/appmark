package com.vipapp.appmark2.item.editviewdialogitem.border;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.BorderEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class Padding extends BorderEditViewDialogItem {
    @Override
    public String getAllAttrName() {
        return "android:padding";
    }

    @Override
    public String getLeftAttrName() {
        return "android:paddingLeft";
    }

    @Override
    public String getRightAttrName() {
        return "android:paddingRight";
    }

    @Override
    public String getTopAttrName() {
        return "android:paddingTop";
    }

    @Override
    public String getBottomAttrName() {
        return "android:paddingBottom";
    }

    @Override
    public int getImage() {
        return R.drawable.padding_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.padding);
    }

    @Override
    public String getAttrName() {
        return "android:padding";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }
}
