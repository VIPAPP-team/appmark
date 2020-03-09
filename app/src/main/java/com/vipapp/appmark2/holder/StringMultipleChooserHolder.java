package com.vipapp.appmark2.holder;

import android.view.View;
import android.widget.CheckBox;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StringMultipleChooserHolder extends RecyclerView.ViewHolder {
    public TextView content;
    public CheckBox checkBox;

    public StringMultipleChooserHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.content);
        checkBox = itemView.findViewById(R.id.checkBox);
    }
}
