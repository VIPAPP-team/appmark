package com.vipapp.appmark2.manager.res;

import android.graphics.Color;

import com.vipapp.appmark2.manager.DefaultResManager;

import java.io.File;

import androidx.annotation.NonNull;

public class ColorsManager extends DefaultResManager<String> {

    public ColorsManager(File source) {
        super(source, "color");
    }

    @NonNull
    @Override
    protected String toValue(@NonNull String stringVal) {
        return stringVal;
    }

    @NonNull
    @Override
    protected String fromValue(@NonNull String value) {
        return value;
    }

}
