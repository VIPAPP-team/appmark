package com.vipapp.appmark2.item.editviewdialogitem.string;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.StringEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class Id extends StringEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.id_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.id);
    }

    @Override
    public String getAttrName() {
        return "android:id";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }
}
