package com.vipapp.appmark2.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vipapp.appmark2.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EditViewDialogHolder extends RecyclerView.ViewHolder {
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
