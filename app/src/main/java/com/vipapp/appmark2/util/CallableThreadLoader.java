package com.vipapp.appmark2.util;

import com.vipapp.appmark2.callback.PushCallback;
import java.util.ArrayList;

import androidx.annotation.Nullable;

@SuppressWarnings("WeakerAccess")
public abstract class CallableThreadLoader {
    private ArrayList<PushCallback<Void>> callbacks = new ArrayList<>();
    private boolean running = false;

    public abstract void load();

    protected CallableThreadLoader() {
    }

    public void reload() {
        reload(null);
    }


    public void reload(@Nullable PushCallback<Void> callback) {
        if(!running) {
            this.running = true;
            Thread.start(() -> {
                load();
                this.running = false;
                execCallbacks();
                if (callback != null)
                    exec(callback);
            });
        } else {
            exec(none -> reload(callback));
        }
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