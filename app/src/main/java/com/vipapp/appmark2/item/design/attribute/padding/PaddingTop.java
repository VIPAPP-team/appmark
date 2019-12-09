package com.vipapp.appmark2.item.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ViewUtils;

import static com.vipapp.appmark2.util.Const.PADDING_TOP;

public class PaddingTop extends DesignAttribute {
    public PaddingTop() {
        super(PADDING_TOP);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingTop(view, AttributesUtils.valueToInt(getValue()));
    }
}
