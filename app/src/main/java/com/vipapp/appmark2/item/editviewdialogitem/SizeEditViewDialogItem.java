package com.vipapp.appmark2.item.editviewdialogitem;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.picker.StringChooser;
import com.vipapp.appmark2.picker.StringNumberPicker;
import com.vipapp.appmark2.project.Project;

import static com.vipapp.appmark2.util.Const.NUMBER_REGEX;
import static com.vipapp.appmark2.util.Const.SIZE_REGEX;
import static com.vipapp.appmark2.util.Const.size_chooser;

public abstract class SizeEditViewDialogItem extends EditViewDialogItem {
    public abstract String getDefaultUnit();
    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue) {
        StringChooser chooser = new StringChooser(item -> {
            if(item.getType() < 2)
                callback.onComplete(item.getInstance());
            else{
                StringNumberPicker picker =  new StringNumberPicker(value ->
                        callback.onComplete(value.matches(NUMBER_REGEX)? value + getDefaultUnit(): value), true);
                picker.setTitle(R.string.enter_number);
                if(defaultValue.matches(SIZE_REGEX))
                    picker.setText(defaultValue);
                picker.show();
            }
        });
        chooser.setArray(size_chooser);
        chooser.setTitle(R.string.select_size);
        chooser.show();
    }
}
