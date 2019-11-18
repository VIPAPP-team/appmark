package com.vipapp.appmark2.items.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.PADDING;
import static com.vipapp.appmark2.utils.Const.PADDING_HORIZONTAL;

public class PaddingHorizontal extends DesignAttribute {
    public PaddingHorizontal() {
        super(PADDING_HORIZONTAL);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingHorizontal(view, AttributesUtils.valueToInt(getValue()));
    }
}
