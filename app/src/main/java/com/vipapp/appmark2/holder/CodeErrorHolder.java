package com.vipapp.appmark2.holder;

import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CodeErrorHolder extends RecyclerView.ViewHolder {
    public ImageView error_icon;
    public TextView error_text;

    public CodeErrorHolder(@NonNull View itemView) {
        super(itemView);
        error_icon = itemView.findViewById(R.id.error_icon);
        error_text = itemView.findViewById(R.id.error_text);
    }
}
