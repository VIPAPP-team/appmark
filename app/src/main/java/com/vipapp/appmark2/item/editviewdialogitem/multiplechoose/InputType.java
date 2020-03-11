package com.vipapp.appmark2.item.editviewdialogitem.multiplechoose;

import android.view.View;
import android.widget.EditText;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.MultipleChooseEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.input_type_choose;

public class InputType extends MultipleChooseEditViewDialogItem {
    @Override
    public ArrayList<String> getChoose() {
        return input_type_choose;
    }

    @Override
    public int getImage() {
        return R.drawable.input_type_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.input_type);
    }

    @Override
    public String getAttrName() {
        return "android:inputType";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setInputType", new Class[]{int.class});
    }
}
