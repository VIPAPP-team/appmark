package com.vipapp.appmark2.items.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.MARGIN_RIGHT;

public class MarginRight extends DesignAttribute {

    public MarginRight() {
        super(MARGIN_RIGHT);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMarginRight(view, AttributesUtils.valueToInt(getValue()));
    }
}
