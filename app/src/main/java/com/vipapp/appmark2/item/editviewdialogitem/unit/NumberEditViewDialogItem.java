package com.vipapp.appmark2.item.editviewdialogitem.unit;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.item.editviewdialogitem.UnitEditViewDialogItem;
import com.vipapp.appmark2.picker.StringNumberPicker;
import com.vipapp.appmark2.project.Project;

public abstract class NumberEditViewDialogItem extends UnitEditViewDialogItem {
    @Override
    public String getDefaultUnit() {
        return "";
    }
}
