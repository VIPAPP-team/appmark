package com.vipapp.appmark2.alert.confirm;

import com.vipapp.appmark2.alert.ConfirmDialog;
import com.vipapp.appmark2.item.OnLoadItem;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.wrapper.mActivity;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.R;

import static com.vipapp.appmark2.util.Const.PROJECT_REMOVED;

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