package com.vipapp.appmark2.alert.confirm;

import com.vipapp.appmark2.alert.ConfirmDialog;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.R;

import java.io.File;

public class DeleteFileDialog {

    public static void show(File file, PushCallback<Void> pushCallback) {
        ConfirmDialog.show(Str.get(R.string.delete_file_warn),result -> {
            if(result) {
                FileUtils.deleteFile(file);
                pushCallback.onComplete(null);
            }
        });
    }
}