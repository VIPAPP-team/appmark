package com.vipapp.appmark2.util;

import java.io.Serializable;

public abstract class ThreadLoader extends CallableThreadLoader implements Serializable {
    protected ThreadLoader() {
        reload();
    }
}