package com.vipapp.appmark2.alert;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.TextView;

public class ConfirmDialog {
    public static void show(String title_text, PushCallback<Boolean> result){
        show(title_text, null, null, result);
    }

    public static void show(String title_text, String text_confirm, String text_cancel, PushCallback<Boolean> result){
        AlertDialog dialog = AlertDialog.createFromResource(R.layout.confirm_dialog);
        View v = dialog.getView();
        // Find views
        TextView title = v.findViewById(R.id.title);
        TextView confirm = v.findViewById(R.id.confirm);
        TextView cancel = v.findViewById(R.id.cancel);
        // Set callbacks
        confirm.setOnClickListener(view -> {
            result.onComplete(true);
            dialog.cancel();
        });
        cancel.setOnClickListener(view -> {
            result.onComplete(false);
            dialog.cancel();
        });
        // Set texts
        title.setText(title_text);
        if(text_confirm != null) confirm.setText(text_confirm);
        if(text_cancel != null) cancel.setText(text_cancel);

        dialog.show();
    }
}
