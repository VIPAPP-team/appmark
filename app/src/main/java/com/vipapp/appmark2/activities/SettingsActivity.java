package com.vipapp.appmark2.activities;

import android.os.Bundle;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.R;

public class SettingsActivity extends BaseActivity {

    @Override
    public Integer onCreateView() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Const.initSettingsConst();
    }
}