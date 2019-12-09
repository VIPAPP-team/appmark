package com.vipapp.appmark2.item;

import android.graphics.Bitmap;
import java.io.File;

public class ProjectItem {
    private Bitmap icon;
    private File icon_file;
    private String name;
    private String project_package;
    private int version_code;
    private String version_name;

    public ProjectItem(String name, String project_package, String version_name, int version_code, Bitmap icon) {
        this.name = name;
        this.project_package = project_package;
        this.version_code = version_code;
        this.version_name = version_name;
        this.icon = icon;
    }

    public ProjectItem(String name, String project_package, String version_name, int version_code, File icon) {
        this.name = name;
        this.project_package = project_package;
        this.version_code = version_code;
        this.version_name = version_name;
        this.icon_file = icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackage() {
        return this.project_package;
    }

    public void setPackage(String project_package) {
        this.project_package = project_package;
    }

    public String getVersionName() {
        return this.version_name;
    }

    public void setVersionName(String version_name) {
        this.version_name = version_name;
    }

    public int getVersionCode() {
        return this.version_code;
    }

    public void setVersionCode(int version_code) {
        this.version_code = version_code;
    }

    public Bitmap getIcon() {
        return this.icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public File getIconFile() {
        return this.icon_file;
    }

    public void setIconFile(File icon_file) {
        this.icon_file = icon_file;
    }
}