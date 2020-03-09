package com.vipapp.appmark2.item.editviewdialogitem;

import android.view.View;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.picker.StringChooser;
import com.vipapp.appmark2.project.Project;

import java.util.ArrayList;
import java.util.Collections;

public abstract class ChooseEditViewDialogItem extends EditViewDialogItem {

    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue) {
        StringChooser chooser = new StringChooser(item -> {
            String value = item.getInstance();
            callback.onComplete(value.equals("none")? "": value);
        });
        ArrayList<Item<String>> choose = new ArrayList<>(getChoose());
        choose.add(new Item<>("none"));
        chooser.setArray(choose);
        chooser.show();
    }

    public abstract ArrayList<Item<String>> getChoose();

}
