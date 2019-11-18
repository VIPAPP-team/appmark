package com.vipapp.appmark2.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vipapp.appmark2.R;

public class FileHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView name;

    public FileHolder(@NonNull View itemView) {
        super(itemView);
        icon = itemView.findViewById(R.id.icon);
        name = itemView.findViewById(R.id.name);
    }
}
