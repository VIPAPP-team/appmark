package com.vipapp.appmark2.activities;

import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.utils.Animation;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.ContextUtils;
import com.vipapp.appmark2.utils.Permissions;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.thread.IntroductionThread;
import com.vipapp.obfuscated.compiler.Compiler;

public class IntroActivity extends BaseActivity {
    ImageView rotatable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextUtils.updateActivity(this);
        setContentView(R.layout.activity_intro);

        if(Permissions.check(this))
            Thread.startThread(IntroductionThread.class);

        findViews();
        animateIcon();

        initCompiler();
    }

    public void findViews(){
        rotatable = findViewById(R.id.rotatable);
    }
    public void animateIcon(){
        Animation.rotate(rotatable, 600, 0f, 180f);
    }

    public void initCompiler(){
        if(Compiler.needInit())
            Compiler.init();
    }

}
