package com.vipapp.appmark2.alert;

import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.BuiltView;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.item.widget.DefaultWidget;
import com.vipapp.appmark2.picker.ImageItemChooser;
import com.vipapp.appmark2.picker.StringChooser;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.ArrayUtils;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.RecyclerView;
import com.vipapp.appmark2.widget.TextView;
import com.vipapp.appmark2.xml.XMLObject;

import java.util.ArrayList;
import java.util.Collections;

import static com.vipapp.appmark2.menu.EditViewDialogMenu.LIST;
import static com.vipapp.appmark2.menu.EditViewDialogMenu.PARENT;
import static com.vipapp.appmark2.menu.EditViewDialogMenu.PROJECT;
import static com.vipapp.appmark2.util.Const.EDIT_DIALOG_ATTRIBUTES;
import static com.vipapp.appmark2.util.Const.WIDGETS;
import static com.vipapp.appmark2.util.Const.add_choose;

public class EditViewDialog {
    public static void show(XMLObject object, Project project, View view, ArrayList<BuiltView> widgets, PushCallback<Void> onEdit){
        int[] add_mode = new int[1];
        boolean root_element = object.toNode().getParentNode().getParentNode() == null;

        AlertDialog dialog = new AlertDialog(){
            @Override
            public void onDismiss() {
                super.onDismiss();
                onEdit.onComplete(null);
            }
        };
        dialog.setView(R.layout.edit_view_dialog);
        View v = dialog.getView();

        ImageItemChooser widgets_chooser = new ImageItemChooser(widget -> {
            XMLObject newXMl = XMLObject.parse(
                    ((DefaultWidget)widget).getDefaultText(generateNewId(widget.getTitle().toLowerCase(), widgets)));
            if(newXMl == null){
                Toast.show(Str.get(R.string.xml_error));
            } else {
                switch (add_mode[0]){
                    case 0:
                        object.addBefore(newXMl);
                        break;
                    case 1:
                        object.addInside(newXMl);
                        break;
                }
                onEdit.onComplete(null);
                dialog.cancel();
                ArrayUtils.filter(widgets, item -> item.getObject().toString().equals(newXMl.toString()))
                        .get(0).getView().callOnClick();
            }
        });
        widgets_chooser.setArray(WIDGETS);
        widgets_chooser.setTitle(R.string.select_widget);

        StringChooser add_chooser = new StringChooser(result -> {
            add_mode[0] = result.getType();
            widgets_chooser.show();
        });
        ArrayList<Item<String>> add_items = new ArrayList<>(add_choose);
        if(!(view instanceof ViewGroup))
            add_items.remove(1);
        if(root_element)
            add_items.remove(0);
        add_chooser.setArray(add_items);

        // find views
        TextView title = v.findViewById(R.id.title);
        RecyclerView recycler = v.findViewById(R.id.recycler);
        View create = v.findViewById(R.id.create);
        View delete = v.findViewById(R.id.delete);
        // setup
        title.setText(object.getName());
        ArrayList<EditViewDialogItem> items = ArrayUtils.filter(EDIT_DIALOG_ATTRIBUTES, item -> item.exists(view));
        Collections.sort(items, (first, second) -> {
            String first_value = first.getDisplayValue(object);
            String second_value = second.getDisplayValue(object);

            boolean first_empty = first_value == null || first_value.equals("");
            boolean second_empty = second_value == null || second_value.equals("");

            if(!first_empty && second_empty){
                return -1;
            } else if(first_empty && !second_empty){
                return 1;
            } else {
                return Integer.compare(items.indexOf(first), items.indexOf(second));
            }

        });
        recycler.pushValue(PARENT, object);
        recycler.pushValue(PROJECT, project);
        recycler.pushValue(LIST, items);

        if(root_element)
            delete.setVisibility(View.GONE);

        // set callbacks
        create.setOnClickListener(mV -> add_chooser.show());
        delete.setOnClickListener(mV ->
                ConfirmDialog.show(Str.get(R.string.delete_view_warn),
                        result -> {
                            object.toNode().getParentNode().removeChild(object.toNode());
                            dialog.cancel();
                        }));
        dialog.show();
    }
    private static String generateNewId(String name, ArrayList<BuiltView> widgets){
        int i = 1;
        String[] id = new String[]{""};

        do{
            id[0] = name + i;
            i++;
        } while (ArrayUtils.any(widgets, widget -> {
            String curr_id = widget.getObject().getNamedAttribute("android:id").getValue();
            return curr_id != null && curr_id.equals("@+id/" + id[0]);
        }));

        return id[0];
    }
}
