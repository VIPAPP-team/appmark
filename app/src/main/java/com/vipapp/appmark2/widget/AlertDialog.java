package com.vipapp.appmark2.widget;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.util.ContextUtils;
import com.vipapp.appmark2.util.wrapper.mContext;

import java.util.Objects;

import static android.view.WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

public class AlertDialog {
    private android.app.AlertDialog dialog;
    private View dialog_view;

    public AlertDialog(){
        // create and setup alert dialog
        dialog = new android.app.AlertDialog.Builder(mContext.get()).create();
        Window dialog_window = dialog.getWindow();
        if(dialog_window != null) {
            dialog_window.setBackgroundDrawableResource(R.drawable.round_white);
        }
        setCallbacks();
    }

    private void setCallbacks(){
        dialog.setOnDismissListener(dialogInterface -> onDismiss());
    }

    public void setView(View view){
        dialog_view = view;
        dialog.setView(view);
    }

    public void setView(int resource){
        setView(ContextUtils.activity.getLayoutInflater().inflate(resource, null));
    }

    public void setBackground(Drawable background){
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(background);
    }
    public void setBackground(int color){
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(color));
    }

    public void show(){
        dialog.show();
        Objects.requireNonNull(dialog.getWindow()).clearFlags(FLAG_NOT_FOCUSABLE|FLAG_ALT_FOCUSABLE_IM);
    }
    public void cancel(){
        dialog.cancel();
    }
    public void setCancelable(boolean cancelable){
        dialog.setCancelable(cancelable);
    }

    public View getView(){
        return dialog_view;
    }
    public Window getWindow(){
        return dialog.getWindow();
    }

    public static AlertDialog createFromView(View view){
        AlertDialog dialog = new AlertDialog();
        dialog.setView(view);
        return dialog;
    }
    public static AlertDialog createFromResource(int resource){
        AlertDialog dialog = new AlertDialog();
        dialog.setView(resource);
        return dialog;
    }

    public void onDismiss(){}

}
