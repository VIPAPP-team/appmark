package com.vipapp.appmark2.items;

import android.content.Context;
import java.io.File;

public interface FileActionItem {
    void action(File file);

    String getPlaceholder(Context context);
}