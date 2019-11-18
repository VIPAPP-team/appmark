package com.vipapp.appmark2.holder;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.EditText;
import com.vipapp.appmark2.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StringsListEditorHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public EditText value;

    public StringsListEditorHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        value = itemView.findViewById(R.id.value);
    }
}
