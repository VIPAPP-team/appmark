package com.vipapp.appmark2.holder;

import androidx.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.RecyclerView;

public class ImageHolder extends RecyclerView.ViewHolder{
    public ImageView image;

    public ImageHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
    }
}
