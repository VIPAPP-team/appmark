package com.vipapp.appmark2.items.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.MARGIN;
import static com.vipapp.appmark2.utils.Const.MARGIN_HORIZONTAL;

public class MarginHorizontal extends DesignAttribute {

    public MarginHorizontal() {
        super(MARGIN_HORIZONTAL);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMarginHorizontal(view, AttributesUtils.valueToInt(getValue()));
    }
}
