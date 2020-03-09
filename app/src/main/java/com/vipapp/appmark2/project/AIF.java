package com.vipapp.appmark2.project;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.exception.IncorrectAIFName;
import com.vipapp.appmark2.project.settings.DefaultProjectSettings;
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

import static com.vipapp.appmark2.util.Const.LOAD_TIME;

@SuppressWarnings("WeakerAccess")
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
    private void add_opened_file(){
        info.put(Const.OPENED_FILE_STRING, Const.getDefaultLastFile(project.getPackage()));
    }
    private void add_warnings(){
        info.put(Const.DANGEROUS_WARN_KEY, Const.DANGEROUS_WARN_VALUE);
    }
    private void add_default_project_settings(){
        info.putAll(new DefaultProjectSettings(project.getDir(), project.getPackage()).getHashMap());
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
        }
    }

}
