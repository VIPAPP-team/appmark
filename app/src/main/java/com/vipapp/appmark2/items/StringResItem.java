package com.vipapp.appmark2.items;

public class StringResItem {
    private String name;
    private boolean translatable = true;
    private String value;

    public StringResItem(String name, String value, boolean translatable) {
        String str = "";
        this.name = str;
        this.value = str;
        this.name = name;
        this.value = value;
        this.translatable = translatable;
    }

    public StringResItem() {
        String str = "";
        this.name = str;
        this.value = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isTranslatable() {
        return this.translatable;
    }

    public void setTranslatable(boolean translatable) {
        this.translatable = translatable;
    }
}