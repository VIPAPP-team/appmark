package com.vipapp.appmark2.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.vipapp.appmark2.util.wrapper.mActivity;
import com.vipapp.appmark2.util.wrapper.mContext;

public class ActivityStarter {

    public static void go(String activityName){
        go(activityName, false);
    }

    public static void go(String activityName, boolean finishOldActivity){
        Intent i = new Intent();
        i.setClassName(mContext.get(), "com.vipapp.appmark2.activity." + activityName);
        Activity activity = ContextUtils.activity;
        activity.startActivity(i);
        if(finishOldActivity)
            activity.finish();
    }

    public static void goExternal(Uri uri){
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        mActivity.get().startActivity(i);
    }

    public static void goExternalUrl(String url){
        goExternal(Uri.parse(url));
    }

}
