package com.vipapp.appmark2.items.setting.item;

import android.view.View;
import com.vipapp.appmark2.items.SettingType;
import com.vipapp.appmark2.items.SettingsItem;

public abstract class LoadableItem<T> extends SettingsItem<T> {
    public LoadableItem(SettingType settingType) {
        super(null, settingType);
    }

    public abstract void setup(View view);
}