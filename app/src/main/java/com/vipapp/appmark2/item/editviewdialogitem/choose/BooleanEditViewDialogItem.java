package com.vipapp.appmark2.item.editviewdialogitem.choose;

import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.editviewdialogitem.ChooseEditViewDialogItem;

import java.util.ArrayList;

import static com.vipapp.appmark2.util.Const.boolean_chooser;

public abstract class BooleanEditViewDialogItem extends ChooseEditViewDialogItem {
    @Override
    public ArrayList<Item<String>> getChoose() {
        return boolean_chooser;
    }
}
