package com.vipapp.appmark2.item.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.MARGIN;

public class MarginAttribute extends DesignAttribute {

    public MarginAttribute() {
        super(MARGIN);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMargins(view, AttributesUtils.valueToInt(getValue()));
    }
}
