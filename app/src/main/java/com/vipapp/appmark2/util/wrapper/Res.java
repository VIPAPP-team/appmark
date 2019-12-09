package com.vipapp.appmark2.util.wrapper;

import android.content.res.Resources;

public class Res {
    public static Resources get(){
        return mContext.get().getResources();
    }
}
