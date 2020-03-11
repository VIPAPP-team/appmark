package com.vipapp.appmark2.item.editviewdialogitem.unit.sp;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.SizeEditViewDialogItem;
import com.vipapp.appmark2.item.editviewdialogitem.unit.SpUnitEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.TextView;

public class TextSize extends SpUnitEditViewDialogItem {
    @Override
    public String getDefaultUnit() {
        return "sp";
    }

    @Override
    public int getImage() {
        return R.drawable.text_size_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.text_size);
    }

    @Override
    public String getAttrName() {
        return "android:textSize";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setTextSize", new Class[]{int.class, float.class});
    }
}
