package com.vipapp.appmark2.item.design.attribute;

import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.item.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.util.ImageUtils;

import static com.vipapp.appmark2.util.Const.SRC_ATTR;

public class SrcAttribute extends DesignAttribute {
    public SrcAttribute() {
        super(SRC_ATTR);
    }

    @Override
    public void applyAction(View view, Res resources) {
        if(view instanceof ImageView)
            ImageUtils.loadInto(resources.get(getValue()), (ImageView)view);
    }
}
