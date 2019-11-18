package com.vipapp.appmark2.alert;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callbacks.Predicate;
import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.picker.ImagePicker;
import com.vipapp.appmark2.picker.ProjectPicker;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.ContextUtils;
import com.vipapp.appmark2.utils.FileUtils;
import com.vipapp.appmark2.utils.ImageUtils;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.Toast;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.EditText;
import com.vipapp.appmark2.widget.TextView;

import java.io.File;
import java.net.URLEncoder;
import java.util.Objects;

public class CreateProjectFirstDialog {
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
                    app_name, project_package, place, version_name,
                    version_id, "MainActivity", 21, onCreateProject);
        });
    }
}
