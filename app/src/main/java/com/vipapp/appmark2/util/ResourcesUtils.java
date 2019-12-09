package com.vipapp.appmark2.util;

import android.content.res.Resources;

public class ResourcesUtils {
    public static int getAndroidIdentifier(String name, String res_type){
        return Resources.getSystem().getIdentifier(name, res_type, "android");
    }
}
