package com.vipapp.appmark2.util;

import android.animation.ObjectAnimator;
import android.view.View;

public class Animation extends Utils {
    public static void animate(View v, String propName, float... values) {
        animate(v, propName, 300, values);
    }

    public static void animate(View v, String propName, long time, float... values) {
        ObjectAnimator.ofFloat(v, propName, values).setDuration(time).start();
    }

    public static void rotate(View v, float... values) {
        rotate(v, 300, values);
    }

    public static void rotate(View v, long time, float... values) {
        animate(v, "rotation", time, values);
    }

    public static void fadeIn(View v) {
        fadeIn(v, 150);
    }

    public static void fadeIn(View v, long time) {
        animate(v, "alpha", time, 0.0f, 1.0f);
    }

    public static void fadeOut(View v) {
        fadeOut(v, 150);
    }

    public static void fadeOut(View v, int time) {
        animate(v, "alpha", (long) time, 1.0f, 0.0f);
    }

    public static void scaleX(View v, float... values) {
        scaleX(v, 300, values);
    }

    public static void scaleX(View v, long time, float... values) {
        animate(v, "scaleX", time, values);
    }

    public static void scaleY(View v, float... values) {
        scaleY(v, 300, values);
    }

    public static void scaleY(View v, long time, float... values) {
        animate(v, "scaleY", time, values);
    }

    public static void scaleAll(View v, float... values) {
        scaleAll(v, 300, values);
    }

    public static void scaleAll(View v, long time, float... values) {
        scaleX(v, time, values);
        scaleY(v, time, values);
    }
}
