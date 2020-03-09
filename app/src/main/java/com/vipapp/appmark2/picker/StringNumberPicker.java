package com.vipapp.appmark2.picker;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;

import static com.vipapp.appmark2.util.Const.SIZE_REGEX;

public class StringNumberPicker extends StringPicker {
    public StringNumberPicker(PushCallback<String> callback) {
        super(callback, false);
    }

    public StringNumberPicker(PushCallback<String> callback, boolean closeOnPick){
        super(callback, closeOnPick);
    }

    @Override
    protected void pushItem(String item) {
        if(item.matches(SIZE_REGEX) || item.equals("")) {
            super.pushItem(item);
        } else {
            setError(R.string.incorrect_number);
        }
    }
}
