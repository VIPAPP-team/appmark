package com.vipapp.appmark2.item.design.attribute;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ClassUtils;

import static com.vipapp.appmark2.util.Const.ORIENTATION_ATTR;

public class OrientationAttribute extends DesignAttribute {
    public OrientationAttribute() {
        super(ORIENTATION_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        ClassUtils.callMethod(view,
                "setOrientation",
                new Class[]{ int.class },
                new Object[]{ AttributesUtils.valueToOrientation(getValue()) });
    }
}
