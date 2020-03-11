package com.vipapp.appmark2.item.editviewdialogitem;

import android.graphics.Color;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.picker.ColorPicker;
import com.vipapp.appmark2.picker.DrawablePicker;
import com.vipapp.appmark2.picker.StringChooser;
import com.vipapp.appmark2.picker.StringPicker;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.ColorUtils;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.drawables_chooser;

public abstract class DrawableOrColorEditViewDialogItem extends EditViewDialogItem {
    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue){
        pickAttribute(callback, project, defaultValue, false);
    }

    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue, boolean hideColorChoose) {
            StringChooser chooser = new StringChooser(item -> {
                switch (item.getType()){
                    case 0:
                        DrawablePicker picker = new DrawablePicker(callback, project);
                        picker.show();
                        break;
                    case 1:
                        ColorPicker colorPicker = new ColorPicker(result -> callback.onComplete(ColorUtils.toString(result)));
                        try {
                            colorPicker.setValue(Color.parseColor(defaultValue));
                        }catch(Exception ignored){}
                        colorPicker.show();
                        break;
                    case 2:
                        StringPicker stringPicker = new StringPicker(callback, true);
                        stringPicker.setTitle(R.string.enter_string);
                        stringPicker.show();
                        break;
                }
            });
            ArrayList<Item<String>> choose = new ArrayList<>(drawables_chooser);
            if(hideColorChoose) choose.remove(1);
            chooser.setArray(choose);
            chooser.show();
    }
}
