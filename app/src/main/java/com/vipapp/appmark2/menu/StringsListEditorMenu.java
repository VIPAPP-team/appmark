package com.vipapp.appmark2.menu;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import com.vipapp.appmark2.holder.StringsListEditorHolder;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.TransformedItem;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.STRINGS_LIST_PUSHED;

public class StringsListEditorMenu extends DefaultMenu<TransformedItem<String, String>, StringsListEditorHolder> {

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

}
