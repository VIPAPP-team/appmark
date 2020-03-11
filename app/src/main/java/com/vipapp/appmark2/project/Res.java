package com.vipapp.appmark2.project;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.vipapp.appmark2.manager.res.ColorsManager;
import com.vipapp.appmark2.manager.res.StringsManager;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.ResourcesUtils;
import com.vipapp.appmark2.util.Thread;
import com.vipapp.appmark2.util.ThreadLoader;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.vipapp.appmark2.util.Const.ANDROID_DRAWABLES;
import static com.vipapp.appmark2.util.Const.LOAD_TIME;
import static com.vipapp.appmark2.util.Const.REFERENCE_REGEX;
import static com.vipapp.appmark2.util.ResourcesUtils.getResType;

@SuppressWarnings("WeakerAccess")
public class Res extends ThreadLoader{
    private Resources androidRes = Resources.getSystem();

    @Nullable
    private StringsManager strings;
    @Nullable
    private ColorsManager colors;
    @Nullable
    private Drawables drawables;

    private File source;

    private Res(File file){
        source = file;
    }
    public static Res fromFile(File file){
        return new Res(file);
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
    public <T> T get(String name) {
        try {

            String withoutReference = removeReference(name);

            if (name.startsWith("@android:"))
                return getAndroidResource(name);

            String res_type = getResType(name);

            if (res_type != null) {
                switch (res_type) {
                    case "string":
                        return (T) getString(withoutReference);
                    case "color":
                        return (T) getColor(withoutReference);
                    case "drawable":
                        return (T) getDrawable(withoutReference);
                }
            }

        } catch (ClassCastException ignored) {
        }

        return null;
    }

    @Override
    public void load() {
        int[] loaded = new int[]{0, 2};
        stringsSetup(loaded);
        colorsSetup(loaded);
        drawablesSetup();
        while(loaded[0] != loaded[1]){ Thread.sleep(LOAD_TIME); }
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

    @SuppressWarnings("unchecked")
    @Nullable
    private <T> T getAndroidResource(String name){
        String res_type = getResType(name);
        if(res_type != null) {
            int id = ResourcesUtils.getAndroidIdentifier(name);
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

    private String colorToString(long color){
        return String.format("#%06X", (0xFFFFFF & color));
    }

    private String removeReference(String string){
        if(!string.startsWith("@")) return string;
        return string.replaceFirst(REFERENCE_REGEX, "");
    }

    @Nullable
    public StringsManager getStrings(){
        return strings;
    }

    @Nullable
    public ColorsManager getColors(){
        return colors;
    }

    @Nullable
    public Drawables getDrawables(){
        return drawables;
    }

}
