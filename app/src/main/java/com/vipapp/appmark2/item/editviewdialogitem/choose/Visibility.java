package com.vipapp.appmark2.item.editviewdialogitem.choose;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.editviewdialogitem.ChooseEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.visibility_chooser;

public class Visibility extends ChooseEditViewDialogItem {
    @Override
    public ArrayList<Item<String>> getChoose() {
        return visibility_chooser;
    }

    @Override
    public int getImage() {
        return R.drawable.visibility_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.visibility);
    }

    @Override
    public String getAttrName() {
        return "android:visibility";
    }

    @Override
    public boolean exists(View v) {
        return true;
    }
}
