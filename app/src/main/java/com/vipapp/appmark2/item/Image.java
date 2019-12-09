package com.vipapp.appmark2.item;

import android.graphics.Bitmap;
import java.io.File;

public class Image {
    private Bitmap bitmap;
    private File file;

    public Image(File file) {
        this.file = file;
    }

    public Image(File file, Bitmap bitmap) {
        this.file = file;
        this.bitmap = bitmap;
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}