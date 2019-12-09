package com.vipapp.appmark2.item.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.PADDING_VERTICAL;

public class PaddingVertical extends DesignAttribute {
    public PaddingVertical() {
        super(PADDING_VERTICAL);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingVertical(view, AttributesUtils.valueToInt(getValue()));
    }
}
