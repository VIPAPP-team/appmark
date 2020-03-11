package com.vipapp.appmark2.util;

import android.content.res.Resources;

import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.util.wrapper.mContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.annotation.Nullable;

import static com.vipapp.appmark2.util.Const.REFERENCE_REGEX;

@SuppressWarnings("WeakerAccess")
public class ResourcesUtils {
    @Nullable
    public static String getResType(String name) {
        Matcher matcher = Pattern.compile(REFERENCE_REGEX).matcher(name);
        if (matcher.find()) {
            String res_type = matcher.group();
            return res_type.replaceAll("(\\?|@(android:)?)", "").replaceAll("/", "");
        }
        return null;
    }

    public static int getAndroidIdentifier(String resourceName) {
        String type = getResType(resourceName);
        String name = resourceName.replaceFirst(REFERENCE_REGEX, "");
        if(type == null){
            type = "attr";
            name = resourceName.replaceFirst("\\?.+:", "");
        }
        return getAndroidIdentifier(name, type);
    }

    public static int getAndroidIdentifier(String name, String type) {
        return Resources.getSystem().getIdentifier(name, type, "android");
    }
}
