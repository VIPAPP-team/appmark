package com.vipapp.appmark2.utils.wrapper;

import androidx.annotation.StringRes;

import com.vipapp.appmark2.utils.ContextUtils;

public class Str {
    // Not deprecated for this class
    @SuppressWarnings("deprecation")
    public static String get(@StringRes int res_id){
        return ContextUtils.getString(res_id);
    }
}
