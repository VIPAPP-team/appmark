package com.vipapp.appmark2.item.design.attribute;

import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.AttributesUtils;

import static com.vipapp.appmark2.util.Const.SCALE_TYPE_ATTR;

public class ScaleTypeAttribute extends DesignAttribute {
    public ScaleTypeAttribute() {
        super(SCALE_TYPE_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        if(view instanceof ImageView)
            ((ImageView)view).setScaleType(AttributesUtils.valueToScaleType(getValue()));
    }
}
