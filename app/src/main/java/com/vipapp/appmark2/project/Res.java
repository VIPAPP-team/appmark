package com.vipapp.appmark2.project;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import com.vipapp.appmark2.manager.res.ColorsManager;
import com.vipapp.appmark2.manager.res.StringsManager;
import com.vipapp.appmark2.utils.FileUtils;
import com.vipapp.appmark2.utils.ImageUtils;
import com.vipapp.appmark2.utils.ResourcesUtils;
import com.vipapp.appmark2.utils.TextUtils;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.ThreadLoader;
import com.vipapp.appmark2.utils.Toast;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vipapp.appmark2.utils.Const.ANDROID_DRAWABLES;
import static com.vipapp.appmark2.utils.Const.LOAD_TIME;
import static com.vipapp.appmark2.utils.Const.REFERENCE_REGEX;

public class Res extends ThreadLoader{
    private Resources androidRes = Resources.getSystem();

    @Nullable
    private StringsManager strings;
    @Nullable
    private ColorsManager colors;
    @Nullable
    private Drawables drawables;

    private File source;
    public static Res fromFile(File file){
        return new Res(file);
    }
    private Res(File file){
        source = file;
    }

    @Override
    public void load(Object... args) {
        int[] loaded = new int[]{0, 2};
        stringsSetup(loaded);
        colorsSetup(loaded);
        drawablesSetup();
        while(loaded[0] != loaded[1]){ Thread.sleep(LOAD_TIME); }
    }

    private void stringsSetup(int[] loaded){
        File strings_file = new File(source, "values/strings.xml");
        strings = new StringsManager(strings_file);
        strings.exec(none -> loaded[0]++);
    }
    private void colorsSetup(int[] loaded){
        File colors_file = new File(source, "values/colors.xml");
        colors = new ColorsManager(colors_file);
        colors.exec(none -> loaded[0]++);
    }
    private void drawablesSetup(){
        File file = new File(source, "drawable");
        drawables = new Drawables(file);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public <T> T get(String name){
        try {

            String withoutReference = removeReference(name);

            if (name.startsWith("@android:"))
                return getAndroidResource(name);

            String res_type = getResType(name);

            if(res_type != null) {
                switch (res_type){
                    case "string":
                        return (T) getString(withoutReference);
                    case "color":
                        return (T) getColor(withoutReference);
                    case "drawable":
                        return (T) getDrawable(withoutReference);
                }
            }

        } catch (ClassCastException ignored){}

        return null;
    }

    private String getString(String string){
        return strings == null? null: strings.get(string);
    }
    private String getColor(String colorName){
        return colors == null? null: colors.get(colorName);
    }
    private File getDrawable(String name){
        return drawables == null? null: drawables.getDrawable(name);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private <T> T getAndroidResource(String name){
        String res_type = getResType(name);
        if(res_type != null) {
            int id = ResourcesUtils.getAndroidIdentifier(name, res_type);
            if(id != 0) {
                switch (res_type) {
                    case "string":
                        return (T) androidRes.getString(id);
                    case "color":
                        return (T) colorToString(androidRes.getColor(id));
                    case "drawable":
                        return (T) createAndroidDrawable(id, name);
                }
            }
        }
        return null;
    }

    private File createAndroidDrawable(int id, String name){
        File file = new File(ANDROID_DRAWABLES, name);
        if(file.exists())
            return file;
        else {
            FileUtils.refresh(file.getParentFile(), true);
            ImageUtils.saveBitmap(BitmapFactory.decodeResource(androidRes, id), file);
        }
        return file;
    }

    private String colorToString(long color){
        return String.format("#%06X", (0xFFFFFF & color));
    }

    @Nullable
    private String getResType(String name){
        Matcher matcher = Pattern.compile(REFERENCE_REGEX).matcher(name);
        if(matcher.find()) {
            String res_type = matcher.group();
            return res_type.replaceAll("@(android:)?", "").replaceAll("/", "");
        }
        return null;
    }

    private String removeReference(String string){
        if(!string.startsWith("@")) return string;
        return string.replaceFirst(REFERENCE_REGEX, "");
    }

}
