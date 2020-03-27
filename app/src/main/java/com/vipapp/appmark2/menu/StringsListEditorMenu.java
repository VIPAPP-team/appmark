package com.vipapp.appmark2.menu;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.TransformedItem;
import com.vipapp.appmark2.widget.EditText;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.vipapp.appmark2.util.Const.STRINGS_LIST_PUSHED;

public class StringsListEditorMenu extends DefaultMenu<TransformedItem<String, String>, StringsListEditorMenu.StringsListEditorHolder> {

    public ArrayList<TransformedItem<String, String>> list(Context context) {
        return null;
    }

    public void bind(StringsListEditorHolder vh, TransformedItem<String, String> item, int i) {
        vh.name.setText(item.getItem1());
        vh.value.setText(item.getItem2());
        setCallbacks(vh, item, i);
    }

    private void setCallbacks(StringsListEditorHolder vh, TransformedItem<String, String> item, int i){
        vh.value.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                item.setItem2(charSequence.toString());
            }
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onValueReceived(Item item) {
        super.onValueReceived(item);
        if(item.getType() == STRINGS_LIST_PUSHED){
            //noinspection unchecked
            ArrayList<TransformedItem<String, String>> value = (ArrayList<TransformedItem<String, String>>)item.getInstance();
            pushArray(value);
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.strings_list_editor_default;
    }

    @Override
    public StringsListEditorHolder getViewHolder(ViewGroup parent, int itemType) {
        return new StringsListEditorHolder(inflate(parent));
    }

    static class StringsListEditorHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public EditText value;

        public StringsListEditorHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            value = itemView.findViewById(R.id.value);
        }
    }


}
