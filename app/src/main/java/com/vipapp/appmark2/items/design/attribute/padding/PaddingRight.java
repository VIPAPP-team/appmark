package com.vipapp.appmark2.items.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.PADDING;
import static com.vipapp.appmark2.utils.Const.PADDING_RIGHT;

public class PaddingRight extends DesignAttribute {
    public PaddingRight() {
        super(PADDING_RIGHT);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingRight(view, AttributesUtils.valueToInt(getValue()));
    }
}
