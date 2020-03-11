package com.vipapp.appmark2.item.editviewdialogitem.unit.no;

import android.view.View;
import android.widget.LinearLayout;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.unit.NumberEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class Weight extends NumberEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.weight_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.weight);
    }

    @Override
    public String getAttrName() {
        return "android:layout_weight";
    }

    @Override
    public boolean exists(View v) {
        return v.getParent() instanceof LinearLayout;
    }
}
