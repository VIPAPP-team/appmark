package com.vipapp.appmark2.item;

public class CallbackObject<T> {
    private int id;
    private T object;

    public CallbackObject(int i, T t) {
        this.object = t;
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public T getObject() {
        return this.object;
    }

    public Class<?> getType() {
        return this.object.getClass();
    }
}