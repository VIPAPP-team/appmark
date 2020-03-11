package com.vipapp.appmark2.item.setting.item;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.item.SettingsType;
import com.vipapp.appmark2.item.setting.type.BooleanSetting;
import com.vipapp.appmark2.util.wrapper.Str;

import static com.vipapp.appmark2.util.Const.SHOW_DESIGN_ERROR_PREF;

public class ShowDesignError extends SettingsItem<Boolean> {
    public ShowDesignError() {
        super(SHOW_DESIGN_ERROR_PREF, new BooleanSetting());
    }

    @Override
    public String getSettingSubtitle(Context context) {
        return context.getString(R.string.show_design_errors_description);
    }

    @Override
    public String getSettingTitle(Context context) {
        return context.getString(R.string.show_design_errors_title);
    }
}
