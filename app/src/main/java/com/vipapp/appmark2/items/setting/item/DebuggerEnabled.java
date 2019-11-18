package com.vipapp.appmark2.items.setting.item;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.items.SettingsItem;
import com.vipapp.appmark2.items.setting.type.BooleanSetting;
import com.vipapp.appmark2.utils.wrapper.Str;

import static com.vipapp.appmark2.utils.Const.DEBUGGER_ENABLED_PREF;
import static com.vipapp.appmark2.utils.Const.DEFAULT_DEBUGGER_ENABLED;

public class DebuggerEnabled extends SettingsItem<Boolean> {

    public DebuggerEnabled() {
        super(DEBUGGER_ENABLED_PREF, new BooleanSetting());
        this.default_value = DEFAULT_DEBUGGER_ENABLED;
    }

    public String getSettingTitle(Context ctx) {
        return Str.get(R.string.debugger_enabled_title);
    }
    public String getSettingSubtitle(Context ctx) {
        return Str.get(R.string.debugger_enabled_subtitle);
    }
}
