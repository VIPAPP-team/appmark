package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.StringSelectableItem;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StringMultipleChooserMenu extends DefaultMenu<StringSelectableItem, StringMultipleChooserMenu.StringMultipleChooserHolder> {
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

    @Override
    public int getLayoutResource() {
        return R.layout.string_multiple_chooser_default;
    }

    @Override
    public StringMultipleChooserHolder getViewHolder(ViewGroup parent, int itemType) {
        return new StringMultipleChooserHolder(inflate(parent));
    }

    static class StringMultipleChooserHolder extends RecyclerView.ViewHolder {
        public TextView content;
        public CheckBox checkBox;

        public StringMultipleChooserHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
