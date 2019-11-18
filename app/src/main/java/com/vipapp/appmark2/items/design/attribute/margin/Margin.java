package com.vipapp.appmark2.items.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.MARGIN;

public class Margin extends DesignAttribute {

    public Margin() {
        super(MARGIN);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMargins(view, AttributesUtils.valueToInt(getValue()));
    }
}
