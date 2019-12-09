package com.vipapp.appmark2.util;

public class MathUtils {
    public static double getDistanceBetween(float x1, float y1, float x2, float y2){
        float distance_x = Math.abs(x1 - x2);
        float distance_y = Math.abs(y1 - y2);
        return Math.sqrt(distance_x * distance_x + distance_y * distance_y);
    }
}
