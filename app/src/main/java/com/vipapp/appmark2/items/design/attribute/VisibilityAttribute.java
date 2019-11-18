package com.vipapp.appmark2.items.design.attribute;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;

import static com.vipapp.appmark2.utils.Const.VISIBILITY_ATTR;

public class VisibilityAttribute extends DesignAttribute {
    public VisibilityAttribute() {
        super(VISIBILITY_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        view.setVisibility(AttributesUtils.valueToVisibility(getValue()));
    }
}
