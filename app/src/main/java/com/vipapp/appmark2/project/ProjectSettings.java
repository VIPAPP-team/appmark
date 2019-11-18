package com.vipapp.appmark2.project;

import java.util.HashMap;

public class ProjectSettings {

    private static String[] available_keys = {"src", "res", "assets", "manifest", "build"};

    private HashMap<String, String> settings = new HashMap<>();

    public ProjectSettings(){}
    public ProjectSettings(String src, String res, String assets, String manifest, String build) {
        put("src", src);
        put("res", res);
        put("assets", assets);
        put("manifest", manifest);
        put("build", build);
    }

    public String get(String name){
        return settings.get(name);
    }

    public void put(String name, String value){
        settings.put(name, value);
    }

    public static String[] getAvailableKeys() {
        return available_keys;
    }
    public HashMap<String, String> getHashMap() {
        return settings;
    }

}
