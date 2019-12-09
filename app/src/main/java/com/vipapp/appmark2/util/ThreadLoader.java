package com.vipapp.appmark2.util;

import java.io.Serializable;

public abstract class ThreadLoader extends CallableThreadLoader implements Serializable {
    public ThreadLoader(Object... args) {
        reload(args);
    }
}