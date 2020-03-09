package com.vipapp.appmark2.item.editviewdialogitem.choose.bool;

import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.choose.BooleanEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

public class Indeterminate extends BooleanEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.indeterminate_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.indeterminate);
    }

    @Override
    public String getAttrName() {
        return "android:indeterminate";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setIndeterminate", new Class[]{boolean.class});
    }
}
