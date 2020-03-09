package com.vipapp.appmark2.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.ActivityResult;
import com.vipapp.appmark2.item.OnLoadItem;
import com.vipapp.appmark2.util.ContextUtils;
import com.vipapp.appmark2.util.DisplayUtils;
import com.vipapp.appmark2.util.Permissions;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.Toast;

import java.util.ArrayList;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    ArrayList<PushCallback<ActivityResult>> callbacks = new ArrayList<>();
    ArrayList<PushCallback<OnLoadItem>> onLoadCallbacks = new ArrayList<>();

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextUtils.updateActivity(this);
        setRequestedOrientation(DisplayUtils.isTablet(this)?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE:
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Integer resId = onCreateView();
        if(resId != null)
            setContentView(resId);

        createView();
        findViews();
        init();
        setCallbacks();
        setup();

    }

    // Marked as deprecated to do not forgot use f()
    @Override
    @Deprecated
    public <T extends View> T findViewById(int id) {
        return super.findViewById(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        for(PushCallback<ActivityResult> callback: callbacks){
            callback.onComplete(new ActivityResult(requestCode, resultCode, data));
        }
    }

    public void exit_with_permission_error(){
        Toast.show(R.string.permission_denied);
        finish();
    }

    // Method for other classes to push item
    public void onLoad(OnLoadItem item){
        // exec callbacks
        Thread.ui(() -> {
            for(PushCallback<OnLoadItem> callback: onLoadCallbacks){
                callback.onComplete(item);
            }
            onLoadCallback(item);
        });
    }
    // Method to override
    public void onLoadCallback(OnLoadItem item){}

    // Architecture methods
    @LayoutRes
    public Integer onCreateView(){ return null; }
    public void createView(){}
    public void findViews(){}
    public void setCallbacks(){}
    public void init(){}
    public void setup(){}

    public void addOnActivityResultCallback(PushCallback<ActivityResult> callback){
        callbacks.add(callback);
    }
    public void addOnLoadCallback(PushCallback<OnLoadItem> callback){
        onLoadCallbacks.add(callback);
    }

    public <T extends View> T f(@IdRes int resId){
        return super.findViewById(resId);
    }

}
