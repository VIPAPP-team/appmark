package com.vipapp.appmark2.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.TextView;

public class MainMenuHolder extends RecyclerView.ViewHolder {

    public TextView title;

    public MainMenuHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
    }

}
