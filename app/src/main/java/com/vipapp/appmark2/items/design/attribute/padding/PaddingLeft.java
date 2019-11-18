package com.vipapp.appmark2.items.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.PADDING_LEFT;

public class PaddingLeft extends DesignAttribute {
    public PaddingLeft() {
        super(PADDING_LEFT);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingLeft(view, AttributesUtils.valueToInt(getValue()));
    }
}