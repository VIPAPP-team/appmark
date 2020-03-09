package com.vipapp.appmark2.item;

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap image;
    private String title;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ImageItem(Bitmap image, String title) {
        this.image = image;
        this.title = title;
    }
}
