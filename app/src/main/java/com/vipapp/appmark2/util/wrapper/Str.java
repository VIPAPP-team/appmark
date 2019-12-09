package com.vipapp.appmark2.util.wrapper;

import androidx.annotation.StringRes;

import com.vipapp.appmark2.util.ContextUtils;

public class Str {
    // Not deprecated for this class
    @SuppressWarnings("deprecation")
    public static String get(@StringRes int res_id){
        return ContextUtils.getString(res_id);
    }
}
