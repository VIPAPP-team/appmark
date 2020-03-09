package com.vipapp.appmark2.menu;

import android.content.Context;

import com.vipapp.appmark2.holder.ChooserHolder;
import com.vipapp.appmark2.holder.ImageItemChooserHolder;
import com.vipapp.appmark2.item.ImageItem;
import com.vipapp.appmark2.item.Item;

import java.util.ArrayList;

public class ImageItemChooserMenu extends DefaultMenu<ImageItem, ImageItemChooserHolder> {
    @Override
    public ArrayList<ImageItem> list(Context context) {
        return null;
    }

    @Override
    public void bind(ImageItemChooserHolder vh, ImageItem item, int i) {
        vh.title.setText(item.getTitle());
        vh.image.setImageBitmap(item.getImage());
        vh.itemView.setOnClickListener(view -> {
            //noinspection unchecked
            pushItem(new Item(item));
        });
    }

    @Override
    public void onValueReceived(Item item) {
        //noinspection unchecked
        pushArray((ArrayList<ImageItem>)item.getInstance());
    }
}
