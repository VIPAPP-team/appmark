package com.vipapp.appmark2.project;

import android.graphics.Bitmap;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.exception.AIFParseException;
import com.vipapp.appmark2.item.FileLocale;
import com.vipapp.appmark2.item.ProjectItem;
import com.vipapp.appmark2.util.CallableThreadLoader;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.TextUtils;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.Toast;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
  * Pack all data from project directory in this class
  * You can create new default project using Project.createNew() method
  * Or open already created one with Project.fromFile()
  */

public class Project extends CallableThreadLoader implements Serializable {
    /* Static class declaration */

    public static final int NO_ERROR = 0;
    public static final int ERROR_NO_AIF = 1;
    public static final int ERROR_PARSING_AIF = 2;
    public static final int ERROR_MANIFEST_FILE_RETRIEVING = 4;
    public static final int ERROR_PARSING_MANIFEST = 5;
    public static final int ERROR_RES_FILE_RETRIEVING = 6;

    /**
     * @param source - project location
     * @param projectPushCallback - callback to which Project instance will be put after setup
     */
    public static void fromFile(@NonNull File source, @Nullable PushCallback<Project> projectPushCallback){
        Project project = new Project(source);
        project.reload(none -> {
            if(projectPushCallback != null)
                projectPushCallback.onComplete(project);
        });
    }

    /**
     * Method starts new thread, generates project with ProjectCreator and after pushes it to callback
     * @param source - new project location
     * @param configuration - new project data
     * @param projectPushCallback - callback to which Project instance will be put after setup
     */
    public static void createNew(@NonNull File source, @NonNull NewProjectConfiguration configuration, @Nullable PushCallback<Project> projectPushCallback){
        Thread.start(() -> {
            Project newProject = ProjectCreator.with(source, configuration).create();
            Thread.ui(() -> {
                if(projectPushCallback != null)
                    projectPushCallback.onComplete(newProject);
            });
        });
    }

    /* Non static class declaration */

    // The file where project localed in
    @NonNull private File source;
    // Appmark Info File associated with this project
    private AIF aif;
    // Appmark settings for this project
    private AIF.ProjectSettings settings;
    // Parsed part of resources
    private Res res;
    // Configuration with parsed project meta-info
    private ProjectConfiguration configuration;
    // Parsed manifest
    private AndroidManifest manifest;

    // Error while load(); if 0 then there is no error
    private int error = NO_ERROR;

    private Project(@NonNull File source){
        this.source = source;
    }

    /**
     * Method setups project from file
     * That method works in current thread. It's not recommended to call from UI
     * After setup variable 'valid' is true or false; if false, then 'error' variable contains error code
     */
    public void load() {
        File aifFile = new File(source, "project.aif");
        if(aifFile.exists()){
            try {
                aif = new AIF(aifFile);
                aif.onAttachProject(this);
                aif.loadUI();
                settings = aif.getSettings();
                String manifestPath = aif.getSettings().get("manifest");
                if(manifestPath != null) {
                    File manifestFile = new File(manifestPath);
                    if(manifestFile.exists()) {
                        manifest = AndroidManifest.fromFile(manifestFile);
                        manifest.load();
                        if(manifest.isCorrect()) {
                            aif.update();
                            settings = aif.getSettings();
                            File resDir = getResDir();
                            if(resDir != null) {
                                res = Res.fromFile(getResDir());
                                res.loadUI();
                                manifest.attachProject(this);
                                configuration = ProjectConfiguration.fromProject(this);
                            } else {
                                error = ERROR_RES_FILE_RETRIEVING;
                            }
                        } else {
                            error = ERROR_PARSING_MANIFEST;
                        }
                    } else {
                        error = ERROR_MANIFEST_FILE_RETRIEVING;
                    }
                } else {
                    error = ERROR_MANIFEST_FILE_RETRIEVING;
                }
            } catch (AIFParseException e){
                error = ERROR_PARSING_AIF;
            }
        } else {
            error = ERROR_NO_AIF;
        }
    }


    /**
     * @return array of file locales that available in project
     */
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

