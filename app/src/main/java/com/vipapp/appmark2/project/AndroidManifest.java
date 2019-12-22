package com.vipapp.appmark2.project;

import androidx.annotation.Nullable;

import com.vipapp.appmark2.util.CallableThreadLoader;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.xml.XMLObject;

import java.io.File;

@SuppressWarnings("WeakerAccess")
public class AndroidManifest extends CallableThreadLoader {
    private File source;

    @Nullable
    private XMLObject parsed_manifest;
    @Nullable
    private XMLObject parsed_application;

    private String text;
    private Res res;

    private AndroidManifest(File file){
        this.source = file;
    }

    public void attachProject(Project project){
        this.res = project.getResources();
    }

    public static AndroidManifest fromFile(File file){
        return new AndroidManifest(file);
    }

    public void generateNewUI(String project_package, String app_name, String version_name, int version_id, String first_activity, int minSDK){
        String manifest = FileUtils.readAssetsUI("texts/default_manifest.xml");
        assert manifest != null;
        manifest = String.format(manifest, project_package, app_name, first_activity, version_name,
                                version_id, minSDK, 25);
        text = manifest;
        FileUtils.writeFileUI(this.source, manifest);
    }

    private void parse(){
        parsed_manifest = XMLObject.parse(text);
        if(parsed_manifest != null){
            parsed_application = parsed_manifest.getNamedXMLObject("application");
        }
    }

    @Override
    public void load() {
        text = FileUtils.readFileUI(source);
        parse();
    }

    public File getFile(){
        return source;
    }

    public boolean isCorrect(){
        return parsed_manifest != null && parsed_application != null;
    }

    public void saveUI(){
        try {
            assert parsed_manifest != null;
            FileUtils.writeFileUI(source, parsed_manifest.toString());
        } catch (Exception ignored) {}
    }

    @Nullable
    public String getVersionName(){
        try {
            assert parsed_manifest != null;
            return parsed_manifest.getNamedAttribute(Const.VERSION_NAME).getValue();
        } catch (Exception e) {
            return null;
        }
    }
    @Nullable
    public String getPackage(){
        try {
            assert parsed_manifest != null;
            return parsed_manifest.getNamedAttribute(Const.PACKAGE).getValue();
        } catch (Exception e) {
            return null;
        }
    }
    public int getVersionCode(){
        try{
            assert parsed_manifest != null;
            String code = parsed_manifest.getNamedAttribute(Const.VERSION_ID).getValue();
            return Integer.parseInt(code == null? "0": code);
        } catch (Exception e){
            return -1;
        }
    }
    @Nullable
    public String getAppName(){
        try{
            assert parsed_application != null;
            return parsed_application.getNamedAttribute(Const.APP_NAME).getValue();
        } catch (Exception e) {
            return null;
        }
    }

    // Setters
    public void setName(String name){
        if(parsed_application != null) {
            parsed_application.setAttribute(Const.APP_NAME, name);
        }
    }
    public void setVersionName(String versionName){
        if(parsed_manifest != null) {
            parsed_manifest.setAttribute(Const.VERSION_NAME, versionName);
        }
    }
    public void setVersionCode(int versionCode){
        if(parsed_manifest != null) {
            parsed_manifest.setAttribute(Const.VERSION_ID, Integer.toString(versionCode));
        }
    }

    @Nullable
    public File getIcon(){
        try{
            assert parsed_application != null;
            return res.get(parsed_application.getNamedAttribute(Const.APP_ICON).getValue());
        } catch (Exception e) {
            return null;
        }
    }

}
