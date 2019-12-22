package com.vipapp.appmark2.project;

import androidx.annotation.NonNull;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.exception.IncorrectAIFName;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.Json;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.ThreadLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class AIF extends ThreadLoader {

    public static final int AIF_VERSION = 7;

    private static final String PACKAGE = "project_package";
    private static final String APP_VERSION = "app_ver";
    private static final String APP_VERSION_NUMBER = "app_num";
    private static final String VERSION = "aif_version";

    private File aif;
    private HashMap<String, String> info = null;
    private Project project;

    private String path;

    AIF(@NonNull String path) {
        this.path = path;
    }

    AIF(HashMap<String, String> metadata, String path) {
        this.info = metadata;
        this.path = path;
    }

    public static boolean isAIF(String pathname) {
        return pathname.matches(".+\\.aif");
    }

    public void onAttachProject(Project project) {
        this.project = project;
    }

    private HashMap<String, String> readAif() {
        String text = FileUtils.readFileUI(aif);
        if (text.equals("")) {
            HashMap<String, String> result = new HashMap<>();
            result.put(VERSION, "0");
            return result;
        }
        return Json.stringHashMap(text);
    }

    private void writeAif() {
        FileUtils.writeFileUI(aif, Json.toPrettyJson(info));
    }

    private void writeAif(boolean thread) {
        if (thread) {
            Thread.start(this::writeAif);
        } else {
            writeAif();
        }
    }

    public void save(boolean thread) {
        writeAif(thread);
    }

    //AIF UPDATES
    private void update_with_app_version() {
        info.put(AIF.APP_VERSION, "1.0");
        info.put(AIF.APP_VERSION_NUMBER, "1");
    }

    private void remove_redundant_args() {
        info.remove(APP_VERSION);
        info.remove(APP_VERSION_NUMBER);
        info.remove(PACKAGE);
    }

    private void replace_default_with_opened() {
        info.put(Const.OPENED_FILE_STRING, info.get(Const.DEFAULT_FILE));
        info.remove(Const.DEFAULT_FILE);
        writeAif();
    }

    private void add_warnings() {
        info.put(Const.DANGEROUS_WARN_KEY, Const.DANGEROUS_WARN_VALUE);
    }

    private void add_default_project_settings() {
        info.putAll(new DefaultProjectSettings(
                project.getSource(), project.getPackage(project.getSource())).getHashMap());
    }

    public void updateAif() {
        String versionStr = info.get(VERSION);
        int version = versionStr == null? 1: Integer.parseInt(versionStr);

        if (version != AIF_VERSION) {
            switch (version) {
                case 1:
                    update_with_app_version();
                case 2:
                    remove_redundant_args();
                case 3:
                case 4:
                    replace_default_with_opened();
                case 5:
                    add_warnings();
                case 6:
                    add_default_project_settings();
            }
        }
        info.put(VERSION, Integer.toString(AIF_VERSION));
        writeAif();
    }

    @Override
    public void load() {

        if (!isAIF(path))
            throw new IncorrectAIFName(path);

        if (info == null) {
            aif = new File(path);
            info = readAif();
        } else {
            aif = new File(path);
            add_warnings();
            writeAif();
        }

        if (info == null) {
            // MAGIC CODE, IDK WHY INFO MAY BE NULL, BUT RECURSION SAVES
            load();
        }

        updateAif();
    }

    // SETTERS
    public void setLastFile(File file) {
        if (FileUtils.isChild(file, aif.getParentFile())) {
            info.put(Const.OPENED_FILE_STRING, file.getAbsolutePath().replaceFirst(project.getSource().getAbsolutePath(), ""));
            writeAif(true);
        }
    }

    // GETTERS
    public File getLastFile() {
        String path = info.get(Const.OPENED_FILE_STRING);
        return path == null? aif: new File(project.getSource(), path);
    }

    public ProjectSettings getProjectSettings() {
        ProjectSettings settings = new ProjectSettings();
        for (String str : ProjectSettings.getAvailableKeys()) {
            settings.put(str, info.get(str));
        }
        return settings;
    }

    public void saveProjectSettings(ProjectSettings settings) {
        for (String str : ProjectSettings.getAvailableKeys()) {
            info.put(str, settings.get(str));
        }
    }

}
