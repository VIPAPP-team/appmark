package com.vipapp.appmark2.item.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.MARGIN_END;

public class MarginEnd extends DesignAttribute {

    public MarginEnd() {
        super(MARGIN_END);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMarginEnd(view, AttributesUtils.valueToInt(getValue()));
    }
}
