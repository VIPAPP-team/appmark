package com.vipapp.appmark2.utils;

import java.io.File;

public class ProjectUtils {

    public static String getFileLocale(File file) {
        String parent_file_name = file.getParentFile().getName();
        if (parent_file_name.equals("values")) {
            return "default";
        }
        return parent_file_name.replaceAll("values-", "");
    }

}
