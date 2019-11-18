package com.vipapp.appmark2.picker.string;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.picker.StringPicker;
import com.vipapp.appmark2.utils.FileUtils;

import static com.vipapp.appmark2.utils.Const.LOCALE_REGEX;

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
