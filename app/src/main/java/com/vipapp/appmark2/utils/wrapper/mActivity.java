package com.vipapp.appmark2.utils.wrapper;

import com.vipapp.appmark2.activities.BaseActivity;
import com.vipapp.appmark2.utils.ContextUtils;

@SuppressWarnings("deprecation")
public class mActivity {
    public static BaseActivity get(){
        return ContextUtils.activity;
    }
}
