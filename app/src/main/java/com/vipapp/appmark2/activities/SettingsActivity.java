package com.vipapp.appmark2.activities;

import android.os.Bundle;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.R;

public class SettingsActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
    }

    protected void onPause() {
        super.onPause();
        Const.initSettingsConst();
    }
}