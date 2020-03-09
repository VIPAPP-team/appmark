package com.vipapp.appmark2.item.editviewdialogitem.multiplechoose;

import android.view.View;
import android.widget.LinearLayout;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.util.wrapper.Str;

public class LayoutGravity extends Gravity {
    @Override
    public String getTitle() {
        return Str.get(R.string.layout_gravity);
    }

    @Override
    public String getAttrName() {
        return "android:layout_gravity";
    }

    @Override
    public boolean exists(View v) {
        return v.getParent() instanceof LinearLayout;
    }
}
