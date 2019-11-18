package com.vipapp.appmark2.items.setting.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.items.setting.type.loadable.SimpleNetLoadableSetting;
import com.vipapp.appmark2.utils.wrapper.Str;
import com.vipapp.appmark2.widget.TextView;
import com.vipapp.obfuscated.server.Server;

public class ServerStatus extends LoadableItem {

    public ServerStatus() {
        super(new SimpleNetLoadableSetting());
    }

    @SuppressLint("SetTextI18n")
    public void setup(View view) {
        // find views
        TextView value = view.findViewById(R.id.value);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        // setup views
        Server.getServerOnline(online -> {
            if(online) {
                value.setText("OK");
                // setting green color to textView
                value.setTextColor(0xff00ff00);
            } else {
                value.setText(Str.get(R.string.offline));
                // setting red color to textView
                value.setTextColor(0xffff0000);
            }
            progressBar.setVisibility(View.GONE);
        });
    }
    public String getSettingTitle(Context ctx) {
        return Str.get(R.string.server_status);
    }

    public String getSettingSubtitle(Context ctx) {
        return null;
    }
}
