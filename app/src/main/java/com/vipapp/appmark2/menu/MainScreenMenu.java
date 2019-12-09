package com.vipapp.appmark2.menu;

import android.content.Context;
import com.vipapp.appmark2.holder.MainMenuHolder;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.wrapper.mActivity;

import java.util.ArrayList;

public class MainScreenMenu extends DefaultMenu<Item<String>, MainMenuHolder> {
    public ArrayList<Item<String>> list(Context context) {
        return Const.main_menu;
    }

    public void bind(MainMenuHolder vh, Item<String> item, int i) {
        vh.title.setText(item.getInstance());
        vh.title.setOnClickListener(view ->
                mActivity.get().onLoad(item.toOnLoadItem()));
    }
}