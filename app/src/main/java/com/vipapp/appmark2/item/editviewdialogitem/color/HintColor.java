package com.vipapp.appmark2.item.editviewdialogitem.color;

import android.view.View;
import android.widget.EditText;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.ColorEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

public class HintColor extends ColorEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.text_color_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.hint_color);
    }

    @Override
    public String getAttrName() {
        return "android:textColorHint";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setHintTextColor", new Class[]{int.class});
    }
}
