package com.vipapp.appmark2.util.wrapper;

import com.vipapp.appmark2.util.ContextUtils;

public class mContext {
    @SuppressWarnings("deprecation")
    public static android.content.Context get(){
        return ContextUtils.context;
    }
}
