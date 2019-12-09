package com.vipapp.appmark2.item.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.PADDING_LEFT;

public class PaddingLeft extends DesignAttribute {
    public PaddingLeft() {
        super(PADDING_LEFT);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingLeft(view, AttributesUtils.valueToInt(getValue()));
    }
}