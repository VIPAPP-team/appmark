package com.vipapp.appmark2.util;

import java.io.File;

public class Language {
    public static int fromFile(File file) {
        return fromFileExtension(FileUtils.getFileExtension(file));
    }

    public static int fromFileName(String filename) {
        return fromFile(new File(filename));
    }

    public static int fromFileExtension(String file_ext) {
        //noinspection ConstantConditions
        return Const.extToLan.containsKey(file_ext)? Const.extToLan.get(file_ext): 0;
    }
}