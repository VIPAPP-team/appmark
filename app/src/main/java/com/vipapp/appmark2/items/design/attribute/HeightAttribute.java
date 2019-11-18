package com.vipapp.appmark2.items.design.attribute;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.HEIGHT_ATTR;

public class HeightAttribute extends DesignAttribute {
    public HeightAttribute() {
        super(HEIGHT_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        ViewUtils.setHeight(view, AttributesUtils.valueToInt(getValue()));
    }
}
