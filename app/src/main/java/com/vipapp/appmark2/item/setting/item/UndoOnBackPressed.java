package com.vipapp.appmark2.item.setting.item;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.item.setting.type.BooleanSetting;

import static com.vipapp.appmark2.util.Const.DEFAULT_ON_BACK_PRESSED;
import static com.vipapp.appmark2.util.Const.UNDO_ON_BACK_PRESSED;

public class UndoOnBackPressed extends SettingsItem<Boolean> {

    public UndoOnBackPressed() {
        super(UNDO_ON_BACK_PRESSED, new BooleanSetting());
        this.default_value = DEFAULT_ON_BACK_PRESSED;
    }

    public String getSettingTitle(Context ctx) {
        return ctx.getString(R.string.undo_on_back_title);
    }
    public String getSettingSubtitle(Context ctx) {
        return ctx.getString(R.string.undo_on_back_subtitle);
    }

}
