package com.vipapp.appmark2.root;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Process;

import com.vipapp.appmark2.activities.DebugActivity;
import com.vipapp.appmark2.callbacks.ActivityLifecycleCallback;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.ContextUtils;
import com.vipapp.appmark2.utils.ExceptionUtils;
import com.vipapp.appmark2.utils.FileUtils;
import com.vipapp.obfuscated.compiler.Compiler;
import com.vipapp.obfuscated.server.Server;

import java.io.PrintWriter;
import java.io.StringWriter;

import androidx.multidex.MultiDexApplication;

import static com.vipapp.appmark2.utils.Const.COMPILER_STORAGE;

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
                intent.putExtra("error", ExceptionUtils.toString(throwable));

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
