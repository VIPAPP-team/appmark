package com.vipapp.appmark2.picker;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.widget.AlertDialog;
import java.util.ArrayList;

public class DefaultPicker<Type> extends AlertDialog {
    private boolean closeOnPick;
    private ArrayList<PushCallback<Type>> callbacks = new ArrayList<>();

    DefaultPicker(PushCallback<Type> callback) {
        this(callback, false);
    }
    DefaultPicker(PushCallback<Type> callback, boolean closeOnPick){
        this.callbacks.add(callback);
        this.closeOnPick = closeOnPick;
    }
    public void addCallback(PushCallback<Type> callback) {
        this.callbacks.add(callback);
    }

    protected void pushItem(Type item) {
        if(closeOnPick)
            cancel();
        for(PushCallback<Type> callback: callbacks) {
            if(callback != null)
                callback.onComplete(item);
        }
    }

}