    /**
     * @param file we need to get locale from
     * @return locale of file
     */
    private String getValuesFileLocale(File file){
        return file.getName().equals("values")? "default": file.getName().replaceAll("values-", "");
    }


    private boolean isLocaleDefault(String locale){
        return locale == null || locale.equals("") || locale.equals("default");
    }

    /**
     * Delete source project directory. After this operation Project object shouldn't be used
     */
    public void delete(){
        FileUtils.deleteFile(source);
    }

    /**
     * @param string to be localised
     * @return localised string to default locale
     */
    public String localizeString(@Nullable String string){
        if(string == null)
            return "";
        if(!string.startsWith("@"))
            return string;
        if(!string.startsWith("@string/"))
            return null;
        return res.get(string);
    }

    /* Getters & Setters */

    /**
     * @param settings placed instead of old settings and aif saves
     */
    public void setSettings(AIF.ProjectSettings settings) {
        this.settings = settings;
        aif.setProjectSettings(settings);
        aif.save(true);
    }

    public ProjectConfiguration getProjectConfiguration(){
        return configuration;
    }

    /**
     * @return if error exists
     */
    public boolean isValid() {
        return error == 0;
    }
    public int getError() {
        return error;
    }
    @NonNull
    public File getSource() {
        return source;
    }
    @NonNull
    public AIF getAif() {
        return aif;
    }
    @NonNull
    public Res getRes() {
        return res;
    }
    @NonNull
    public AndroidManifest getManifest() {
        return manifest;
    }
    @NonNull
    public AIF.ProjectSettings getSettings() {
        return settings;
    }

    /* Getters for settings */

