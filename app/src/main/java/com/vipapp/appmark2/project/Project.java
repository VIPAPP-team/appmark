package com.vipapp.appmark2.project;

import android.graphics.Bitmap;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.FileLocale;
import com.vipapp.appmark2.item.ProjectItem;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.FileGenUtils;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.TextUtils;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.ThreadLoader;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;

import static com.vipapp.appmark2.util.Const.LOAD_TIME;

@SuppressWarnings("WeakerAccess")
public class Project extends ThreadLoader implements Serializable {

    private Res resources;
    private File source;
    private AIF aif;
    private AndroidManifest manifest;
    private boolean supported = false;
    private ProjectSettings settings;

    private Project(File source){
        this.source = source;
    }
    public static Project fromFile(File file){
        return new Project(file);
    }

    private void aifSetup(){
        this.aif = getAIF(this);
        aif.onAttachProject(this);
        int[] loading = new int[]{0, 1};
        this.aif.exec(none -> loading[0]++);

        // hold thread until loaded
        while(loading[0] != loading[1]){ Thread.sleep(LOAD_TIME); }
    }
    private void settingsSetup(){
        settings = aif.getProjectSettings();
    }
    private void setupResources(){
        int[] loading = new int[]{0, 1};
        resources = Res.fromFile(getResDir());
        resources.exec(none -> loading[0]++);
        while(loading[0] != loading[1]){
            Thread.sleep(LOAD_TIME);
        }
    }
    private void manifestSetup(){
        int[] load = new int[]{0, 1};
        File manifest_file = getAndroidManifestFile();
        manifest = AndroidManifest.fromFile(manifest_file);
        manifest.attachProject(this);
        manifest.reload(none -> load[0]++);
        while(load[0] != load[1]){ Thread.sleep(LOAD_TIME); }
    }
    private void loadAllUI(){
        setupResources();
    }

    // can throw exception while setup
    private void setup(){
        aifSetup();
        settingsSetup();
        loadAllUI();
        manifestSetup();
    }

