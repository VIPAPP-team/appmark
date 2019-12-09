package com.vipapp.appmark2.util;

import com.vipapp.appmark2.callback.PushCallback;
import java.util.ArrayList;

public abstract class CallableThreadLoader {
    private ArrayList<PushCallback<Void>> callbacks = new ArrayList<>();
    private boolean running = false;

    public abstract void load(Object... objArr);

    protected CallableThreadLoader() {
    }

    public final void reload(PushCallback<Void> callback, Object... args) {
        reload(args);
        exec(callback);
    }

    public final void reload(Object... args) {
        this.running = true;
        Thread.start(() -> {
            load(args);
            this.running = false;
            execCallbacks();
        });
    }

    public final void execCallbacks() {
        for(PushCallback<Void> callback: callbacks){
            exec(callback);
        }
        this.callbacks.clear();
    }

    public final void exec(PushCallback<Void> callback) {
        if (this.running) {
            this.callbacks.add(callback);
        } else {
            Thread.ui(() -> callback.onComplete(null));
        }
    }
}