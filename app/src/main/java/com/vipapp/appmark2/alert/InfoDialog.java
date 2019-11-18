package com.vipapp.appmark2.alert;

import androidx.annotation.StringRes;
import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.utils.wrapper.Str;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.TextView;

public class InfoDialog extends NetInfoDialog {

    private TextView title;
    private TextView content;
    private TextView ok;
    private ProgressBar progressBar;

    // callback with callback to push String
    public InfoDialog(String title, String message){
        super(title, callback -> callback.onComplete(message));
    }

    public InfoDialog(@StringRes int title_res, String message){
        this(Str.get(title_res), message);
    }

}
