package com.vipapp.appmark2.item.editviewdialogitem.unit.no;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.unit.NumberEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

public class WeightSum extends NumberEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.weight_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.weight_sum);
    }

    @Override
    public String getAttrName() {
        return "android:weightSum";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setWeightSum", new Class[]{float.class});
    }
}
