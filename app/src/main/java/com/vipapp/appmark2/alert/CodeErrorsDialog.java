package com.vipapp.appmark2.alert;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.activity.CodeActivity;
import com.vipapp.appmark2.compiler.ErrorsParser;
import com.vipapp.appmark2.widget.AlertDialog;
import com.vipapp.appmark2.widget.RecyclerView;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;

public class CodeErrorsDialog {
    public static int ERRORS_ARRAY = 0;
    public static int CODE_ACTIVITY = 1;
    public static void show(ArrayList<ErrorsParser.Error> errors, CodeActivity activity){
        AlertDialog dialog = AlertDialog.createFromResource(R.layout.code_errors_dialog);
        View v = dialog.getView();

        RecyclerView recyclerView = v.findViewById(R.id.recycler);
        TextView ok = v.findViewById(R.id.ok);

        recyclerView.pushValue(ERRORS_ARRAY, errors);
        recyclerView.pushValue(CODE_ACTIVITY, activity);
        recyclerView.addOnPushCallback(item -> dialog.cancel());

        ok.setOnClickListener(view -> dialog.cancel());

        dialog.show();
    }
}
