package com.vipapp.appmark2.item.editviewdialogitem;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.picker.StringNumberPicker;
import com.vipapp.appmark2.project.Project;

import static com.vipapp.appmark2.util.Const.NUMBER_REGEX;

public abstract class UnitEditViewDialogItem extends EditViewDialogItem {
    public abstract String getDefaultUnit();
    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue) {
        StringNumberPicker picker =  new StringNumberPicker(value ->
                callback.onComplete(value.matches(NUMBER_REGEX)? value + getDefaultUnit(): value), true);
        picker.setTitle(R.string.enter_number);
        picker.setText(defaultValue);
        picker.show();
    }
}
