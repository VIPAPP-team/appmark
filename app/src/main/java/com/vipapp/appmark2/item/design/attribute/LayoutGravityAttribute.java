package com.vipapp.appmark2.item.design.attribute;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;

import static com.vipapp.appmark2.util.Const.LAYOUT_GRAVITY_ATTR;

public class LayoutGravityAttribute extends DesignAttribute {
    public LayoutGravityAttribute() {
        super(LAYOUT_GRAVITY_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {}
}