    private boolean try_to_setup() {
        try {
            setup();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void load() {
        if(notProject(source))
            throw new RuntimeException("This is not project");

        supported = try_to_setup();
    }

    private void saveUI(){
        manifest.saveUI();
    }
    public void save(PushCallback<Void> onSave){
        Thread.start(() -> {
            saveUI();
            if(onSave != null) onSave.onComplete(null);
        });
    }
    public void save(){
        save(null);
    }

    private void saveSettings(){
        aif.saveProjectSettings(settings);
        aif.save(true);
    }

    public void delete(){
        FileUtils.deleteFile(source);
    }

    public String localizeString(@Nullable String string){
        if(string == null)
            return "";
        if(!string.startsWith("@"))
            return string;
        if(!string.startsWith("@string/"))
            return null;
        return resources.get(string);
    }

    public Res getResources() {
        return resources;
    }

    public String getPackage() {
        return manifest.getPackage();
    }
    public String getPackage(File file){
        if(FileUtils.isChild(file, getJavaDir())){
            String relative_path = FileUtils.getRelativePath(file, getJavaDir()).replace('/', '.');
            String package_with_dot = getPackage() + "." + TextUtils.replaceLast(relative_path, file.getName(), "");
            return package_with_dot.substring(0, package_with_dot.length() - 1);
        }
        return "";
    }
    public String getName() {
        return localizeString(manifest.getAppName());
    }
    public String getVersionName(){
        return manifest.getVersionName();
    }
    public int getVersionCode(){
        return manifest.getVersionCode();
    }
    public File getIcon() {
        return manifest.getIcon();
    }
    public File getSource() {
        return source;
    }
    public AIF getAif(){
        return aif;
    }
    public boolean isSupported() {
        return supported;
    }
    public AndroidManifest getManifest() {
        return manifest;
    }
    public ProjectSettings getSettings() {
        return settings;
    }

    public File getJavaDir(){
        return new File(settings.get("src"));
    }
    public File getResDir(){
        return new File(settings.get("res"));
    }
    public File getAssetsDir(){
        return new File(settings.get("assets"));
    }
    public File getLayoutDir(){
        return new File(getResDir(), "layout");
    }
    public File getValuesDir(){
        return getValuesDir(null);
    }
    public File getValuesDir(String locale){
        return new File(getResDir(),
                "values" + (isLocaleDefault(locale)? "": "-" + locale));
    }
    public File getDrawablesDir(){
        return new File(getResDir(), "drawable");
    }
    public File getStringsFile(){
        return new File(getValuesDir(), "strings.xml");
    }
    public File getStringsFile(String locale){
        return new File(getValuesDir(locale), "strings.xml");
    }

    public File getAndroidManifestFile(){
        return new File(settings.get("manifest"));
    }

    // Locales
    public ArrayList<FileLocale> getFileLocales() {
        ArrayList<FileLocale> list = new ArrayList<>();
        File workspace = getResDir();
        if (workspace.exists() && workspace.isDirectory()) {
            for (File file : workspace.listFiles()) {
                if (FileUtils.isFileValues(file, this)) {
                    list.add(new FileLocale(file, getValuesFileLocale(file)));
                }
            }
        }
        return list;
    }

    private String getValuesFileLocale(File file){
        return file.getName().equals("values")? "default": file.getName().replaceAll("values-", "");
    }
    private boolean isLocaleDefault(String locale){
        return locale == null || locale.equals("") || locale.equals("default");
    }

    // Setters
    public void setSettings(ProjectSettings settings){
        this.settings = settings;
        saveSettings();
    }

    public void setName(String name){
        if(manifest.getAppName() == null || !manifest.getAppName().equals(name))
            manifest.setName(name);
    }
    public void setVersionName(String versionName){
        if(manifest.getVersionName() == null || !manifest.getVersionName().equals(versionName))
            manifest.setVersionName(versionName);
    }
    public void setVersionCode(int versionCode){
        if(manifest.getVersionCode() != versionCode)
            manifest.setVersionCode(versionCode);
    }
    public void setIconUI(Bitmap icon){
        ImageUtils.saveBitmap(icon, getIcon());
    }

    public ProjectItem getProjectItem(){
        return getProjectItem(true);
    }
    public ProjectItem getProjectItem(boolean needToLocalize){
        return new ProjectItem(needToLocalize? getName(): manifest.getAppName(),
                getPackage(),
                getVersionName(),
                getVersionCode(),
                getIcon());
    }

    // SERIALIZATION METHODS
    private void readObject(ObjectInputStream stream) {
        try {
            String path = (String) stream.readObject();
            source = new File(path);
        } catch (IOException|ClassNotFoundException e){
            // MAGIC CODE CUZ IN RANDOM MOMENTS IS CAN BE THROWED
            readObject(stream);
            return;
        }
        reload();
    }
    private void writeObject(ObjectOutputStream stream) {
        try {
            if (source != null) {
                stream.writeObject(source.getAbsolutePath());
            }
        } catch (IOException e){
            // MAGIC CODE CUZ IN RANDOM MOMENTS IS CAN BE THROWED
            writeObject(stream);
        }
    }

    // STATIC METHODS
    public static boolean notProject(File file){
        return file == null ||
                !file.isDirectory() ||
                file.list((file1, s) -> AIF.isAIF(s)).length != 1;
    }

    private static AIF getAIF(Project project){
        return new AIF(
                new File(project.source, project.source.list((file1, s) -> AIF.isAIF(s))[0]).toString());
    }

    public static void createNew(File source, String name, String packag, String version_name, int version_id, String first_activity, int minSDK, PushCallback<Project> callback) {
        Thread.start(() -> {
            FileUtils.refresh(source, true);
            HashMap<String, String> meta_info = new HashMap<String, String>(){{}};
            generateAIF(meta_info, source, packag);
            FileGenUtils.generateDefaultProject(source, packag, name, version_name, version_id, first_activity, minSDK,
                    none -> {
                        Project project = new Project(source);
                        project.exec(_none_ -> callback.onComplete(project));
                    });
        });
    }

    private static void generateAIF(HashMap<String, String> meta_info, File source, String project_package){
        source = new File(source, "project.aif");
        FileUtils.refresh(source, false);
        meta_info.put(Const.OPENED_FILE_STRING, Const.getDefaultLastFile(project_package));
        meta_info.put("aif_version", String.valueOf(AIF.AIF_VERSION));
        meta_info.putAll(new DefaultProjectSettings(source.getParentFile(), project_package).getHashMap());
        new AIF(meta_info, source.getAbsolutePath());
    }

}
