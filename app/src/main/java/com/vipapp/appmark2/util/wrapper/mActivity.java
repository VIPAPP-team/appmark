package com.vipapp.appmark2.util.wrapper;

import com.vipapp.appmark2.activity.BaseActivity;
import com.vipapp.appmark2.util.ContextUtils;

@SuppressWarnings("deprecation")
public class mActivity {
    public static BaseActivity get(){
        return ContextUtils.activity;
    }
}
