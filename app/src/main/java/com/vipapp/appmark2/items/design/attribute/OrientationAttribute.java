package com.vipapp.appmark2.items.design.attribute;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ClassUtils;

import static com.vipapp.appmark2.utils.Const.ORIENTATION_ATTR;

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
