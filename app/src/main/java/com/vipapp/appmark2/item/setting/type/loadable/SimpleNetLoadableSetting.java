package com.vipapp.appmark2.item.setting.type.loadable;

import android.content.SharedPreferences;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.setting.item.LoadableItem;
import com.vipapp.appmark2.item.setting.type.LoadableSettingType;
import com.vipapp.appmark2.widget.TextView;

public class SimpleNetLoadableSetting extends LoadableSettingType {
    public SimpleNetLoadableSetting() {
        super(R.layout.setting_label);
    }

    public void setupView(View v, SharedPreferences prefs, LoadableItem item) {
        super.setupView(v, prefs, item);
        TextView text = v.findViewById(R.id.title);
        text.setText(item.getSettingTitle(v.getContext()));
    }
}