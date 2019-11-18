package com.vipapp.appmark2.items.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.MARGIN;
import static com.vipapp.appmark2.utils.Const.MARGIN_VERTICAL;

public class MarginVertical extends DesignAttribute {

    public MarginVertical() {
        super(MARGIN_VERTICAL);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMarginVertical(view, AttributesUtils.valueToInt(getValue()));
    }
}
