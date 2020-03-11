package com.vipapp.appmark2.item.editviewdialogitem.unit.dp;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.SizeEditViewDialogItem;
import com.vipapp.appmark2.item.editviewdialogitem.unit.DpUnitEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

public class TranslationY extends DpUnitEditViewDialogItem {
    @Override
    public int getImage() {
        return R.drawable.translation_y_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.translation_y);
    }

    @Override
    public String getAttrName() {
        return "android:translationY";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }

}
