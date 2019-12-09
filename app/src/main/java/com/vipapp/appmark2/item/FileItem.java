package com.vipapp.appmark2.item;

import android.content.Context;
import android.graphics.Bitmap;

public abstract class FileItem {
    public abstract Bitmap getImage(Context context);

    public abstract String getString(Context context);

    public abstract void onClick();
}