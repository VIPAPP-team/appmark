package com.vipapp.appmark2.items;

public class Item<Type> {
    private Type instance;
    private int type;

    public Item(Type instance) {
        this(0, instance);
    }

    public Item(int type, Type instance) {
        this.type = type;
        this.instance = instance;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Type getInstance() {
        return this.instance;
    }

    public void setInstance(Type instance) {
        this.instance = instance;
    }

    public OnLoadItem toOnLoadItem() {
        return new OnLoadItem(this.type, this.instance);
    }
}