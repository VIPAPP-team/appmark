package com.vipapp.appmark2.picker.string;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.picker.StringPicker;
import com.vipapp.appmark2.util.FileUtils;

import com.vipapp.appmark2.R;

public class FileNamePicker extends StringPicker {
    public FileNamePicker(PushCallback<String> pushCallback) {
        super(pushCallback);
        setTitle(R.string.enter_filename);
        setHint("file.txt");
    }

    @Override
    public void pushItem(String str) {
        if (FileUtils.checkFileName(str)) {
            cancel();
            super.pushItem(str);
            return;
        }
        setError(R.string.filename_error);
    }
}
