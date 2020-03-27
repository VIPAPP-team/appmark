package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.wrapper.mActivity;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainScreenMenu extends DefaultMenu<Item<String>, MainScreenMenu.MainMenuHolder> {
    @Override
    public int getLayoutResource() {
        return R.layout.main_menu_default;
    }

    @Override
    public MainMenuHolder getViewHolder(ViewGroup parent, int itemType) {
        return new MainMenuHolder(inflate(parent));
    }

    public ArrayList<Item<String>> list(Context context) {
        return Const.main_menu;
    }

    public void bind(MainMenuHolder vh, Item<String> item, int i) {
        vh.title.setText(item.getInstance());
        vh.title.setOnClickListener(view ->
                mActivity.get().onLoad(item.toOnLoadItem()));
    }

    static class MainMenuHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public MainMenuHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }

    }
}