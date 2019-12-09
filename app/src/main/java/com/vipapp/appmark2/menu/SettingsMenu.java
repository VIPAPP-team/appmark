package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.holder.EmptyHolder;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.wrapper.mLayoutInflater;
import com.vipapp.appmark2.util.wrapper.mSharedPreferences;

import java.util.ArrayList;

public class SettingsMenu extends DefaultMenu<SettingsItem, EmptyHolder> {

    public ArrayList<SettingsItem> list(Context context) {
        return Const.settingsItems;
    }

    public void bind(EmptyHolder vh, SettingsItem item, int i) {
        // view setup
        //noinspection unchecked
        item.getSettingType().setupView(vh.getView(), mSharedPreferences.get(), item);
    }

    @Override
    public EmptyHolder getViewHolder(ViewGroup parent, int itemType) {
        return new EmptyHolder(getSettingView(itemType, parent));
    }

    private View getSettingView(int pos, ViewGroup parent){
        SettingsItem item = Const.settingsItems.get(pos);
        // getting and inflating xml res
        int xml_res = item.getSettingType().getXmlResource();
        return mLayoutInflater.get().inflate(xml_res, parent, false);
    }

    @Override
    public int getItemViewType(int pos) {
        return pos;
    }

}
