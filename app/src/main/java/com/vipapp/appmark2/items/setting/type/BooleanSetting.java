package com.vipapp.appmark2.items.setting.type;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Switch;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.items.SettingsType;
import com.vipapp.appmark2.items.SettingsItem;
import com.vipapp.appmark2.widget.TextView;

public class BooleanSetting extends SettingsType<SettingsItem<Boolean>> {
    public BooleanSetting() {
        super(R.layout.setting_boolean_default);
    }

    public void setupView(View v, SharedPreferences prefs, SettingsItem<Boolean> item) {
        TextView title = v.findViewById(R.id.title);
        TextView subtitle = v.findViewById(R.id.subtitle);
        Switch checkbox = v.findViewById(R.id.checkbox);
        item.default_value = item.default_value != null && item.default_value;
        checkbox.setChecked(prefs.getBoolean(item.getSettingName(), item.default_value));
        title.setText(item.getSettingTitle(v.getContext()));
        subtitle.setText(item.getSettingSubtitle(v.getContext()));
        v.setOnClickListener(view -> checkbox.setChecked(!checkbox.isChecked()));
        checkbox.setOnCheckedChangeListener((compoundButton, checked) -> prefs.edit().putBoolean(item.getSettingName(), checked).apply());
    }
}