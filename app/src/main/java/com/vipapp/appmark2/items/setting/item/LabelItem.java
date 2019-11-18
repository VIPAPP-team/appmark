package com.vipapp.appmark2.items.setting.item;

import android.content.Context;
import androidx.annotation.StringRes;

import com.vipapp.appmark2.items.SettingsItem;
import com.vipapp.appmark2.items.setting.type.LabelSetting;
import com.vipapp.appmark2.utils.wrapper.Str;

public class LabelItem extends SettingsItem {

    private String title, value;

    public LabelItem(String title){
        super(null, new LabelSetting());
        this.title = title;
    }
    public LabelItem(@StringRes int title_res){
        super(null, new LabelSetting());
        this.title = Str.get(title_res);
    }
    public LabelItem(String title, String value) {
        super(null, new LabelSetting());
        this.title = title;
        this.value = value;
    }
    public LabelItem(@StringRes int title_res, String value){
        super(null, new LabelSetting());
        title = Str.get(title_res);
        this.value = value;
    }

    public String getSettingTitle(Context ctx) {
        return title;
    }
    public String getSettingSubtitle(Context ctx) {
        return value;
    }

}
