package com.vipapp.appmark2.item.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.PADDING_START;

public class PaddingStart extends DesignAttribute {
    public PaddingStart() {
        super(PADDING_START);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingStart(view, AttributesUtils.valueToInt(getValue()));
    }
}
