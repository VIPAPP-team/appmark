package com.vipapp.appmark2.item.setting.item;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.item.setting.type.pickable.SimpleIntSetting;
import com.vipapp.appmark2.util.wrapper.Str;

import static com.vipapp.appmark2.util.Const.DEFAULT_MAX_LINES_IN_TEXT_EDITOR;
import static com.vipapp.appmark2.util.Const.MAX_LINES_IN_TEXT_EDITOR_PREF;
import static com.vipapp.appmark2.util.Const.MAX_MAX_LINES_IN_TEXT_EDITOR;
import static com.vipapp.appmark2.util.Const.MIN_MAX_LINES_IN_TEXT_EDITOR;

public class MaxLinesInEditor extends SettingsItem<Integer> {

    public MaxLinesInEditor() {
        super(MAX_LINES_IN_TEXT_EDITOR_PREF, new SimpleIntSetting(MIN_MAX_LINES_IN_TEXT_EDITOR, MAX_MAX_LINES_IN_TEXT_EDITOR));
        this.default_value = DEFAULT_MAX_LINES_IN_TEXT_EDITOR;
    }

    @Override
    public String getSettingTitle(Context ctx) {
        return Str.get(R.string.max_lines_in_text_editor);
    }

    @Override
    public String getSettingSubtitle(Context ctx) {
        return Str.get(R.string.max_lines_in_editor_description);
    }
}
