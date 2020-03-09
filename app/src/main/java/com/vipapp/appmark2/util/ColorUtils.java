package com.vipapp.appmark2.util;

import android.graphics.Color;

public class ColorUtils {
    public static int[] toARGB(int color){
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return new int[]{alpha, red, green, blue};
    }
    public static String toString(int color){
        return String.format("#%08X", color);
    }
}
