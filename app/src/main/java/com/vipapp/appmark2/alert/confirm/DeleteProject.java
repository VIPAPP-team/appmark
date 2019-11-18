package com.vipapp.appmark2.alert.confirm;

import com.vipapp.appmark2.alert.ConfirmDialog;
import com.vipapp.appmark2.items.OnLoadItem;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.utils.wrapper.mActivity;
import com.vipapp.appmark2.utils.wrapper.Str;
import com.vipapp.appmark2.R;

import static com.vipapp.appmark2.utils.Const.PROJECT_REMOVED;

public class DeleteProject {
    public static void show(Project project, int i) {
        ConfirmDialog.show(Str.get(R.string.delete_project_warn), result -> {
            if (result) {
                project.delete();
                mActivity.get().onLoad(new OnLoadItem(PROJECT_REMOVED, i));
            }
        });
    }
}