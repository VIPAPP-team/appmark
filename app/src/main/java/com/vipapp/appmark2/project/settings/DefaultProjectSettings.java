package com.vipapp.appmark2.project.settings;

import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.project.ProjectSettings;
import com.vipapp.appmark2.utils.Const;

import java.io.File;

public class DefaultProjectSettings extends ProjectSettings {

    public DefaultProjectSettings(File dir, String project_package) {
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
