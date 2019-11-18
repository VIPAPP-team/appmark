package com.vipapp.appmark2.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;

public class Permissions {
    public static int REQUEST_CODE = 101;
    public static String[] permissions = { Manifest.permission.WRITE_EXTERNAL_STORAGE };

    public static void request(Activity activity){
        ActivityCompat.requestPermissions(activity, permissions, REQUEST_CODE);
    }

    public static boolean check(Activity activity){
        return Build.VERSION.SDK_INT < 23 || checkPermissions(activity);
    }

    @SuppressLint("NewApi")
    private static boolean checkPermissions(Activity activity){
        for(String permission: permissions)
            if(activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED)
                return false;
        return true;
    }

}
