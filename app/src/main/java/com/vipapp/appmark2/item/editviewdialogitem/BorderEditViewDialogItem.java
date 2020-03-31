package com.vipapp.appmark2.item.editviewdialogitem;

import android.view.View;

import com.google.common.base.Joiner;
import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.EditViewDialogItem;
import com.vipapp.appmark2.project.Project;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.EditText;
import com.vipapp.appmark2.widget.TextView;
import com.vipapp.appmark2.xml.XMLObject;

import java.util.Objects;

import static com.vipapp.appmark2.util.ClassUtils.getOrDefault;
import static com.vipapp.appmark2.util.Const.SIZE_REGEX;

public abstract class BorderEditViewDialogItem extends EditViewDialogItem {

    private String[] attrs = new String[]{
            getAllAttrName(),
            getLeftAttrName(),
            getRightAttrName(),
            getTopAttrName(),
            getBottomAttrName()
    };

    public abstract String getAllAttrName();
    public abstract String getLeftAttrName();
    public abstract String getRightAttrName();
    public abstract String getTopAttrName();
    public abstract String getBottomAttrName();

    @Override
    public void pickAttribute(PushCallback<String> callback, Project project, String defaultValue) {
        AlertDialog dialog = new AlertDialog(){};
        dialog.setView(R.layout.border_edit_view_dialog_item);

        View v = dialog.getView();
        TextView title = v.findViewById(R.id.title);
        EditText valueAll = v.findViewById(R.id.valueAll);
        EditText valueLeft = v.findViewById(R.id.valueLeft);
        EditText valueRight = v.findViewById(R.id.valueRight);
        EditText valueTop = v.findViewById(R.id.valueTop);
        EditText valueBottom = v.findViewById(R.id.valueBottom);
        View ok = v.findViewById(R.id.ok);

        String[] defaultValues = defaultValue.split(";");
        EditText[] tvs = new EditText[]{
                valueAll,
                valueLeft,
                valueRight,
                valueTop,
                valueBottom
        };
        String[] values = new String[tvs.length];

        for(int i = 0; i < tvs.length; i++)
            tvs[i].setText(defaultValues[i].equals("none")? "": defaultValues[i]);

        title.setText(getTitle());

        ok.setOnClickListener(view -> {
            boolean valid_data = true;

            for(int i = 0; i < tvs.length; i++){
                String value = Objects.requireNonNull(tvs[i].getText()).toString();
                if(!value.matches(SIZE_REGEX) && !value.equals("")) {
                    valid_data = false;
                    tvs[i].setError(Str.get(R.string.incorrect_number));
                    break;
                }
                if(!value.contains("dp"))
                    value += "dp";
                value = getOrDefault(value, "none", str -> !str.equals("dp"));
                values[i] = value;
            }

            if(valid_data)
                callback.onComplete(Joiner.on(';').join(values));

            dialog.cancel();
        });

        dialog.show();
    }
    @Override
    public void applyAttr(XMLObject object, String attrValue) {
        int i = 0;
        for(String value: attrValue.split(";")){
            if(value.equals("none")) {
                object.removeAttribute(attrs[i]);
                continue;
            }

            String attrName = attrs[i];
            object.setAttribute(attrName, value);

            i++;
        }
    }

    @Override
    public String getDisplayValue(XMLObject object) {
        String[] values = new String[attrs.length];
        for(int i = 0; i < values.length; i++)
            //noinspection Convert2MethodRef
            values[i] = getOrDefault(object.getNamedAttribute(attrs[i]).getValue(), "none", str -> str != null);
        return Joiner.on(';').join(values);
    }
}
