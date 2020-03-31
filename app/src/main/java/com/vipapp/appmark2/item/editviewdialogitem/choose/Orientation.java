package com.vipapp.appmark2.item.editviewdialogitem.choose;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.editviewdialogitem.ChooseEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.orientation_chooser;

public class Orientation extends ChooseEditViewDialogItem {

    @Override
    public ArrayList<Item<String>> getChoose() {
        return orientation_chooser;
    }

    @Override
    public int getImage() {
        return R.drawable.orientation_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.orientation);
    }

    @Override
    public String getAttrName() {
        return "android:orientation";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setOrientation", new Class[]{int.class});
    }
}
