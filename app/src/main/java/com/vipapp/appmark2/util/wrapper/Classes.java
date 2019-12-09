package com.vipapp.appmark2.util.wrapper;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.ClassItem;
import com.vipapp.appmark2.util.AndroidInspector;
import com.vipapp.appmark2.util.Const;

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
