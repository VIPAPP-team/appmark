package com.vipapp.appmark2.exception;

import androidx.annotation.NonNull;

public class IncorrectAIFName extends RuntimeException {
    private String filename;

    public IncorrectAIFName(String pathname) {
        this.filename = pathname;
    }

    @NonNull
    public String toString() {
        return "AIF must have .aif extension. This file ( " + this.filename + ") have not";
    }
}