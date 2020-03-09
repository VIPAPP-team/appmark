package com.vipapp.appmark2.item;

import android.view.View;

import com.vipapp.appmark2.xml.XMLObject;

public class BuiltView {
    private View view;
    private XMLObject object;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public XMLObject getObject() {
        return object;
    }

    public void setObject(XMLObject object) {
        this.object = object;
    }

    public BuiltView(View view, XMLObject object) {
        this.view = view;
        this.object = object;
    }
}
