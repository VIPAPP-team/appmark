package com.vipapp.appmark2.item.editviewdialogitem.unit.no;

import android.view.View;
import android.widget.TextView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.unit.NumberEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class Lines extends NumberEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.lines_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.lines);
    }

    @Override
    public String getAttrName() {
        return "android:lines";
    }

    @Override
    public boolean exists(View v) {
        return v instanceof TextView;
    }
}
