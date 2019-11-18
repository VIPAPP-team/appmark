package com.vipapp.appmark2.items;

import android.content.Context;

public abstract class SettingsItem<T> {
    public T default_value = null;
    private String setting_name;
    private SettingType setting_type;

    public abstract String getSettingSubtitle(Context context);

    public abstract String getSettingTitle(Context context);

    public SettingsItem(String setting_name, SettingType setting_type) {
        this.setting_name = setting_name;
        this.setting_type = setting_type;
    }

    public String getSettingName() {
        return this.setting_name;
    }

    public void setSettingName(String setting_name) {
        this.setting_name = setting_name;
    }

    public SettingType getSettingType() {
        return this.setting_type;
    }

    public void setSettingType(SettingType setting_type) {
        this.setting_type = setting_type;
    }
}