package com.vipapp.appmark2.utils;

import android.util.SparseIntArray;

import com.vipapp.appmark2.callbacks.Predicate;
import com.vipapp.appmark2.utils.wrapper.mActivity;

public class Thread extends java.lang.Thread{

    private static SparseIntArray threads = new SparseIntArray();

    public Thread(){}

    public Thread(Runnable runnable){
        super(runnable);
    }

    public static void startThread(Class<? extends Thread> thread){
        try{ thread.newInstance().start(); } catch (Exception ignored) {}
    }

    public static void ui(Runnable runnable){
        try{
            if(mActivity.get() != null) mActivity.get().runOnUiThread(runnable); else {
                runnable.run();
            }
        } catch (Exception e){
            ui(() -> Toast.error("Toast[12]", e));
            delay(1000, runnable, true);
        }
    }

    public static void sleep(long milliseconds){
        try {
            java.lang.Thread.sleep(milliseconds);
        } catch (InterruptedException ignored){}
    }

    public static void delay(long milliseconds, Runnable action){
        delay(milliseconds, action, false);
    }

    public static void delay(long milliseconds, Runnable action, boolean ui){
        new Thread(() -> {
            sleep(milliseconds);
            if(ui)
                ui(action);
            else
                new Thread(action).start();
        }).start();
    }

    public static void delay(int id, long milliseconds, Runnable action){
        delay(id, milliseconds, action, false);
    }

    public static void loop(long milliseconds, Predicate<Boolean> action){
        Thread.start(() -> {
            while(action.test(null)){
                sleep(milliseconds);
            }
        });
    }

    public static void loop(long milliseconds, Runnable action){
        loop(milliseconds, none -> {
            action.run();
            return true;
        });
    }

    public static void delay(int id, long milliseconds, Runnable action, boolean ui){
        threads.put(id, threads.get(id, 0) + 1);
        int start_value = threads.get(id, 0);
        delay(milliseconds, () -> {
            if(start_value == threads.get(id)) action.run();
        }, ui);
    }

    public static void start(Runnable action){
        new Thread(action).start();
    }

}
