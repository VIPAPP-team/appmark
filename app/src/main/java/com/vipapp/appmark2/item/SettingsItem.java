package com.vipapp.appmark2.item;

import android.content.Context;

public abstract class SettingsItem<T> {
    public T default_value = null;
    private String setting_name;
    private SettingsType setting_type;

    public abstract String getSettingSubtitle(Context context);

    public abstract String getSettingTitle(Context context);

    public SettingsItem(String setting_name, SettingsType setting_type) {
        this.setting_name = setting_name;
        this.setting_type = setting_type;
    }

    public String getSettingName() {
        return this.setting_name;
    }

    public void setSettingName(String setting_name) {
        this.setting_name = setting_name;
    }

    public SettingsType getSettingType() {
        return this.setting_type;
    }

    public void setSettingType(SettingsType setting_type) {
        this.setting_type = setting_type;
    }
}