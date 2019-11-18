package com.vipapp.appmark2.items.design.attribute.padding;

import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ViewUtils;

import static com.vipapp.appmark2.utils.Const.PADDING;
import static com.vipapp.appmark2.utils.Const.PADDING_TOP;

public class PaddingTop extends DesignAttribute {
    public PaddingTop() {
        super(PADDING_TOP);
    }

    public void applyAction(View view, Res resources) {
        ViewUtils.setPaddingTop(view, AttributesUtils.valueToInt(getValue()));
    }
}
