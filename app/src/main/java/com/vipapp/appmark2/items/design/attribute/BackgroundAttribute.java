package com.vipapp.appmark2.items.design.attribute;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.AttributesUtils;
import com.vipapp.appmark2.utils.ImageUtils;

import static com.vipapp.appmark2.utils.Const.BACKGROUND_ATTR;

public class BackgroundAttribute extends DesignAttribute {

    public BackgroundAttribute() {
        super(BACKGROUND_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        AttributesUtils.valueToDrawableOrColor(getValue(), resources, view::setBackground);
    }
}
