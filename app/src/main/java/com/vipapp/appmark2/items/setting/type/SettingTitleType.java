package com.vipapp.appmark2.items.setting.type;

import android.content.SharedPreferences;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.items.SettingType;
import com.vipapp.appmark2.items.SettingsItem;
import com.vipapp.appmark2.widget.TextView;

public class SettingTitleType extends SettingType {
    public SettingTitleType() {
        super(R.layout.setting_title);
    }

    public void setupView(View v, SharedPreferences prefs, SettingsItem item) {
        TextView tv = v.findViewById(R.id.title);
        tv.setText(item.getSettingTitle(v.getContext()));
    }
}