package com.vipapp.appmark2.item.editviewdialogitem.string;

import android.graphics.Bitmap;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.StringEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.TextView;

public class Text extends StringEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.text_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.text);
    }

    @Override
    public String getAttrName() {
        return "android:text";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setText", new Class[]{CharSequence.class});
    }
}
