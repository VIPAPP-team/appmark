package com.vipapp.appmark2.util.wrapper;

public class Service {
    public static Object get(String name){
        return mContext.get().getSystemService(name);
    }
}