    public File getJavaDir(){
        return new File(settings.get("src"));
    }
    public File getManifestFile(){
        return new File(settings.get("manifest"));
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

    /* Getters for configuration */

    public void setName(String name){
        manifest.setName(name);
    }
    public void setVersionName(String versionName){
        manifest.setVersionName(versionName);
    }
    public void setVersionId(int versionId){
        manifest.setVersionCode(versionId);
    }
    public void setIconUI(Bitmap icon){
        ImageUtils.saveBitmap(icon, manifest.getIcon());
    }

    public String getName(){
        return configuration.getName();
    }
    public String getVersionName(){
        return configuration.getVersionName();
    }
    public int getVersionId(){
        return configuration.getVersionId();
    }
    public String getPackage(){
        return configuration.getPackage();
    }
    public String getFilePackage(File file){
        if(FileUtils.isChild(file, getJavaDir())){
            String relative_path = FileUtils.getRelativePath(file, getJavaDir()).replace('/', '.');
            String package_with_dot = getPackage() + "." + TextUtils.replaceLast(relative_path, file.getName(), "");
            return package_with_dot.substring(0, package_with_dot.length() - 1);
        }
        return "";
    }
    public File getIcon(){
        return configuration.getIconFile();
    }

    /**
     * Serialization by storing and reading file
     */
    private void readObject(ObjectInputStream stream) {
        try {
            String path = (String) stream.readObject();
            source = new File(path);
            reload();
        } catch (IOException | ClassNotFoundException e){
            Toast.show("Failed to read project");
        }
    }
    private void writeObject(ObjectOutputStream stream) {
        try {
            stream.writeObject(source.getAbsolutePath());
        } catch (IOException e){
            Toast.show("Failed to write project");
        }
    }

    /* Inner classes */

    /**
     * Class with new project meta-info
     */
    public static class NewProjectConfiguration {
        private String name;
        private String packag;
        private String versionName;
        private int versionId;
        private Bitmap icon;

        public NewProjectConfiguration(String name, String packag, String versionName, int versionId){
            this(name, packag, versionName, versionId, null);
        }
        public NewProjectConfiguration(String name, String packag, String versionName, int versionId, Bitmap icon) {
            this.name = name;
            this.packag = packag;
            this.versionName = versionName;
            this.versionId = versionId;
            this.icon = icon;
        }

        public String getName() {
            return name;
        }
        public String getPackage() {
            return packag;
        }
        public String getVersionName() {
            return versionName;
        }
        public int getVersionId() {
            return versionId;
        }
        public Bitmap getIcon() {
            return icon;
        }
    }

    /**
     * Class with all project meta-info
     */
    public static class ProjectConfiguration extends NewProjectConfiguration{
        private File icon;

        static ProjectConfiguration fromProject(Project project){
            AndroidManifest manifest = project.getManifest();
            return new ProjectConfiguration(manifest.getAppName(), manifest.getPackage(), manifest.getVersionName(), manifest.getVersionCode(), manifest.getIcon());
        }
        private ProjectConfiguration(String name, String packag, String versionName, int versionId, File iconFile) {
            super(name, packag, versionName, versionId);
            this.icon = iconFile;
        }

        public File getIconFile() {
            return icon;
        }
    }

    /**
     * Class with settings for project creator
     */
    private static class ProjectCreatorSettings{

        public static ProjectCreatorSettings getDefault(){
            return new ProjectCreatorSettings("MainActivity", 21, 25);
        }

        private String startActivityName;
        private int minSDK;
        private int targetSDK;

        private ProjectCreatorSettings(String startActivityName, int minSDK, int targetSDK) {
            this.startActivityName = startActivityName;
            this.minSDK = minSDK;
            this.targetSDK = targetSDK;
        }

        String getStartActivityName() {
            return startActivityName;
        }
        int getMinSDK() {
            return minSDK;
        }
        int getTargetSDK() {
            return targetSDK;
        }
    }

    /**
     * Class for create new project
     */
    private static class ProjectCreator {

        @NonNull
        static ProjectCreator with(@NonNull File file, @NonNull NewProjectConfiguration configuration){
            return new ProjectCreator(file, configuration);
        }

        static void generateActivityUI(File xml_file, File activity, String packag){
            FileUtils.writeFileUI(xml_file, FileUtils.readAssetsUI("texts/default_activity.xml"));
            FileUtils.writeFileUI(activity, String.format(FileUtils.readAssetsUI("texts/default_activity.java"),
                    packag, FileUtils.getFileName(activity), FileUtils.getFileName(xml_file)));
        }

        @NonNull
        private File source;
        @NonNull
        private NewProjectConfiguration configuration;
        @NonNull
        private ProjectCreatorSettings settings = ProjectCreatorSettings.getDefault();


        private ProjectCreator(@NonNull File source, @NonNull NewProjectConfiguration configuration){
            this.source = source;
            this.configuration = configuration;
        }

        public ProjectCreator applySettings(@NonNull ProjectCreatorSettings settings){
            this.settings = settings;
            return this;
        }

        public Project create(){
            generateFiles();

            Project project = new Project(source);
            project.load();

            return project;
        }

        private void generateFiles(){
            // Refreshing current directory isn't necessary because AIF.generateDefault() do it
            AIF.generateDefaultUI(source, configuration.getPackage());
            // Generating default files
            File root = new File(source, "app/src/main");
            FileUtils.refresh(root, true);

            File java = new File(root, "java/" + configuration.getPackage().replace('.', '/'));
            File res = new File(root, "res");
            File assets = new File(root, "assets");

            File layout = new File(res, "layout");
            File values = new File(res, "values");

            File main_activity = new File(java, "MainActivity.java");
            File main_activity_xml = new File(layout, "activity_main.xml");

            File strings = new File(values, "strings.xml");
            FileUtils.writeFileUI(strings, String.format(FileUtils.readAssetsUI("texts/values_default_texts/strings.xml"), configuration.getName()));

            File styles = new File(values, "styles.xml");
            FileUtils.writeFileUI(styles, FileUtils.readAssetsUI("texts/values_default_texts/styles.xml"));

            generateActivityUI(main_activity_xml, main_activity, configuration.getPackage());

            File manifestFile = new File(root, "AndroidManifest.xml");

            FileUtils.refresh(manifestFile, false);
            FileUtils.refresh(assets, true);

            AndroidManifest.fromFile(manifestFile)
                    .generateNewUI(configuration.getPackage(), configuration.getVersionName(), configuration.getVersionId(), settings.getStartActivityName(), settings.getMinSDK(), settings.getTargetSDK());
        }

    }

}
