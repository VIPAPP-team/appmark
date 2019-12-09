package com.vipapp.appmark2.alert;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.TransformedItem;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.RecyclerView;
import com.vipapp.appmark2.widget.TextView;
import java.util.ArrayList;
import static com.vipapp.appmark2.util.Const.STRINGS_LIST_PUSHED;

public class StringsListEditor {
    public static void show(String title, ArrayList<TransformedItem<String, String>> strings, PushCallback<ArrayList<TransformedItem<String, String>>> results){
        AlertDialog dialog = AlertDialog.createFromResource(R.layout.strings_list_editor_dialog);

        View v = dialog.getView();
        // find views
        TextView tv_title = v.findViewById(R.id.title);
        RecyclerView strings_list_editor = v.findViewById(R.id.strings_list_editor);
        TextView ok = v.findViewById(R.id.ok);
        // views setup
        tv_title.setText(title);
        strings_list_editor.pushValue(STRINGS_LIST_PUSHED, strings);
        // set callbacks
        ok.setOnClickListener(view -> {
            dialog.cancel();
            results.onComplete(strings);
        });

        dialog.show();
    }
}
