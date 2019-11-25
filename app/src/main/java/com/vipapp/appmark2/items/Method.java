package com.vipapp.appmark2.items;

import com.vipapp.appmark2.utils.ClassUtils;

@SuppressWarnings("UnusedReturnValue")
public class Method {

    private Object parent;
    private String name;
    private Class<?>[] classes;

    public Method(Object parent, String name, Class<?>... classes){
        this.parent = parent;
        this.name = name;
        this.classes = classes;
    }

    public boolean exists(){
        return ClassUtils.hasMethod(parent, name, classes);
    }

    public boolean call(Object... args){
        return ClassUtils.callMethod(parent, name, classes, args);
    }

}
