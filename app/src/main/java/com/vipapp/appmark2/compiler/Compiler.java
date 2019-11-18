package com.vipapp.appmark2.compiler;

import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.project.ProjectSettings;
import com.vipapp.appmark2.utils.wrapper.mAssets;
import com.vipapp.appmark2.utils.wrapper.mContext;
import com.vipapp.fjc.*;

import java.io.File;

import static com.vipapp.appmark2.utils.Const.AAPT_STORAGE;
import static com.vipapp.appmark2.utils.Const.ANDROID_JAR_STORAGE;

public class Compiler {

    public static ApkBuilderInit get(){
        return ApkBuilderInit.INSTANCE;
    }

    public static void init(){
        get().initAAPT(mAssets.get(), mContext.get().getFilesDir().getPath());
        get().copyAndroidJar(mAssets.get(), mContext.get().getFilesDir().getPath());
    }
    public static boolean needInit(){
        return !new File(ANDROID_JAR_STORAGE).exists();
    }

    public static void compileDebug(Project project, ApkBuilderCallBack callBack){
        compileRelease(project, null, callBack);
    }
    public static void compileRelease(Project project, ApkBuilderCert cert, ApkBuilderCallBack callBack){
        ProjectSettings settings = project.getSettings();
        ApkBuilderConfig config = new ApkBuilderConfig(AAPT_STORAGE, ANDROID_JAR_STORAGE,
                settings.get("src"), settings.get("res"), settings.get("assets"),
                settings.get("manifest"), null, null, settings.get("build"), cert);
        new ApkBuilder(config, callBack).build();
    }

}
