package com.vipapp.appmark2.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.TextView;

public class ChooserHolder extends RecyclerView.ViewHolder {

    public TextView content;

    public ChooserHolder(@NonNull View itemView) {
        super(itemView);
        content = itemView.findViewById(R.id.content);
    }

}
