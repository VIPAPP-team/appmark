package com.vipapp.appmark2.xml;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class XMLAttribute {
    private String name, value;

    public XMLAttribute(@NonNull String name, @Nullable String value) {
        this.name = name;
        this.value = value;
    }

    @NonNull
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
