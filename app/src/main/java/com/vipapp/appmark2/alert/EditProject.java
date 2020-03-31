package com.vipapp.appmark2.alert;

import com.vipapp.appmark2.item.OnLoadItem;
import com.vipapp.appmark2.item.OnProjectEdited;
import com.vipapp.appmark2.picker.ProjectPicker;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.wrapper.mActivity;

public class EditProject {
    public static void show(Project project, int i){
        ProjectPicker picker = new ProjectPicker(projectConfig -> {
            project.setName(projectConfig.getName());
            project.setVersionName(projectConfig.getVersionName());
            project.setVersionId(projectConfig.getVersionId());
            Thread.start(() -> {
                project.setIconUI(projectConfig.getIcon());
                project.getManifest().save(none -> mActivity.get().onLoad(new OnLoadItem(Const.PROJECT_EDITED, new OnProjectEdited(project, i))));
            });
        }, project.getProjectConfiguration(), none -> true);
        picker.show();
    }
}
