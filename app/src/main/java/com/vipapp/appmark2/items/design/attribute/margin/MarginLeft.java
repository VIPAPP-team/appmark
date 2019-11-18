package com.vipapp.appmark2.items.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.MARGIN_LEFT;

public class MarginLeft extends DesignAttribute {

    public MarginLeft() {
        super(MARGIN_LEFT);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMarginLeft(view, AttributesUtils.valueToInt(getValue()));
    }
}
