package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.xml.XMLObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditViewDialogMenu extends DefaultMenu<EditViewDialogItem, EditViewDialogMenu.EditViewDialogHolder> {
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

    @Override
    public int getLayoutResource() {
        return R.layout.edit_view_default;
    }

    @Override
    public EditViewDialogHolder getViewHolder(ViewGroup parent, int itemType) {
        return new EditViewDialogHolder(inflate(parent));
    }

    static class EditViewDialogHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;
        public TextView value;
        public TextView subtitle;

        public EditViewDialogHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            value = itemView.findViewById(R.id.value);
            subtitle = itemView.findViewById(R.id.subtitle);
        }
    }
}
