package com.vipapp.appmark2.project;

import androidx.annotation.Nullable;

import com.vipapp.appmark2.util.Const;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static com.vipapp.appmark2.util.Const.DRAWABLE_REGEX;

@SuppressWarnings("WeakerAccess")
public class Drawables {
    private File source;
    Drawables(File source){
        this.source = source;
    }

    public ArrayList<File> getDrawables(){
        return new ArrayList<>(Arrays.asList(source.listFiles(file -> file.getName().matches(DRAWABLE_REGEX))));
    }

    @Nullable
    public File getDrawable(String name){
        File[] matches = source.listFiles(file -> file.getName().matches(
                    String.format(Const.NAMED_DRAWABLE_REGEX, name)));

        if (matches.length == 1)
            return matches[0];

        return null;
    }
}
