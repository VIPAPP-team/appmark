package com.vipapp.appmark2.alert;

import com.vipapp.appmark2.items.OnLoadItem;
import com.vipapp.appmark2.items.OnProjectEdited;
import com.vipapp.appmark2.picker.ProjectPicker;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.wrapper.mActivity;

public class EditProject {
    public static void show(Project project, int i){
        ProjectPicker picker = new ProjectPicker(projectItem -> {
            project.setName(projectItem.getName());
            project.setVersionName(projectItem.getVersionName());
            project.setVersionCode(projectItem.getVersionCode());
            Thread.start(() -> {
                project.setIconUI(projectItem.getIcon());
                project.save(none ->
                        mActivity.get().onLoad(new OnLoadItem(Const.PROJECT_EDITED, new OnProjectEdited(project, i))));
            });
        }, project.getProjectItem(false), none -> true);
        picker.show();
    }
}
