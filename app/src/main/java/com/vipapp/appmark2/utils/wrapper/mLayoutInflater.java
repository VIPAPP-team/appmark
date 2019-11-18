package com.vipapp.appmark2.utils.wrapper;

import com.vipapp.appmark2.utils.ContextUtils;

public class mLayoutInflater {
    // Not deprecated for this class
    @SuppressWarnings("deprecation")
    public static android.view.LayoutInflater get(){
        return ContextUtils.getLayoutInflater();
    }
}
