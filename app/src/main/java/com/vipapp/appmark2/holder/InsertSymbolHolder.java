package com.vipapp.appmark2.holder;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InsertSymbolHolder extends RecyclerView.ViewHolder {
    public TextView value;
    public InsertSymbolHolder(@NonNull View itemView) {
        super(itemView);
        value = itemView.findViewById(R.id.value);
    }
}
