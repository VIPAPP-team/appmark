package com.vipapp.appmark2.items.design;

import android.view.View;

import com.vipapp.appmark2.project.Res;
import com.vipapp.appmark2.xml.XMLObject;

@SuppressWarnings("WeakerAccess")
public abstract class DesignAttribute {

    private String name;
    private String value;

    public DesignAttribute(String name){
        this.name = name;
    }

    public void apply(View view, XMLObject object, Res resources){
        if(name == null || (value = object.getNamedAttribute(name).getValue()) != null)
            applyAction(view, resources);
    }

    protected String getValue(){
        return value;
    }
    protected String getName(){
        return name;
    }

    public abstract void applyAction(View view, Res resources);

}
