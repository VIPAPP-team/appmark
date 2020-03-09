package com.vipapp.appmark2.item.editviewdialogitem.string;

import android.view.View;
import android.widget.EditText;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.StringEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

public class Hint extends StringEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.hint_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.hint);
    }

    @Override
    public String getAttrName() {
        return "android:hint";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setHint", new Class[]{CharSequence.class});
    }
}
