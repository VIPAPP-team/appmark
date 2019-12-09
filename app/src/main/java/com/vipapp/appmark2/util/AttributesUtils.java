package com.vipapp.appmark2.util;


import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.project.Res;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;
import static com.vipapp.appmark2.util.Const.gravity_string_to_int;

public class AttributesUtils {

    @SuppressWarnings("unchecked")
    public static <T> T convert(String value, Class<T> clazz, Res resources){
        if(clazz.equals(float.class))
            return (T) (Float) valueToFloat(value);

        if(clazz.equals(int.class))
            return (T) (Integer) valueToInt(value);

        if(clazz.equals(String.class) || clazz.equals(CharSequence.class))
            return (T) valueToString(value, resources);

        return (T) value;
    }

    public static String valueToString(String value, Res resources){
        String result = resources.get(value);
        return result == null? value: result;
    }

    public static int valueToGravity(String value){
        int result = 0;
        for(String gravity: value.split("\\|")){
            int current_gravity = getGravityForString(gravity);
            result = result == 0? current_gravity: result|current_gravity;
        }
        return result;
    }

    private static int getGravityForString(String value){
        //noinspection Convert2MethodRef
        return ClassUtils.getOrDefault(gravity_string_to_int.get(value), 0, object -> object != null);
    }

    public static int valueToOrientation(String value){
        if(value.equals("horizontal"))
            return HORIZONTAL;
        if(value.equals("vertical"))
            return VERTICAL;
        return HORIZONTAL;
    }

    public static int valueToVisibility(String value){
        switch (value){
            case "gone":
                return GONE;
            case "visible":
                return VISIBLE;
            case "invisible":
                return INVISIBLE;
        }
        return VISIBLE;
    }

    private static float valueToFloat(String value){
        float size;

        switch (value) {
            case "match_parent":
                return MATCH_PARENT;
            case "wrap_content":
                return  WRAP_CONTENT;
            case "fill_parent":
                //noinspection deprecation
                return FILL_PARENT;
            default:
                value = value.replaceFirst("f", "");
                if(value.contains("dp") || value.contains("dip"))
                    size = Float.parseFloat(value.replaceAll("(dp|dip)", "")) *
                            DisplayUtils.getScaledDensity();
                else
                    size = Float.parseFloat(value);
        }

        return size;
    }

    public static int valueToInt(String value){
        return (int)valueToFloat(value);
    }

    public static void valueToDrawableOrColor(String value, Res resources, PushCallback<Drawable> callback){
        if(value.contains("drawable/"))
            valueToDrawable(value, resources, callback);
        else {
            callback.onComplete(new ColorDrawable(valueToColor(value, resources)));
        }
    }

    public static int valueToColor(String value, Res resources){
        String color = value;

        if(value.contains("color/"))
            color = resources.get(color);

        try{
            return Color.parseColor(color);
        } catch (Exception e){
            return 0;
        }
    }

    private static void valueToDrawable(String value, Res resources, PushCallback<Drawable> callback){
        ImageUtils.load(resources.get(value), result ->
                callback.onComplete(new BitmapDrawable(com.vipapp.appmark2.util.wrapper.Res.get(), result)));
    }

    public static boolean attributeExists(String name){
        return getAttrId(name) != 0;
    }

    public static int getAttrId(String name){
        return ResourcesUtils.getAndroidIdentifier(name, "attr");
    }

}
