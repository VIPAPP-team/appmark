package com.vipapp.appmark2.items.design.attribute;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.WIDTH_ATTR;

public class WidthAttribute extends DesignAttribute {
    public WidthAttribute() {
        super(WIDTH_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        ViewUtils.setWidth(view, AttributesUtils.valueToInt(getValue()));
    }
}
