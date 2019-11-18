package com.vipapp.appmark2.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vipapp.appmark2.R;

public class ProjectHolder extends RecyclerView.ViewHolder {

    public LinearLayout edit_panel;
    public ImageView delete;
    public ImageView close;
    public ImageView edit;
    public TextView name;
    public TextView packag;
    public TextView version_name;
    public ImageView icon;

    public ProjectHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        packag = itemView.findViewById(R.id.packag);
        version_name = itemView.findViewById(R.id.version_name);
        icon = itemView.findViewById(R.id.icon);
        edit_panel = itemView.findViewById(R.id.edit_panel);
        delete = itemView.findViewById(R.id.delete);
        edit = itemView.findViewById(R.id.edit);
        close = itemView.findViewById(R.id.close);
    }

}
