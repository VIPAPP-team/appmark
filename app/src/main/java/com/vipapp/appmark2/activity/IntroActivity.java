package com.vipapp.appmark2.activity;

import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.util.Animation;
import com.vipapp.appmark2.util.Permissions;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.thread.IntroductionThread;
import com.vipapp.appmark2.compiler.Compiler;

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
    public void setup() {
        if(Permissions.check(this))
            Thread.startThread(IntroductionThread.class);

        animateIcon();
    }

    public void animateIcon(){
        Animation.rotate(rotatable, 600, 0f, 180f);
    }

    public void initCompiler(){
        if(Compiler.needInit())
            Compiler.init();
    }

}
