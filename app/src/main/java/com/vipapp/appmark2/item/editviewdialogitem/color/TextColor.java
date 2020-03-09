package com.vipapp.appmark2.item.editviewdialogitem.color;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.ColorEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

public class TextColor extends ColorEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.text_color_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.text_color);
    }

    @Override
    public String getAttrName() {
        return "android:textColor";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setTextColor", new Class[]{int.class});
    }
}
