package com.vipapp.appmark2.picker.string;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.picker.StringPicker;

import static com.vipapp.appmark2.util.Const.LOCALE_REGEX;

public class LocalePicker extends StringPicker {

    public LocalePicker(PushCallback<String> callback) {
        super(callback);
        setTitle(R.string.enter_locale_name);
        setHint("en, ru, uk");
    }

    @Override
    public void pushItem(String string){
        if (string.matches(LOCALE_REGEX)) {
            cancel();
            super.pushItem(string);
            return;
        }
        setError(R.string.locale_name_error);
    }

}
