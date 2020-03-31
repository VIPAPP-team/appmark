package com.vipapp.appmark2.util;

import java.io.Serializable;

public abstract class ThreadLoader extends CallableThreadLoader implements Serializable {
    private boolean loaded = false;

    protected ThreadLoader() {
        Thread.delay(100, () -> {
            if(!loaded)
                reload();
        });
    }

    public void loadUI(){
        loaded = true;
        load();
    }

}