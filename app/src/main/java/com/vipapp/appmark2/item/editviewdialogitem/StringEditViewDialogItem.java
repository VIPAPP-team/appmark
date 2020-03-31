package com.vipapp.appmark2.item.editviewdialogitem;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.picker.StringPicker;
import com.vipapp.appmark2.project.Project;

public abstract class StringEditViewDialogItem extends EditViewDialogItem {

    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue) {
        StringPicker picker = new StringPicker(callback, true);
        picker.setTitle(R.string.enter_string);
        picker.setText(defaultValue);
        picker.show();
    }

}
