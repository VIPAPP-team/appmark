package com.vipapp.appmark2.item.editviewdialogitem.drawable;

import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.DrawableEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class Src extends DrawableEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.src_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.src);
    }

    @Override
    public String getAttrName() {
        return "android:src";
    }

    @Override
    public boolean exists(View v) {
        return v instanceof ImageView;
    }
}
