package com.vipapp.appmark2.utils.wrapper;

import com.vipapp.appmark2.utils.ContextUtils;

public class mSharedPreferences {
    // Not deprecated for this class
    @SuppressWarnings("deprecation")
    public static android.content.SharedPreferences get(){
        return ContextUtils.getSharedPreferences();
    }

    // Setters
    public static void putString(String key, String value){
        get().edit().putString(key, value).apply();
    }
    public static void putInteger(String key, Integer value){
        get().edit().putInt(key, value).apply();
    }

    // Getters
    public static String getString(String key, String default_value){
        return get().getString(key, default_value);
    }
    public static Integer getInteger(String key, Integer default_value){
        return get().getInt(key, default_value);
    }
    public static Boolean getBoolean(String key, Boolean default_value){
        return get().getBoolean(key, default_value);
    }

    public static void remove(String key){
        get().edit().remove(key).apply();
    }

}
