package com.vipapp.appmark2.items.design.attribute.margin;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.MARGIN;
import static com.vipapp.appmark2.utils.Const.MARGIN_END;

public class MarginEnd extends DesignAttribute {

    public MarginEnd() {
        super(MARGIN_END);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setMarginEnd(view, AttributesUtils.valueToInt(getValue()));
    }
}
