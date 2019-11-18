package com.vipapp.appmark2.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vipapp.appmark2.callbacks.PushCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {

    public static void loadInto(@Nullable File file, ImageView view){
        try {
            Glide.with(view)
                    .load(file)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(view);
        } catch (Exception ignored){}
    }
    public static void load(@Nullable File file, PushCallback<Bitmap> result){
        Thread.start(() -> {
            Bitmap bitmap = getFromFile(file);
            Thread.ui(() -> result.onComplete(bitmap));
        });
    }
    private static Bitmap getFromFile(@Nullable File file){
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            assert file != null;
            return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        } catch (Exception e){
            return null;
        }
    }

    public static void saveBitmap(Bitmap bitmap, @Nullable File file){
        FileUtils.refresh(file, false);
        try {
            assert file != null;
            try (FileOutputStream out = new FileOutputStream(file)) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception ignored){}
    }

}
