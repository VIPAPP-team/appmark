package com.vipapp.appmark2.utils.wrapper;

import com.vipapp.appmark2.utils.ContextUtils;

public class mContext {
    @SuppressWarnings("deprecation")
    public static android.content.Context get(){
        return ContextUtils.context;
    }
}
