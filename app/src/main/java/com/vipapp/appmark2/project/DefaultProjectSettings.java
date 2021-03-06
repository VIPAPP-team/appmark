package com.vipapp.appmark2.project;

import com.vipapp.appmark2.util.Const;

import java.io.File;

class DefaultProjectSettings extends AIF.ProjectSettings {

    DefaultProjectSettings(File dir, String project_package) {
        File java = new File(dir, Const.getJavaDir(project_package));
        File res = new File(dir, Const.RES);
        File assets = new File(dir, Const.ASSETS);
        File manifest = new File(dir, Const.ANDROID_MANIFEST_LOCATION);
        File build = new File(dir, Const.BUILD_DIR);

        put("src", java.getAbsolutePath());
        put("res", res.getAbsolutePath());
        put("assets", assets.getAbsolutePath());
        put("manifest", manifest.getAbsolutePath());
        put("build", build.getAbsolutePath());

    }

}
