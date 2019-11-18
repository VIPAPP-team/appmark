package com.vipapp.appmark2.menu;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.vipapp.appmark2.holder.StringsListEditorHolder;
import com.vipapp.appmark2.items.Item;
import com.vipapp.appmark2.items.TransformedItem;
import com.vipapp.appmark2.utils.Toast;
import com.vipapp.appmark2.utils.wrapper.Service;
import com.vipapp.appmark2.utils.wrapper.Window;

import java.util.ArrayList;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
import static android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
import static com.vipapp.appmark2.utils.Const.STRINGS_LIST_PUSHED;

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
