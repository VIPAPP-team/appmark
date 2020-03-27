package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.adapter.DefaultAdapter;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.wrapper.mContext;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InsertSymbolMenu extends DefaultMenu<String, InsertSymbolMenu.InsertSymbolHolder> {

    @Override
    public ArrayList<String> list(Context context) {
        return new ArrayList<>(Arrays.asList(Const.CODE_EDITOR_SYMBOLS));
    }

    @Override
    public void bind(InsertSymbolHolder vh, String item, int i) {
        vh.value.setText(item.equals("    ")? "TAB": item);
        vh.value.setOnClickListener(view -> pushItem(new Item<>(Const.SYMBOL_INSERTED, item)));
    }

    @Override
    public int getLayoutResource() {
        return R.layout.insert_symbol_default;
    }

    @Override
    public InsertSymbolHolder getViewHolder(ViewGroup parent, int itemType) {
        return new InsertSymbolHolder(inflate(parent));
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext.get());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        return manager;
    }

    static class InsertSymbolHolder extends RecyclerView.ViewHolder {
        public TextView value;
        InsertSymbolHolder(@NonNull View itemView) {
            super(itemView);
            value = itemView.findViewById(R.id.value);
        }
    }

}
