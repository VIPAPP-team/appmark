package com.vipapp.appmark2.item.setting.item;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.item.setting.type.BooleanSetting;
import com.vipapp.appmark2.util.wrapper.Str;

import static com.vipapp.appmark2.util.Const.DEFAULT_HIGHLIGHT_DEBUG;
import static com.vipapp.appmark2.util.Const.HIGHLIGHT_DEBUG_PREF;

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
