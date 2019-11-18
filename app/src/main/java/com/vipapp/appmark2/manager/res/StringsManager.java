package com.vipapp.appmark2.manager.res;

import com.vipapp.appmark2.manager.DefaultResManager;

import java.io.File;

import androidx.annotation.NonNull;

public class StringsManager extends DefaultResManager<String> {

    public StringsManager(File source) {
        super(source, "string");
    }

    @NonNull
    protected String toValue(@NonNull String stringVal) {
        return stringVal;
    }
    @NonNull
    protected String fromValue(@NonNull String value) {
        return value;
    }

}
