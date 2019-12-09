package com.vipapp.appmark2.item.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.MARGIN_TOP;

public class MarginTop extends DesignAttribute {

    public MarginTop() {
        super(MARGIN_TOP);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMarginTop(view, AttributesUtils.valueToInt(getValue()));
    }
}
