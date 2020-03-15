package com.vipapp.appmark2.menu;

import android.annotation.SuppressLint;
import android.content.Context;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.activity.CodeActivity;
import com.vipapp.appmark2.compiler.ErrorsParser;
import com.vipapp.appmark2.holder.CodeErrorHolder;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.util.TextUtils;

import java.util.ArrayList;
import java.util.Locale;

import static com.vipapp.appmark2.alert.CodeErrorsDialog.CODE_ACTIVITY;
import static com.vipapp.appmark2.alert.CodeErrorsDialog.ERRORS_ARRAY;

public class CodeErrorMenu extends DefaultMenu<ErrorsParser.Error, CodeErrorHolder> {
    private CodeActivity activity;

    @Override
    public ArrayList<ErrorsParser.Error> list(Context context) {
        return null;
    }

    @Override
    public void bind(CodeErrorHolder vh, ErrorsParser.Error item, int i) {
        vh.error_icon.setImageResource(item.getType() == ErrorsParser.Error.TYPE_ERROR? R.drawable.error_icon: R.drawable.warning_icon);
        vh.error_text.setText(String.format(Locale.getDefault(), "%s(at line %d)", item.getMessage(), item.getLineNumber()));
        vh.itemView.setOnClickListener(view -> {
            activity.openFile(item.getFile(), none -> {
                activity.content.setSelection(activity.content.getLineEndIndex(item.getLineNumber() - 1) - 1);
                activity.content.requestFocus();
            });
            pushItem(null);
        });
    }

    @Override
    public void onValueReceived(Item item) {
        super.onValueReceived(item);
        if(item.getType() == ERRORS_ARRAY)
            //noinspection unchecked
            pushArray((ArrayList<ErrorsParser.Error>)item.getInstance());
        if(item.getType() == CODE_ACTIVITY)
            activity = (CodeActivity) item.getInstance();
    }
}
