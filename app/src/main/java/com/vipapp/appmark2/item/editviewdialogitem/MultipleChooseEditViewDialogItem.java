package com.vipapp.appmark2.item.editviewdialogitem;

import com.google.common.base.Joiner;
import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.item.StringSelectableItem;
import com.vipapp.appmark2.picker.MultipleStringChooser;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.ArrayUtils;

import java.util.ArrayList;

public abstract class MultipleChooseEditViewDialogItem extends EditViewDialogItem {

    public abstract ArrayList<String> getChoose();

    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue) {
        String[] selected = defaultValue == null? new String[0]: defaultValue.split("\\|");
        ArrayList<StringSelectableItem> choose = new ArrayList<>();
        for(String string: getChoose())
            choose.add(new StringSelectableItem(string, ArrayUtils.in_array(selected, string)));

        MultipleStringChooser chooser = new MultipleStringChooser(result -> {
            ArrayList<String> selected_strings = new ArrayList<>();
            for(StringSelectableItem item: result)
                if(item.isSelected())
                    selected_strings.add(item.getText());
            callback.onComplete(Joiner.on("|").join(selected_strings));
        });
        chooser.setArray(choose);
        chooser.setTitle(R.string.select_gravity);
        chooser.show();
    }

}
