package com.vipapp.appmark2.item.editviewdialogitem.choose;

import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.editviewdialogitem.ChooseEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.scale_type_choose;

public class ScaleType extends ChooseEditViewDialogItem {
    @Override
    public ArrayList<Item<String>> getChoose() {
        return scale_type_choose;
    }

    @Override
    public int getImage() {
        return R.drawable.scale_type_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.scale_type);
    }

    @Override
    public String getAttrName() {
        return "android:scaleType";
    }

    @Override
    public boolean exists(View v) {
        return v instanceof ImageView;
    }
}
