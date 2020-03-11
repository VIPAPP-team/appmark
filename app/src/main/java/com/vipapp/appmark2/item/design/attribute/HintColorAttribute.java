package com.vipapp.appmark2.item.design.attribute;

import android.view.View;
import android.widget.EditText;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ClassUtils;

import static com.vipapp.appmark2.util.Const.HINT_COLOR_ATTR;

public class HintColorAttribute extends DesignAttribute {
    public HintColorAttribute() {
        super(HINT_COLOR_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        ClassUtils.callMethod(view, "setHintTextColor",
                new Class[]{
                        int.class
                },
                new Object[]{
                        AttributesUtils.valueToColor(getValue(), resources)
                });
    }
}
