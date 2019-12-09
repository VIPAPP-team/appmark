package com.vipapp.appmark2.item.setting.item;

import android.view.View;
import com.vipapp.appmark2.item.SettingsType;
import com.vipapp.appmark2.item.SettingsItem;

@SuppressWarnings("WeakerAccess")
public abstract class LoadableItem<T> extends SettingsItem<T> {
    public LoadableItem(SettingsType settingType) {
        super(null, settingType);
    }

    public abstract void setup(View view);
}