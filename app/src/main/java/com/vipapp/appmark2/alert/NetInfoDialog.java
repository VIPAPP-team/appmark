package com.vipapp.appmark2.alert;

import androidx.annotation.StringRes;

import android.text.Html;
import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.utils.wrapper.Str;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.TextView;

public class NetInfoDialog extends AlertDialog {

    private TextView title;
    private TextView content;
    private TextView ok;
    private ProgressBar progressBar;

    // callback with callback to push String
    public NetInfoDialog(String title, PushCallback<PushCallback<String>> callback){
        setView(R.layout.net_info_dialog);
        findViews(getView());
        setCallbacks(callback);
        this.title.setText(title);
    }

    public NetInfoDialog(@StringRes int title_res, PushCallback<PushCallback<String>> callback){
        this(Str.get(title_res), callback);
    }

    private void findViews(View v){
        title = v.findViewById(R.id.title);
        content = v.findViewById(R.id.content);
        ok = v.findViewById(R.id.ok);
        progressBar = v.findViewById(R.id.progressBar);
    }
    private void setCallbacks(PushCallback<PushCallback<String>> callback){
        callback.onComplete(string -> {
            this.content.setText(Html.fromHtml(string));
            progressBar.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);
        });
        ok.setOnClickListener(view -> cancel());
    }

}
