package com.vipapp.appmark2.util;

import com.vipapp.appmark2.callback.PushCallback;
import java.util.ArrayList;

public abstract class CallableThreadLoader {
    private ArrayList<PushCallback<Void>> callbacks = new ArrayList<>();
    private boolean running = false;

    public abstract void load();

    protected CallableThreadLoader() {
    }

    public final void reload(PushCallback<Void> callback) {
        reload();
        exec(callback);
    }


    protected final void reload() {
        this.running = true;
        Thread.start(() -> {
            load();
            this.running = false;
            execCallbacks();
        });
    }

    private void execCallbacks() {
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