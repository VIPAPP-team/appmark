package com.vipapp.appmark2.items;

import java.io.File;

public class FileLocale {
    private String locale_name;
    private File path;

    public FileLocale(File path, String locale_name) {
        this.path = path;
        this.locale_name = locale_name;
    }

    public File getPath() {
        return this.path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public String getLocaleName() {
        return this.locale_name;
    }

    public void setLocaleName(String locale_name) {
        this.locale_name = locale_name;
    }
}