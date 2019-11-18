package com.vipapp.appmark2.items.setting.item;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.items.SettingType;
import com.vipapp.appmark2.items.SettingsItem;
import com.vipapp.appmark2.items.setting.type.BooleanSetting;
import com.vipapp.appmark2.utils.wrapper.Str;

import static com.vipapp.appmark2.utils.Const.DEFAULT_HIGHLIGHT_DEBUG;
import static com.vipapp.appmark2.utils.Const.HIGHLIGHT_DEBUG_PREF;

public class ShowHighlightUpdate extends SettingsItem<Boolean> {

    public ShowHighlightUpdate() {
        super(HIGHLIGHT_DEBUG_PREF, new BooleanSetting());
        this.default_value = DEFAULT_HIGHLIGHT_DEBUG;
    }

    public String getSettingTitle(Context ctx) {
        return Str.get(R.string.highlight_debug);
    }
    public String getSettingSubtitle(Context ctx) {
        return Str.get(R.string.highlight_debug_description);
    }

}
