package com.vipapp.appmark2.alert;

import android.graphics.Bitmap;

import com.vipapp.appmark2.callback.Predicate;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.picker.ProjectPicker;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.Thread;

import java.io.File;

public class CreateProjectDialog {
    public static void show(PushCallback<Project> onCreateProject, Predicate<String> checkName){
        ProjectPicker picker = new ProjectPicker(project ->
                startProjectCreating(project.getName(), project.getPackage(), project.getVersionName(),
                        project.getVersionCode(), project.getIcon(), onCreateProject), checkName);
        picker.show();
    }
    private static void startProjectCreating(String app_name, String project_package, String version_name, int version_id, Bitmap icon, PushCallback<Project> onCreateProject){
        //Creating icon file
        Thread.start(() -> {
            String app_name_final = app_name.replace('/', '_');
            File place = new File(Const.PROJECT_STORAGE, app_name_final + Const.APP_ICON_DEFAULT);
            ImageUtils.saveBitmap(icon, place);
            Project.createNew(new File(new File(Const.PROJECT_STORAGE), app_name_final),
                    app_name, project_package, version_name,
                    version_id, "MainActivity", 21, onCreateProject);
        });
    }
}
