package com.vipapp.appmark2.item.editviewdialogitem.unit.no;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.unit.NumberEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

public class Progress extends NumberEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.numbers_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.progress);
    }

    @Override
    public String getAttrName() {
        return "android:progress";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setProgress", new Class[]{int.class});
    }
}
