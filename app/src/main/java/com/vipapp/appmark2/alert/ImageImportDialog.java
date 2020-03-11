package com.vipapp.appmark2.alert;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.picker.ImagePicker;
import com.vipapp.appmark2.picker.string.FileNamePicker;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.Toast;

import java.io.File;

public class ImageImportDialog {
    public static void show(File directory, PushCallback<File> result){
        ImagePicker picker = new ImagePicker(image -> {
            FileNamePicker fileNamePicker = new FileNamePicker(string -> {
                try {
                    // Add to string ".png" if filename not contains dot
                    File file = new File(directory,
                            string.indexOf('.') == -1 ? string + ".png" : string);
                    FileUtils.refresh(file, false);
                    ImageUtils.saveBitmap(image.getBitmap(), file);
                    result.onComplete(file);
                } catch (Exception e){
                    Toast.show(R.string.filename_error);
                }
            });
            fileNamePicker.show();
        });
        picker.show();
    }
}
