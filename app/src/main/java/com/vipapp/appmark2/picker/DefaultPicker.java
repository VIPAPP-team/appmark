package com.vipapp.appmark2.picker;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.widget.AlertDialog;
import java.util.ArrayList;

public class DefaultPicker<Type> extends AlertDialog {
    private ArrayList<PushCallback<Type>> callbacks = new ArrayList<>();

    DefaultPicker(PushCallback<Type> callback) {
        this.callbacks.add(callback);
    }
    public void addCallback(PushCallback<Type> callback) {
        this.callbacks.add(callback);
    }

    protected void pushItem(Type item) {
        for(PushCallback<Type> callback: callbacks) {
            if(callback != null)
                callback.onComplete(item);
        }
    }

}