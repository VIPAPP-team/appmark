package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.activity.CodeActivity;
import com.vipapp.appmark2.compiler.ErrorsParser;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.vipapp.appmark2.alert.CodeErrorsDialog.CODE_ACTIVITY;
import static com.vipapp.appmark2.alert.CodeErrorsDialog.ERRORS_ARRAY;

public class CodeErrorMenu extends DefaultMenu<ErrorsParser.Error, CodeErrorMenu.CodeErrorHolder> {
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
                activity.code.setSelection(activity.code.getLineEndIndex(item.getLineNumber() - 1) - 1);
                activity.code.requestFocus();
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

    @Override
    public int getLayoutResource() {
        return R.layout.code_errors_default;
    }

    @Override
    public CodeErrorHolder getViewHolder(ViewGroup parent, int itemType) {
        return new CodeErrorHolder(inflate(parent));
    }

    static class CodeErrorHolder extends RecyclerView.ViewHolder {
        public ImageView error_icon;
        public TextView error_text;

        public CodeErrorHolder(@NonNull View itemView) {
            super(itemView);
            error_icon = itemView.findViewById(R.id.error_icon);
            error_text = itemView.findViewById(R.id.error_text);
        }
    }
}
