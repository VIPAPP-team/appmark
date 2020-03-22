package com.vipapp.appmark2.holder;

import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HintsHolder extends RecyclerView.ViewHolder {
    public ImageView hint_icon;
    public TextView hint_text;

    public HintsHolder(@NonNull View itemView) {
        super(itemView);
        hint_icon = itemView.findViewById(R.id.hint_icon);
        hint_text = itemView.findViewById(R.id.hint_text);
    }
}
