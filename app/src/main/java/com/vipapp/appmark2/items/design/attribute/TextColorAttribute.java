package com.vipapp.appmark2.items.design.attribute;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ClassUtils;

import static com.vipapp.appmark2.utils.Const.TEXT_COLOR_ATTR;

public class TextColorAttribute extends DesignAttribute {
    public TextColorAttribute() {
        super(TEXT_COLOR_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        ClassUtils.callMethod(view,
                "setTextColor",
                new Class[]{ int.class },
                new Object[]{ AttributesUtils.valueToColor(getValue(), resources) });
    }
}
