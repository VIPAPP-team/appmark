package com.vipapp.appmark2.manager;

import android.database.Cursor;
import android.provider.MediaStore;

import com.vipapp.appmark2.item.Image;
import com.vipapp.appmark2.util.wrapper.mContext;

import java.io.File;
import java.util.ArrayList;

public class GalleryManager extends DefaultManager<Image> {

    public GalleryManager(){
        super(-1);
    }
    public GalleryManager(int count){
        super(count);
    }

    @Override
    public ArrayList<Image> init(Object... args) {
        ArrayList<Image> images = new ArrayList<>();
        String[] projection = new String[]{
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATE_TAKEN,
                MediaStore.Images.ImageColumns.MIME_TYPE
        };
        final Cursor cursor = mContext.get().getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
                        null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");
        int count = (int) args[0];
        if (cursor != null && cursor.moveToFirst()) {
            while(count > 0) {
                String imageLocation = cursor.getString(1);
                File imageFile = new File(imageLocation);
                if (imageFile.exists()) {
                    images.add(new Image(imageFile));
                } else {
                    break;
                }
                if(!cursor.moveToNext()){
                    break;
                }
                count--;
            }
            cursor.close();
        }
        // mActivity.get().onLoad(new OnLoadItem(Const.GALLERY_LOADED));
        return images;
    }

}
