package com.vipapp.appmark2.alert;

import android.annotation.SuppressLint;
import androidx.annotation.StringRes;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.wrapper.Str;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.TextView;

public class LoadingDialog {

    @SuppressLint("StaticFieldLeak")
    private static AlertDialog dialog;
    private static TextView title;

    public static void show(boolean cancelable){
        Thread.ui(() -> {
            hide();
            dialog = AlertDialog.createFromResource(R.layout.loading_dialog);

            View view = dialog.getView();

            title = view.findViewById(R.id.title);

            dialog.setCancelable(cancelable);
            dialog.show();
        });
    }
    public static void show(){
        show(false);
    }

    public static void show(String message, boolean cancelable){
        show(cancelable);
        setTitle(message);
    }
    public static void show(@StringRes int res, boolean cancelable){
        show(cancelable);
        setTitle(res);
    }

    public static void show(String message){
        show(message, false);
    }
    public static void show(@StringRes int res){
        show(res, false);
    }

    public static void hide(){
        Thread.ui(() -> {
            try {
                if (dialog != null) dialog.cancel();
            } catch (Throwable ignored){}
            dialog = null;
        });
    }

    public static void setTitle(String message){
        Thread.ui(() -> {
            title.setVisibility(View.VISIBLE);
            title.setText(message);
        });
    }
    public static void setTitle(@StringRes int res){
        setTitle(Str.get(res));
    }

}
