package com.vipapp.appmark2.items.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.PADDING;
import static com.vipapp.appmark2.utils.Const.PADDING_START;

public class PaddingStart extends DesignAttribute {
    public PaddingStart() {
        super(PADDING_START);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingStart(view, AttributesUtils.valueToInt(getValue()));
    }
}
