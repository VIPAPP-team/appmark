package com.vipapp.appmark2.callback;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Build;
import android.os.Bundle;
import com.vipapp.appmark2.activity.IntroActivity;
import com.vipapp.appmark2.util.ActivityStarter;
import com.vipapp.appmark2.util.ContextUtils;
import com.vipapp.appmark2.util.Permissions;

import com.vipapp.appmark2.util.wrapper.mContext;

public class ActivityLifecycleCallback implements ActivityLifecycleCallbacks {
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    public void onActivityDestroyed(Activity activity) {
        activity.finish();
    }

    public void onActivityPaused(Activity activity) {
        ContextUtils.stopActivity();
    }

    public void onActivityResumed(Activity activity) {
        if (!Permissions.check(activity)) {
            if (activity instanceof IntroActivity) {
                Permissions.request(activity);
            } else {
                ActivityStarter.go("IntroActivity", true);
            }
        }
        ContextUtils.updateActivity(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}