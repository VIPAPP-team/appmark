package com.vipapp.appmark2.item.design.attribute;

import android.util.TypedValue;
import android.view.View;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;
import com.vipapp.appmark2.util.ClassUtils;
import com.vipapp.appmark2.widget.TextView;

import static com.vipapp.appmark2.util.Const.TEXT_SIZE_ATTR;

public class TextSizeAttribute extends DesignAttribute {
    public TextSizeAttribute() {
        super(TEXT_SIZE_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        ClassUtils.callMethod(view, "setTextSize",
                new Class[]{int.class, float.class},
                new Object[]{TypedValue.COMPLEX_UNIT_SP, AttributesUtils.valueToFloat(getValue())});
    }
}
