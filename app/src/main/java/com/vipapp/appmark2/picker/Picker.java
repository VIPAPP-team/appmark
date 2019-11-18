package com.vipapp.appmark2.picker;

import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.widget.AlertDialog;
import java.util.ArrayList;
import java.util.Iterator;

public class Picker<Type> extends AlertDialog {
    private ArrayList<PushCallback<Type>> callbacks = new ArrayList<>();

    Picker(PushCallback<Type> callback) {
        this.callbacks.add(callback);
    }

    protected void pushItem(Type item) {
        for(PushCallback<Type> callback: callbacks) {
            if(callback != null)
                callback.onComplete(item);
        }
    }

    public void addCallback(PushCallback<Type> callback) {
        this.callbacks.add(callback);
    }
}