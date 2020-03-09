package com.vipapp.appmark2.item.editviewdialogitem;

import android.graphics.Color;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.picker.ColorPicker;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.ColorUtils;
import com.vipapp.appmark2.util.Toast;

public abstract class ColorEditViewDialogItem extends EditViewDialogItem {
    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue) {
        ColorPicker picker = new ColorPicker(color -> callback.onComplete(ColorUtils.toString(color)));
        try {
            picker.setValue(Color.parseColor(defaultValue));
        }catch(Exception ignored){}
        picker.show();
    }
}
