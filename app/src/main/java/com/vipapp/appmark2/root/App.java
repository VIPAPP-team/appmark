package com.vipapp.appmark2.root;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Process;

import com.vipapp.appmark2.activity.DebugActivity;
import com.vipapp.appmark2.callback.ActivityLifecycleCallback;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.ContextUtils;
import com.vipapp.appmark2.util.ThrowableUtils;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.server.Server;

import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        preInit();
        initDebugger();
        initServer();
        initFiles();
    }

    /* REGISTER ACTIVITY LIFECYCLE CALLBACKS AND START INITIALIZATION */
    public void preInit(){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallback());
        ContextUtils.updateContext(getApplicationContext());
        initUtils();
    }
    public void initUtils(){
        // Constants initialization
        Const.init(this);
    }
    public void initDebugger(){
        if(Const.DEBUGGER_ENABLED){
            Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
                // starting debug activity
                Intent intent = new Intent(getApplicationContext(), DebugActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("error", ThrowableUtils.toString(throwable));

                PendingIntent pendingIntent = PendingIntent.getActivity(
                        getApplicationContext(), 11111, intent, PendingIntent.FLAG_ONE_SHOT);

                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                assert alarmManager != null;
                alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000, pendingIntent);
                // exit
                Process.killProcess(Process.myPid());
                System.exit(2);
            });
        }
    }
    public void initServer(){
        Server.getServerOnline(result -> {});
    }
    public void initFiles(){
        FileUtils.setupFiles();
    }

}
