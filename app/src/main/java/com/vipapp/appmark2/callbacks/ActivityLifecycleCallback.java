package com.vipapp.appmark2.callbacks;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.vipapp.appmark2.activities.IntroActivity;
import com.vipapp.appmark2.utils.ActivityStarter;
import com.vipapp.appmark2.utils.ContextUtils;
import com.vipapp.appmark2.utils.Permissions;

public class ActivityLifecycleCallback implements ActivityLifecycleCallbacks {
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
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