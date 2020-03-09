package com.vipapp.appmark2.item.editviewdialogitem.multiplechoose;

import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.MultipleChooseEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.gravity_chooser;

public class Gravity extends MultipleChooseEditViewDialogItem {
    @Override
    public ArrayList<String> getChoose() {
        return gravity_chooser;
    }

    @Override
    public int getImage() {
        return R.drawable.gravity_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.gravity);
    }

    @Override
    public String getAttrName() {
        return "android:gravity";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setGravity", new Class[]{int.class});
    }
}
