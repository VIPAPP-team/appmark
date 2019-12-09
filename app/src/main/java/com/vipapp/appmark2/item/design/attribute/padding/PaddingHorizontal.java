package com.vipapp.appmark2.item.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.PADDING_HORIZONTAL;

public class PaddingHorizontal extends DesignAttribute {
    public PaddingHorizontal() {
        super(PADDING_HORIZONTAL);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingHorizontal(view, AttributesUtils.valueToInt(getValue()));
    }
}
