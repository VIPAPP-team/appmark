package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.ViewGroup;

import com.vipapp.appmark2.holder.InsertSymbolHolder;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.util.Const;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertSymbolMenu extends DefaultMenu<String, InsertSymbolHolder> {

    @Override
    public ArrayList<String> list(Context context) {
        return new ArrayList<>(Arrays.asList(Const.CODE_EDITOR_SYMBOLS));
    }

    @Override
    public void bind(InsertSymbolHolder vh, String item, int i) {
        vh.value.setText(item.equals("    ")? "\\t": item);
        vh.value.setOnClickListener(view -> pushItem(new Item<>(Const.SYMBOL_INSERTED, item)));
    }

    @Override
    public InsertSymbolHolder getViewHolder(ViewGroup parent, int itemType) {
        return super.getViewHolder(parent, itemType);
    }

}
