package com.vipapp.appmark2.menu;

import android.content.Context;

import com.vipapp.appmark2.holder.HintsHolder;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.util.ArrayUtils;
import com.vipapp.appmark2.widget.CodeText;

import java.util.ArrayList;

public class HintsMenu extends DefaultMenu<CodeText.Hint, HintsHolder> {
    public static int ARRAY_PUSHED = 0;
    public static int TEXT_CHANGED = 1;
    public static int TEXT_INSERTED = 2;
    public static int SHOW_POPUP = 3;


    private ArrayList<CodeText.Hint> hints;
    @Override
    public ArrayList<CodeText.Hint> list(Context context) {
        return hints;
    }

    @Override
    public void bind(HintsHolder vh, CodeText.Hint item, int i) {
        vh.hint_text.setText(item.getBody());
        vh.itemView.setOnClickListener(view -> pushItem(new Item<>(TEXT_INSERTED, item.getInsertValue())));
    }

    @Override
    public void onValueReceived(Item item) {
        super.onValueReceived(item);
        if(item.getType() == ARRAY_PUSHED) {
            //noinspection unchecked
            hints = (ArrayList<CodeText.Hint>) item.getInstance();
        }
        if(item.getType() == TEXT_CHANGED){
            if(hints != null) {
                String word = (String) item.getInstance();
                ArrayList<CodeText.Hint> filtered = ArrayUtils.filter(hints, hint -> hint.show(word));
                pushArray(filtered);
                pushItem(new Item<>(SHOW_POPUP, filtered.size() > 0 && !word.equals("")));
            }
        }
    }
}
