package com.vipapp.appmark2.items.setting.item;

import android.view.View;
import com.vipapp.appmark2.items.SettingsType;
import com.vipapp.appmark2.items.SettingsItem;

@SuppressWarnings("WeakerAccess")
public abstract class LoadableItem<T> extends SettingsItem<T> {
    public LoadableItem(SettingsType settingType) {
        super(null, settingType);
    }

    public abstract void setup(View view);
}