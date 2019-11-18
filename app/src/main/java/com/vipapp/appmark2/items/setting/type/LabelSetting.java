package com.vipapp.appmark2.items.setting.type;

import android.content.SharedPreferences;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.items.SettingType;
import com.vipapp.appmark2.items.SettingsItem;
import com.vipapp.appmark2.widget.TextView;

public class LabelSetting extends SettingType<SettingsItem<String>> {
    public LabelSetting() {
        super(R.layout.setting_label);
    }

    public void setupView(View v, SharedPreferences prefs, SettingsItem<String> item) {
        TextView value = v.findViewById(R.id.value);
        TextView title = v.findViewById(R.id.title);
        title.setText(item.getSettingTitle(v.getContext()));
        value.setText(item.getSettingSubtitle(v.getContext()));
    }
}