package com.vipapp.appmark2.item.editviewdialogitem.choose.bool;

import android.view.View;
import android.widget.TextView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.choose.BooleanEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class SingleLine extends BooleanEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.single_line_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.single_line);
    }

    @Override
    public String getAttrName() {
        return "android:singleLine";
    }

    @Override
    public boolean exists(View v) {
        return v instanceof TextView;
    }
}
