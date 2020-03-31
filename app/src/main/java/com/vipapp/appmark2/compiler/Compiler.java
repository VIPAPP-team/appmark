package com.vipapp.appmark2.compiler;

import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.project.AIF.ProjectSettings;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.wrapper.mAssets;
import com.vipapp.appmark2.util.wrapper.mContext;
import com.vipapp.fjc.*;

import java.io.File;

import static com.vipapp.appmark2.util.Const.AAPT_STORAGE;
import static com.vipapp.appmark2.util.Const.ANDROID_JAR_STORAGE;

@SuppressWarnings("WeakerAccess")
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

    public static void compileRelease(Project project, ApkBuilderCert cert, ApkBuilderCallBack callback){
        ProjectSettings settings = project.getSettings();
        File assets = new File(settings.get("assets"));
        FileUtils.refresh(assets, true);
        ApkBuilderConfig config = new ApkBuilderConfig(AAPT_STORAGE, ANDROID_JAR_STORAGE,
                settings.get("src"), settings.get("res"), assets.getAbsolutePath(),
                settings.get("manifest"), null, null, settings.get("build"), cert);
        new ApkBuilder(config, callback).build();
    }

    public static void compileDebug(Project project, ApkBuilderCallBack callback){
        compileRelease(project, null, callback);
    }

}
