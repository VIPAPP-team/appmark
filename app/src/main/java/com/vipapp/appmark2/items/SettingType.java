package com.vipapp.appmark2.items;

import android.content.SharedPreferences;
import androidx.annotation.LayoutRes;
import android.view.View;

public abstract class SettingType<SettingsItemType extends SettingsItem> {

    @LayoutRes
    private int xml_resource;

    public SettingType(@LayoutRes int xml_resource){
        this.xml_resource = xml_resource;
    }

    public abstract void setupView(View v, SharedPreferences prefs, SettingsItemType item);

    public int getXmlResource() {
        return xml_resource;
    }
    public void setXmlResource(int xml_resource) {
        this.xml_resource = xml_resource;
    }

}
