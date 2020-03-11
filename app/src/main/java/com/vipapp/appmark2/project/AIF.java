package com.vipapp.appmark2.project;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.exception.IncorrectAIFName;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.Json;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.ThreadLoader;
import com.vipapp.appmark2.util.ThrowableUtils;
import com.vipapp.appmark2.util.Toast;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.vipapp.appmark2.util.Const.LOAD_TIME;

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

    private void addOnAttachCallback(PushCallback<Void> callback){
        if(project == null) onAttach.add(callback);
        else callback.onComplete(null);
    }

    private HashMap<String, String> read_aif(){
        String text = FileUtils.readFileUI(aif);
        try {
            if(text.equals("")) throw new RuntimeException();
            return Json.stringHashMap(text);
        } catch (Exception e){
            HashMap<String, String> result = new HashMap<>();
            result.put(VERSION, "1");
            return result;
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
                project.getSource(), project.getPackage(project.getSource())).getHashMap());
    }

    public void update_aif(){
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
            write_aif();
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

    @Nullable
    public ProjectSettings getProjectSettings(){
        try {
            ProjectSettings settings = new ProjectSettings();
            for (String str : ProjectSettings.getAvailableKeys()) {
                settings.put(str, info.get(str));
            }
            return settings;
        } catch (Exception e){
            return null;
        }
    }

    public void saveProjectSettings(ProjectSettings settings) {
        for (String str : ProjectSettings.getAvailableKeys()) {
            info.put(str, settings.get(str));
        }
    }

    @Override
    public void load() {

        if (!isAIF(path))
            throw new IncorrectAIFName(pathname);

        if(info == null) {
            aif = new File(path);
            info = read_aif();
        } else {
            //noinspection unchecked
            info = (HashMap<String, String>) args[0];
            aif = new File(path);
            add_warnings();
            write_aif();
        }

        if(info == null) {
            // MAGIC CODE, IDK WHY INFO MAY BE NULL, BUT RECURSION SAVES
            load(args);
        }
    }

}
