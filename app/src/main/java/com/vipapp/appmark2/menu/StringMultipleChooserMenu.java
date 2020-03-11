package com.vipapp.appmark2.menu;

import android.content.Context;

import com.vipapp.appmark2.holder.ChooserHolder;
import com.vipapp.appmark2.holder.StringMultipleChooserHolder;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.StringSelectableItem;
import com.vipapp.appmark2.util.Toast;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class StringMultipleChooserMenu extends DefaultMenu<StringSelectableItem, StringMultipleChooserHolder> {
    public void bind(StringMultipleChooserHolder chooserHolder, StringSelectableItem item, int i) {
        chooserHolder.checkBox.setChecked(item.isSelected());
        chooserHolder.content.setText(item.getText());
        chooserHolder.itemView.setOnClickListener(view -> {
            item.setSelected(!item.isSelected());
            chooserHolder.checkBox.setChecked(item.isSelected());
        });
    }

    public ArrayList<StringSelectableItem> list(Context context) {
        return null;
    }

    public void onValueReceived(Item item) {
        //noinspection unchecked
        pushArray((ArrayList)item.getInstance());
    }
}
