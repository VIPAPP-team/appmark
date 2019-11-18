package com.vipapp.appmark2.items.design.attribute;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.vipapp.appmark2.items.design.DesignAttribute;
import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.utils.ImageUtils;

import static com.vipapp.appmark2.utils.Const.SRC_ATTR;

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
