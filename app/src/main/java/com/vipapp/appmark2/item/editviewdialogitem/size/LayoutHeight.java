package com.vipapp.appmark2.item.editviewdialogitem.size;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.SizeEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class LayoutHeight extends SizeEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.height_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.layout_height);
    }

    @Override
    public String getAttrName() {
        return "android:layout_height";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }

    @Override
    public String getDefaultUnit() {
        return "dp";
    }
}
