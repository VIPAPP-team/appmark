package com.vipapp.appmark2.item.editviewdialogitem.choose.bool;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.choose.BooleanEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

public class Checked extends BooleanEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.checked_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.checked);
    }

    @Override
    public String getAttrName() {
        return "android:checked";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setChecked", new Class[]{boolean.class});
    }
}
