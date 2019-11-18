package com.vipapp.appmark2.project;

import androidx.annotation.Nullable;

import com.vipapp.appmark2.utils.Const;

import java.io.File;

public class Drawables {
    private File source;
    Drawables(File source){
        this.source = source;
    }

    @Nullable
    public File getDrawable(String name){
        File[] matches = source.listFiles(file -> file.getName().matches(
                    String.format(Const.DRAWABLE_REGEX, name)));

        if (matches.length == 1)
            return matches[0];

        return null;
    }
}
