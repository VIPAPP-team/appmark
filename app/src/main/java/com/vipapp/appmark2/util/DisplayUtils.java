package com.vipapp.appmark2.util;

import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.util.wrapper.Res;

public class DisplayUtils {
    public static boolean isTablet(Context ctx){
        return ctx.getResources().getBoolean(R.bool.is_tablet);
    }

    public static float getScaledDensity(){
        return Res.get().getDisplayMetrics().scaledDensity;
    }

}
