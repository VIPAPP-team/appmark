package com.vipapp.appmark2.item.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.vipapp.appmark2.item.ImageItem;
import com.vipapp.appmark2.util.wrapper.Res;

import androidx.annotation.DrawableRes;

public abstract class DefaultWidget extends ImageItem {
    public DefaultWidget(@DrawableRes int drawable, String title) {
        super(BitmapFactory.decodeResource(Res.get(), drawable), title);
    }
    public String getDefaultText(String id){
        return String.format("<%1$s\nandroid:id=\"@+id/%2$s\"\nandroid:layout_width='wrap_content'\nandroid:layout_height='wrap_content'\nandroid:padding=\"8dp\"/>", getTitle(), id);
    }
}
