package com.vipapp.appmark2.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vipapp.appmark2.exception.AIFParseException;
import com.vipapp.appmark2.exception.IncorrectAIFName;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.Json;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.ThreadLoader;

import java.io.File;
import java.util.HashMap;

import static com.vipapp.appmark2.util.Const.PROJECT_STORAGE;

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

    /**
     * @param source - project source where aif need be located in (Need be child of project storage)
     */
    public static void generateDefaultUI(File source, String projectPackage){
        File aifSource = new File(source, "project.aif");
        FileUtils.refresh(aifSource, false);

        HashMap<String, String> info = new HashMap<>();
        info.put("aif_version", String.valueOf(AIF_VERSION));
        info.putAll(new DefaultProjectSettings(source, projectPackage).getHashMap());

        new AIF(info, aifSource.getAbsolutePath()).loadUI();
    }

    public AIF(@NonNull File file){
        this(file.getAbsolutePath());
    }

    public AIF(@NonNull String path) {
        this.path = path;
    }

    public AIF(HashMap<String, String> metadata, String path) {
        this.info = metadata;
        this.path = path;
    }

    public static boolean isAIF(String pathname) {
        return pathname.matches(".+\\.aif");
    }

    public void onAttachProject(Project project) {
        this.project = project;
    }

    private HashMap<String, String> read_aif(){
        String text = FileUtils.readFileUI(aif);
        try {
            if(text.equals("")) throw new RuntimeException();
            return Json.stringHashMap(text);
        } catch (Exception e){
            throw new AIFParseException();
        }
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
    private void add_opened_file(){
        info.put(Const.OPENED_FILE_STRING, Const.getDefaultLastFile(project.getPackage()));
    }

    private void add_warnings() {
        info.put(Const.DANGEROUS_WARN_KEY, Const.DANGEROUS_WARN_VALUE);
    }

    private void add_default_project_settings() {
        info.putAll(new DefaultProjectSettings(
                project.getSource(), project.getPackage()).getHashMap());
    }

    public void update(){
        String versionStr = info.get(VERSION);
        int version = versionStr == null? 0: Integer.parseInt(versionStr);
        if (version != AIF_VERSION) {
            if (version < 7)
                version = 1;

            switch (version) {
                case 1:
                    add_opened_file();
                    add_warnings();
                    add_default_project_settings();
            }
            info.put(VERSION, Integer.toString(AIF_VERSION));
            writeAif();
        }
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

    @NonNull
    public ProjectSettings getSettings(){
        ProjectSettings settings = new ProjectSettings();
        for (String str : ProjectSettings.getAvailableKeys()) {
            settings.put(str, info.get(str));
        }
        return settings;
    }

    public void setProjectSettings(ProjectSettings settings) {
        for (String str : ProjectSettings.getAvailableKeys()) {
            info.put(str, settings.get(str));
        }
    }

    @Override
    public void load() {

        if (!isAIF(path))
            throw new IncorrectAIFName(path);

        if(info == null) {
            aif = new File(path);
            info = read_aif();
        } else {
            aif = new File(path);
            add_warnings();
            writeAif();
        }
    }

    public static class ProjectSettings {

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

}
