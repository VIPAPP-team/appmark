package com.vipapp.appmark2.activity;

import com.vipapp.appmark2.util.Const;
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