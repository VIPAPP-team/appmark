package com.vipapp.appmark2.item.editviewdialogitem.choose;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.editviewdialogitem.ChooseEditViewDialogItem;
import com.vipapp.appmark2.item.editviewdialogitem.MultipleChooseEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.text_style_chooser;

public class TextStyle extends ChooseEditViewDialogItem {
    @Override
    public ArrayList<Item<String>> getChoose() {
        return text_style_chooser;
    }

    @Override
    public int getImage() {
        return R.drawable.text_style_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.text_style);
    }

    @Override
    public String getAttrName() {
        return "android:textStyle";
    }

    @Override
    public boolean exists(View v) {
        return v instanceof TextView;
    }
}
