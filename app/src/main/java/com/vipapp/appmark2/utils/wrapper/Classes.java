package com.vipapp.appmark2.utils.wrapper;

import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.items.ClassItem;
import com.vipapp.appmark2.utils.AndroidInspector;
import com.vipapp.appmark2.utils.Const;

import java.util.ArrayList;

public class Classes {
    public static void get(final PushCallback<ArrayList<ClassItem>> result){
        new Thread(){
            public void run(){
                AndroidInspector.getAllClasses(Const.ANDROID_JAR_STORAGE, result);
            }
        }.start();
    }
    public static void get(){
        get(result -> {});
    }
}
