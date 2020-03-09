package com.vipapp.appmark2.item.editviewdialogitem.multiplechoose;

import android.view.View;
import android.widget.EditText;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.editviewdialogitem.MultipleChooseEditViewDialogItem;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.util.wrapper.Str;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.ime_option_choose;

public class ImeOption extends MultipleChooseEditViewDialogItem {
    @Override
    public ArrayList<String> getChoose() {
        return ime_option_choose;
    }

    @Override
    public int getImage() {
        return R.drawable.input_type_icon;
    }

    @Override
    public String getTitle() {
        return Str.get(R.string.ime_option);
    }

    @Override
    public String getAttrName() {
        return "android:imeOptions";
    }

    @Override
    public boolean exists(View v) {
        return ClassUtils.hasMethod(v, "setImeOptions", new Class[]{int.class});
    }
}
