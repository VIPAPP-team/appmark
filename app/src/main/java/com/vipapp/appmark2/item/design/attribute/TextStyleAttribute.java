package com.vipapp.appmark2.item.design.attribute;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.ClassUtils;

import static com.vipapp.appmark2.util.AttributesUtils.valueToTypeface;
import static com.vipapp.appmark2.util.Const.TEXT_STYLE_ATTR;

public class TextStyleAttribute extends DesignAttribute {
    public TextStyleAttribute() {
        super(TEXT_STYLE_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        if(view instanceof TextView){
            TextView textView = (TextView) view;
            textView.setTypeface(textView.getTypeface(), valueToTypeface(getValue()));
        }
    }
}
