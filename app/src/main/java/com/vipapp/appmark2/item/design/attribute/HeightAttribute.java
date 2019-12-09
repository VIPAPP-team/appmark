package com.vipapp.appmark2.item.design.attribute;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.HEIGHT_ATTR;

public class HeightAttribute extends DesignAttribute {
    public HeightAttribute() {
        super(HEIGHT_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        ViewUtils.setHeight(view, AttributesUtils.valueToInt(getValue()));
    }
}
