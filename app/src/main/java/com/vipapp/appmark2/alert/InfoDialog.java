package com.vipapp.appmark2.alert;

import androidx.annotation.StringRes;

import android.widget.ProgressBar;

import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.TextView;

public class InfoDialog extends LoadableInfoDialog {

    private TextView title;
    private TextView content;
    private TextView ok;
    private ProgressBar progressBar;

    // callback with callback to push String
    @SuppressWarnings("WeakerAccess")
    public InfoDialog(String title, String message){
        super(title, callback -> callback.onComplete(message));
    }

    public InfoDialog(@StringRes int title_res, String message){
        this(Str.get(title_res), message);
    }

}
