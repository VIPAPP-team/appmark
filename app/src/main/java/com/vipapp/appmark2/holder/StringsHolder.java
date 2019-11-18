package com.vipapp.appmark2.holder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.widget.EditText;

public class StringsHolder extends RecyclerView.ViewHolder {
    public EditText name;
    public EditText value;
    public LinearLayout main_linear;
    public ImageView add;

    public StringsHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        value = itemView.findViewById(R.id.value);
        main_linear = itemView.findViewById(R.id.main_linear);
        add = itemView.findViewById(R.id.add);
    }

}
