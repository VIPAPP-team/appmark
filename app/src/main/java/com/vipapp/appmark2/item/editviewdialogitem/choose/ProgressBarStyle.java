package com.vipapp.appmark2.item.editviewdialogitem.choose;

import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.editviewdialogitem.ChooseEditViewDialogItem;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.progress_bar_style_choose;

public class ProgressBarStyle extends ChooseEditViewDialogItem {
    @Override
    public ArrayList<Item<String>> getChoose() {
        return progress_bar_style_choose;
    }

    @Override
    public int getImage() {
        return R.drawable.progress_bar_style_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.progress_bar_style);
    }

    @Override
    public String getAttrName() {
        return "android:style";
    }

    @Override
    public boolean exists(View v) {
        return v instanceof ProgressBar;
    }
}
