package com.vipapp.appmark2.items;

public class OnLoadItem {
    private int id;
    private Object object;

    public OnLoadItem(int id) {
        this(id, null);
    }

    public OnLoadItem(int id, Object object) {
        this.id = id;
        this.object = object;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}