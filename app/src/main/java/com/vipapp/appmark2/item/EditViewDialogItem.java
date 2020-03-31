package com.vipapp.appmark2.item;

import android.view.View;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.xml.XMLObject;

import androidx.annotation.DrawableRes;

public abstract class EditViewDialogItem {
    @DrawableRes
    public abstract int getImage();
    public abstract String getTitle();
    public abstract String getAttrName();
    public abstract void pickAttribute(PushCallback<String> callback, Project project, String defaultValue);
    public abstract boolean exists(View v);

    public void action(XMLObject object, Project project, String defaultValue, PushCallback<String> updateState){
        pickAttribute(attrValue -> {
            applyAttr(object, attrValue);
            updateState.onComplete(attrValue);
        }, project, defaultValue);
    }

    public void applyAttr(XMLObject object, String attrValue){
        if(attrValue == null || attrValue.equals(""))
            object.removeAttribute(getAttrName());
        else
            object.setAttribute(getAttrName(), attrValue);
    }

    public String getDisplayValue(XMLObject object){
        return object.getNamedAttribute(getAttrName()).getValue();
    }
}
