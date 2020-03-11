package com.vipapp.appmark2.holder;

import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageItemChooserHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView image;

    public ImageItemChooserHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        image = itemView.findViewById(R.id.image);
    }
}
