package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChooserMenu extends DefaultMenu<Item<String>, ChooserMenu.ChooserHolder> {
    public void bind(ChooserHolder chooserHolder, Item<String> item, int i) {
        chooserHolder.content.setText(item.getInstance());
        chooserHolder.itemView.setOnClickListener(view -> pushItem(item));
    }

    public ArrayList<Item<String>> list(Context context) {
        return null;
    }

    public void onValueReceived(Item item) {
        //noinspection unchecked
        pushArray((ArrayList)item.getInstance());
    }

    @Override
    public int getLayoutResource() {
        return R.layout.string_chooser_default;
    }

    @Override
    public ChooserHolder getViewHolder(ViewGroup parent, int itemType) {
        return new ChooserHolder(inflate(parent));
    }

    static class ChooserHolder extends RecyclerView.ViewHolder {

        public TextView content;

        ChooserHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
        }

    }

}
