package com.vipapp.appmark2.items.setting.type;

import android.content.SharedPreferences;
import android.view.View;
import com.vipapp.appmark2.items.SettingType;
import com.vipapp.appmark2.items.setting.item.LoadableItem;

public class LoadableSettingType extends SettingType<LoadableItem> {
    public LoadableSettingType(int xml_resource) {
        super(xml_resource);
    }
    public void setupView(View v, SharedPreferences prefs, LoadableItem item) {
        item.setup(v);
    }
}