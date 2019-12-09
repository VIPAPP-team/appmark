package com.vipapp.appmark2.item.design.attribute;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;

import static com.vipapp.appmark2.util.Const.BACKGROUND_ATTR;

public class BackgroundAttribute extends DesignAttribute {

    public BackgroundAttribute() {
        super(BACKGROUND_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        AttributesUtils.valueToDrawableOrColor(getValue(), resources, view::setBackground);
    }
}
