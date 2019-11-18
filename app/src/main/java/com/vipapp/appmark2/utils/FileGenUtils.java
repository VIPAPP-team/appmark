package com.vipapp.appmark2.utils;

import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.project.AndroidManifest;

import java.io.File;

public class FileGenUtils {

    public static void generateDefaultProject(File project, String project_package, String app_name, String version_name, int version_id, String first_activity, int minSDK, PushCallback<Void> callback){
        new Thread(() -> {
            try {
                File root = new File(project, "app/src/main");
                FileUtils.refresh(root, true);

                File java = new File(root, "java/" + project_package.replace('.', '/'));
                File res = new File(root, "res");
                File assets = new File(root, "assets");

                File layout = new File(res, "layout");
                File values = new File(res, "values");

                File main_activity = new File(java, "MainActivity.java");
                File main_activity_xml = new File(layout, "activity_main.xml");

                File strings = new File(values, "strings.xml");
                FileUtils.writeFileUI(strings, FileUtils.readAssetsUI("texts/values_default_texts/strings.xml"));

                File styles = new File(values, "styles.xml");
                FileUtils.writeFileUI(styles, FileUtils.readAssetsUI("texts/values_default_texts/styles.xml"));

                generateActivityUI(main_activity_xml, main_activity, project_package);

                File manifestFile = new File(root, "AndroidManifest.xml");

                FileUtils.refresh(manifestFile, false);
                FileUtils.refresh(assets, true);

                AndroidManifest manifest = AndroidManifest.fromFile(manifestFile);
                manifest.generateNewUI(project_package, app_name, version_name, version_id, first_activity, minSDK);
                Thread.ui(() -> callback.onComplete(null));
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void generateActivityUI(File xml_file, File activity, String packag){
        FileUtils.writeFileUI(xml_file, FileUtils.readAssetsUI("texts/default_activity.xml"));
        FileUtils.writeFileUI(activity, String.format(FileUtils.readAssetsUI("texts/default_activity.java"),
                packag, FileUtils.getFileName(activity), FileUtils.getFileName(xml_file)));
    }
}
