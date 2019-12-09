package com.vipapp.appmark2.item.design.attribute;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ClassUtils;

import static com.vipapp.appmark2.util.Const.TEXT_COLOR_ATTR;

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
