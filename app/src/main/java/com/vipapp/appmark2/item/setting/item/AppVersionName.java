package com.vipapp.appmark2.item.setting.item;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.setting.type.loadable.SimpleNetLoadableSetting;
import com.vipapp.appmark2.util.wrapper.mAppInfo;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.TextView;
import com.vipapp.appmark2.server.Server;

public class AppVersionName extends LoadableItem {

    public AppVersionName() {
        super(new SimpleNetLoadableSetting());
    }

    public void setup(View view) {
        // find views
        TextView value = view.findViewById(R.id.value);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        // setup views
        value.setText(mAppInfo.get().versionName);
        progressBar.setVisibility(View.VISIBLE);
        // load actual info
        Server.getActualVersion(version -> {
            if(version != null) {
                if(version.equals(mAppInfo.get().versionName)) {
                    value.setText(String.format(Str.get(R.string.actual_version_name), mAppInfo.get().versionName));
                } else {
                    value.setText(String.format(Str.get(R.string.newest_version_available), mAppInfo.get().versionName, version));
                }
            }
            progressBar.setVisibility(View.GONE);
        });
    }

    public String getSettingTitle(Context ctx) {
        return Str.get(R.string.app_version_name);
    }

    public String getSettingSubtitle(Context ctx) {
        return null;
    }
}
