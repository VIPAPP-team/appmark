package com.vipapp.appmark2.util.wrapper;

import com.vipapp.appmark2.util.ContextUtils;

public class mLayoutInflater {
    // Not deprecated for this class
    @SuppressWarnings("deprecation")
    public static android.view.LayoutInflater get(){
        return ContextUtils.getLayoutInflater();
    }
}
