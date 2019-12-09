package com.vipapp.appmark2.widget;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vipapp.appmark2.util.Animation;

public class FadeInLayout extends LinearLayout {

    public FadeInLayout(Context ctx){
        super(ctx);
        setup();
    }
    public FadeInLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }
    public FadeInLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }
    public FadeInLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setup();
    }

    //CREATE ENTER ANIMATION
    public void setup(){
        Animation.scaleAll(this, 600, 1.5f, 1);
        Animation.fadeIn(this, 600);
    }

}
