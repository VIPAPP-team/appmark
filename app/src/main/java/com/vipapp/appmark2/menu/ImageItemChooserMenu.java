package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.ImageItem;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageItemChooserMenu extends DefaultMenu<ImageItem, ImageItemChooserMenu.ImageItemChooserHolder> {
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

    @Override
    public int getLayoutResource() {
        return R.layout.image_item_default;
    }

    @Override
    public ImageItemChooserHolder getViewHolder(ViewGroup parent, int itemType) {
        return new ImageItemChooserHolder(inflate(parent));
    }

    static class ImageItemChooserHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;

        public ImageItemChooserHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }
}
