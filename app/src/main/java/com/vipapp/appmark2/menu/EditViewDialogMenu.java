package com.vipapp.appmark2.menu;

import android.content.Context;

import com.vipapp.appmark2.holder.EditViewDialogHolder;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.xml.XMLObject;

import java.util.ArrayList;
import java.util.Objects;

public class EditViewDialogMenu extends DefaultMenu<EditViewDialogItem, EditViewDialogHolder> {
    public static final int LIST = 2;
    public static final int PARENT = 0;
    public static final int PROJECT = 1;

    private XMLObject parent;
    private Project project;

    @Override
    public ArrayList<EditViewDialogItem> list(Context context) {
        return null;
    }

    @Override
    public void bind(EditViewDialogHolder vh, EditViewDialogItem item, int i) {
        String[] value = new String[]{ item.getDisplayValue(parent) };

        vh.icon.setImageResource(item.getImage());
        vh.title.setText(item.getTitle());
        vh.value.setText(value[0]);
        vh.subtitle.setText(item.getAttrName());

        vh.itemView.setOnClickListener(view -> item.action(parent, project, value[0], newValue -> {
            value[0] = newValue;
            vh.value.setText(value[0]);
        }));
    }

    @Override
    public void onValueReceived(Item item) {
        switch (item.getType()){
            case PROJECT:
                project = (Project) item.getInstance();
                break;
            case LIST:
                //noinspection unchecked
                pushArray((ArrayList<EditViewDialogItem>) item.getInstance());
                break;
            case PARENT:
                parent = (XMLObject) item.getInstance();
                break;
        }
    }
}
