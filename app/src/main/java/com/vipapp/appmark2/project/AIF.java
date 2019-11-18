package com.vipapp.appmark2.project;

import androidx.annotation.NonNull;

import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.exception.IncorrectAIFName;
import com.vipapp.appmark2.project.settings.DefaultProjectSettings;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.FileUtils;
import com.vipapp.appmark2.utils.Json;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.ThreadLoader;
import com.vipapp.appmark2.utils.wrapper.Str;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class AIF extends ThreadLoader {

    private ArrayList<PushCallback<Void>> onAttach = new ArrayList<>();

    public static final int AIF_VERSION = 7;

    static final String NAME = "project_name";
    static final String ICON = "icon_path";
    private static final String PACKAGE = "project_package";
    private static final String APP_VERSION = "app_ver";
    private static final String APP_VERSION_NUMBER = "app_num";
    private static final String VERSION = "aif_version";

    private File aif;
    private HashMap<String, String> info = new HashMap<>();
    private Project project;

    AIF(@NonNull String pathname) {
        super(pathname);
    }
    AIF(HashMap<String, String> metadata, String pathname){
        super(metadata, pathname);
    }

    public static boolean isAIF(String pathname){
        return pathname.matches(".+\\.aif");
    }

    public void onAttachProject(Project project){
        this.project = project;
        execOnAttachCallbacks();
    }
    private void execOnAttachCallbacks(){
        for(PushCallback<Void> callback: onAttach) callback.onComplete(null);
    }

    private HashMap<String, String> read_aif(){
        String text = FileUtils.readFileUI(aif);
        if(text.equals("")){
            HashMap<String, String> result = new HashMap<>();
            result.put(VERSION, "0");
            return result;
        }
        return Json.stringHashMap(text);
    }
    private void write_aif(){
        FileUtils.writeFileUI(aif, Json.toPrettyJson(info));
    }
    private void write_aif(boolean thread){
        if(thread){
            Thread.start(this::write_aif);
        } else {
            write_aif();
        }
    }

    public void save(boolean thread){
        write_aif(thread);
    }

    //AIF UPDATES
    private void update_with_app_version(){
        info.put(AIF.APP_VERSION, "1.0");
        info.put(AIF.APP_VERSION_NUMBER, "1");
    }
    private void remove_redundant_args(){
        info.remove(APP_VERSION);
        info.remove(APP_VERSION_NUMBER);
        info.remove(PACKAGE);
    }
    private void add_default_file(){
        info.put(Const.DEFAULT_FILE, Const.getDefaultLastFile(project.getPackage()));
    }
    private void replace_default_with_opened(){
        info.put(Const.OPENED_FILE_STRING, info.get(Const.DEFAULT_FILE));
        info.remove(Const.DEFAULT_FILE);
        write_aif();
    }
    private void add_warnings(){
        info.put(Const.DANGEROUS_WARN_KEY, Const.DANGEROUS_WARN_VALUE);
    }
    private void add_default_project_settings(){
        info.putAll(new DefaultProjectSettings(project.getDir(), project.getPackage()).getHashMap());
    }

    private void update_aif(int old_version){
        switch(old_version){
            case 1: update_with_app_version();
            case 2: remove_redundant_args();
            case 3: add_default_file();
            case 4: replace_default_with_opened();
            case 5: add_warnings();
            case 6: add_default_project_settings();
        }
        info.put(VERSION, Integer.toString(AIF_VERSION));
        write_aif();
    }

    // SETTERS
    public void setLastFile(File file){
        if(FileUtils.isChild(file, aif.getParentFile())){
            info.put(Const.OPENED_FILE_STRING, file.getAbsolutePath().replaceFirst(project.getDir().getAbsolutePath(), ""));
            write_aif(true);
        }
    }
    // GETTERS
    public File getLastFile(){
        return new File(project.getDir(), info.get(Const.OPENED_FILE_STRING));
    }
    public ProjectSettings getProjectSettings(){
        ProjectSettings settings = new ProjectSettings();
        for(String str: ProjectSettings.getAvailableKeys()){
            settings.put(str, info.get(str));
        }
        return settings;
    }
    public void saveProjectSettings(ProjectSettings settings){
        for(String str: ProjectSettings.getAvailableKeys()){
            info.put(str, settings.get(str));
        }
    }

    @Override
    public void load(Object... args) {
        String pathname = (String) (args[0] instanceof String? args[0]: args[1]);

        if (!isAIF(pathname))
            throw new IncorrectAIFName(pathname);

        if(args[0] instanceof HashMap) {
            //noinspection unchecked
            info = (HashMap<String, String>) args[0];
            aif = new File(pathname);
            add_warnings();
            write_aif();
        } else {
            aif = new File(pathname);
            info = read_aif();
        }

        if(info == null) {
            // MAGIC CODE, IDK WHY INFO MAY BE NULL, BUT RECURSION SAVES
            load(args);
        } else {
            int version = Integer.parseInt(info.get(VERSION));
            if (version != AIF_VERSION)
                update_aif(version);
        }
    }

}
