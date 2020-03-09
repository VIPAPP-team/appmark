package com.vipapp.appmark2.activity;

import android.content.pm.PackageManager;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.widget.View;
import com.vipapp.appmark2.util.ActivityStarter;
import com.vipapp.appmark2.util.Animation;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.Permissions;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.util.thread.IntroductionThread;
import com.vipapp.appmark2.compiler.Compiler;
import com.vipapp.appmark2.util.wrapper.mSharedPreferences;

import androidx.annotation.NonNull;

import static com.vipapp.appmark2.util.Const.DEBUGGER_ENABLED_PREF;

public class IntroActivity extends BaseActivity {
    ImageView rotatable;

    @Override
    public Integer onCreateView() {
        return R.layout.activity_intro;
    }

    @Override
    public void findViews(){
        rotatable = f(R.id.rotatable);
    }

    @Override
    public void init() {
        initCompiler();
    }

    @Override
    public void setCallbacks() {
        rotatable.setOnLongClickListener(view -> {
            mSharedPreferences.get()
                    .edit()
                    .putBoolean(DEBUGGER_ENABLED_PREF, false)
                    .apply();
            Thread.setDefaultUncaughtExceptionHandler(null);
            Toast.show(R.string.debugger_disabled_msg);
            return false;
        });
    }

    @Override
    public void setup() {
        if(Permissions.check(this)) {
            FileUtils.setupFiles();
            Thread.startThread(IntroductionThread.class);
        }

        animateIcon();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults.length != Permissions.permissions.length) {
            exit_with_permission_error();
            return;
        }

        for(int result: grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                exit_with_permission_error();
                return;
            }
        }

        ActivityStarter.go("IntroActivity", true);

    }

    public void animateIcon(){
        Animation.rotate(rotatable, 600, 0f, 180f);
    }

    public void initCompiler(){
        if(Compiler.needInit())
            Compiler.init();
    }

}
