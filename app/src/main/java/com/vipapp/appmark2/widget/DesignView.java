package com.vipapp.appmark2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.vipapp.appmark2.callback.Mapper;
import com.vipapp.appmark2.item.BuiltView;
import com.vipapp.appmark2.item.design.DesignObject;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.ThrowableUtils;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.xml.XMLObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.vipapp.appmark2.util.Const.SHOW_DESIGN_ERROR;

public class DesignView extends FrameLayout {

    public DesignView(@NonNull Context context) {
        super(context);
        setup(context, null);
    }
    public DesignView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup(context, attrs);
    }
    public DesignView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup(context, attrs);
    }
    public DesignView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup(context, attrs);
    }

    private void setup(@NonNull Context context, @Nullable AttributeSet attrs){
        if(attrs != null) setupWithAttrs(context, attrs);
    }

    private void setupWithAttrs(@NonNull Context context, @NonNull AttributeSet attrs){

    }

    public boolean buildDesign(XMLObject object, Project project, Mapper<BuiltView, View> onNewView){
        removeAllViews();
        try {
            DesignObject designObject = DesignObject.createNew(object);
            View v = designObject.setupView(designObject.getXMLObject(), project, onNewView);
            addView(v);
            return true;
        } catch (Throwable th){
            if(SHOW_DESIGN_ERROR)
                Toast.show(ThrowableUtils.toString(th));
            return false;
        }
    }

}
