package com.vipapp.appmark2.item.setting.item;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.item.setting.type.pickable.SimpleIntSetting;
import com.vipapp.appmark2.util.wrapper.Str;

import static com.vipapp.appmark2.util.Const.DEFAULT_MAIN_EDITOR_FONT_SIZE;
import static com.vipapp.appmark2.util.Const.MAIN_EDITOR_FONT_SIZE_PREF;
import static com.vipapp.appmark2.util.Const.MAX_MAIN_EDITOR_FONT_SIZE;
import static com.vipapp.appmark2.util.Const.MIN_MAIN_EDITOR_FONT_SIZE;

public class MainEditorFontSize extends SettingsItem<Integer> {

    public MainEditorFontSize() {
        super(MAIN_EDITOR_FONT_SIZE_PREF, new SimpleIntSetting(MIN_MAIN_EDITOR_FONT_SIZE, MAX_MAIN_EDITOR_FONT_SIZE));
        this.default_value = DEFAULT_MAIN_EDITOR_FONT_SIZE;
    }

    public String getSettingTitle(Context ctx) {
        return Str.get(R.string.main_editor_font_size_title);
    }
    public String getSettingSubtitle(Context ctx) {
        return Str.get(R.string.main_editor_font_size_subtitle);
    }

}
