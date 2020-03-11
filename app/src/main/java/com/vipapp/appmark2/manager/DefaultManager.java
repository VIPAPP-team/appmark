package com.vipapp.appmark2.manager;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.util.Thread;
import java.util.ArrayList;

import androidx.annotation.AnyThread;

@SuppressWarnings("WeakerAccess")
public abstract class DefaultManager<Type> {
    private ArrayList<PushCallback<ArrayList<Type>>> callbacks = new ArrayList<>();
    private ArrayList<Type> objects = new ArrayList<>();
    private boolean running = false;

    @AnyThread
    public abstract ArrayList<Type> init(Object... objArr);

    public DefaultManager(Object... args) {
        reload(args);
    }

    public final void exec(PushCallback<ArrayList<Type>> callback) {
        Thread.ui(() -> {
            if (this.running) {
                this.callbacks.add(callback);
            } else {
                callback.onComplete(this.objects);
            }
        });
    }

    private void execCallbacks() {
        for(PushCallback<ArrayList<Type>> callback: callbacks){
            exec(callback);
        }
        this.callbacks.clear();
    }

    public final void reload(Object... args) {
        this.running = true;
        Thread.start(() -> {
            this.objects = init(args);
            this.running = false;
            execCallbacks();
        });
    }

    public final void reload(PushCallback<ArrayList<Type>> onReload, Object... args) {
        reload(args);
        exec(onReload);
    }

    public final ArrayList<Type> getObjects() {
        return this.objects;
    }

    public void setObjects(ArrayList<Type> objects) {
        this.objects = objects;
    }
}