package com.vipapp.appmark2.util;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;

import com.vipapp.appmark2.activity.BaseActivity;

import static android.content.Context.MODE_PRIVATE;
import static com.vipapp.appmark2.util.Const.PREFS_NAME;

@SuppressWarnings({"DeprecatedIsStillUsed", "deprecation"})
public class ContextUtils extends Utils{

    // Method mContext.get() exists
    @SuppressLint("StaticFieldLeak")
    @Deprecated
    public static Context context;
    // Method mActivity.get() exists
    @SuppressLint("StaticFieldLeak")
    @Deprecated
    public static BaseActivity activity;

    private static boolean running = false;

    public static void updateContext(Context ctx){
        context = ctx;
    }

    public static void updateActivity(Activity activity){
        context = activity;
        ContextUtils.activity = (BaseActivity) activity;
        running = true;
    }

    public static void stopActivity(){
        running = false;
    }

    public static Object getSystemService(String name){
        return context.getSystemService(name);
    }

    // Method Str.get(int res) exists
    @Deprecated
    public static String getString(int res){
        return ContextUtils.context.getString(res);
    }

    // Method mLayoutInflater exists
    @Deprecated
    public static LayoutInflater getLayoutInflater(){
        return ContextUtils.activity.getLayoutInflater();
    }

    // Method mSharedPreferences.get() exists
    @Deprecated
    public static SharedPreferences getSharedPreferences(){
        return ContextUtils.context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    }

    // Method mAppInfo.get() exists
    @Deprecated
    public static PackageInfo getPackageInfo(){
        try {
            return ContextUtils.context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }
}
