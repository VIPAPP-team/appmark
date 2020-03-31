package com.vipapp.appmark2.item.editviewdialogitem;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.project.Project;

public abstract class DrawableEditViewDialogItem extends DrawableOrColorEditViewDialogItem {
    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue) {
        pickAttribute(callback, project, defaultValue, true);
    }
}
