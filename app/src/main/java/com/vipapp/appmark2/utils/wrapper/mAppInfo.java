package com.vipapp.appmark2.utils.wrapper;

import android.content.pm.PackageInfo;

import com.vipapp.appmark2.utils.ContextUtils;

public class mAppInfo {
    // Not deprecated for this class
    @SuppressWarnings("deprecation")
    public static PackageInfo get(){
        return ContextUtils.getPackageInfo();
    }

}
