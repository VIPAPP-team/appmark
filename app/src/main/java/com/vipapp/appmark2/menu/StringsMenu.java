package com.vipapp.appmark2.menu;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.holder.StringsHolder;
import com.vipapp.appmark2.items.Item;
import com.vipapp.appmark2.manager.res.StringsManager;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.ContextUtils;
import com.vipapp.obfuscated.xml.XMLAttribute;
import com.vipapp.obfuscated.xml.XMLObject;

import java.util.ArrayList;
import java.util.Objects;


public class StringsMenu extends DefaultMenu<XMLObject, StringsHolder> {

    private SparseArray<String> strings = new SparseArray<>();

    private boolean text_input = true;
    private StringsManager manager;

    public ArrayList<XMLObject> list(Context context) {
        return null;
    }

    @Override
    public void onValueReceived(Item item) {
        super.onValueReceived(item);
        if(item.getType() == Const.STRINGS_PUSHED) {
            manager = (StringsManager) item.getInstance();
            manager.exec(list -> {
                if(list != null) {
                    pushWithAddButton(list);
                }
            });
        }
    }

    private void pushWithAddButton(ArrayList<XMLObject> list, boolean need_to_notify){
        ArrayList<XMLObject> copy = new ArrayList<>(list);
        copy.add(null);
        pushArray(copy, need_to_notify);
    }
    private void pushWithAddButton(ArrayList<XMLObject> list){
        pushWithAddButton(list, true);
    }

    public void bind(StringsHolder vh, XMLObject item, int i) {
        if(item == null){
            vh.add.setVisibility(View.VISIBLE);
            vh.add.setOnClickListener(view -> createEmptyString());
        } else {
            text_input = false;
            XMLAttribute attr = item.getNamedAttribute("name");
            vh.name.setText(attr.getValue());
            text_input = false;
            vh.value.setText(item.getValue());
            text_input = true;
            setCallbacks(vh, i);
        }
    }
    private void createEmptyString(){
        int pos = manager.getObjects().size();
        strings.put(pos, "");
        manager.add("", "");
        pushWithAddButton(manager.getObjects(), false);
        notifyInserted(pos);
    }

    public void setCallbacks(StringsHolder holder, int pos){
        holder.name.removeAllCallbacks();
        holder.value.removeAllCallbacks();
        holder.name.addTextChangedListener(new TextWatcher() {
            String oldName = "";
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                oldName = charSequence.toString();
            }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            public void afterTextChanged(Editable editable) {
                if(text_input) {
                    if(!changeName(oldName, Objects.requireNonNull(holder.name.getText()).toString(), pos)){
                        holder.name.setError(ContextUtils.getString(R.string.incorrect_string_name));
                    }
                } else {
                    text_input = true;
                }
            }
        });
        holder.name.setOnFocusChangeListener((view, focused) -> {
            if(!focused) {
                holder.name.setText(strings.get(pos, Objects.requireNonNull(holder.name.getText()).toString()));
                holder.name.setError(null);
            }
        });
        holder.value.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            public void afterTextChanged(Editable editable) {
                if(text_input) {
                    changeValue(Objects.requireNonNull(holder.name.getText()).toString(), editable.toString(), pos);
                } else {
                    text_input = true;
                }
            }
        });
    }

    private boolean changeName(String old_name, String new_name, int pos){

        if(old_name.equals(new_name))
            return true;

        boolean result = true;
        old_name = strings.get(pos, old_name);
        String new_name_rl = new_name;
        if(manager.get(new_name) == null) {
            manager.rename(old_name, new_name);
        } else {
            result = false;
            new_name_rl = old_name;
        }
        strings.put(pos, new_name_rl);
        return result;
    }

    private void changeValue(String name, String value, int pos){
        manager.changeValue(strings.get(pos, name), value);
    }

}
