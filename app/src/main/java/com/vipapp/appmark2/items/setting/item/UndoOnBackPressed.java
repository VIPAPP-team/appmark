package com.vipapp.appmark2.items.setting.item;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.activities.BaseActivity;
import com.vipapp.appmark2.items.SettingType;
import com.vipapp.appmark2.items.SettingsItem;
import com.vipapp.appmark2.items.setting.type.BooleanSetting;

import static com.vipapp.appmark2.utils.Const.DEFAULT_ON_BACK_PRESSED;
import static com.vipapp.appmark2.utils.Const.UNDO_ON_BACK_PRESSED;

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
