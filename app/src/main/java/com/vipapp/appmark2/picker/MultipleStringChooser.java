package com.vipapp.appmark2.picker;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.StringSelectableItem;
import com.vipapp.appmark2.widget.RecyclerView;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.StringRes;

public class MultipleStringChooser extends DefaultPicker<ArrayList<StringSelectableItem>> {
    private ArrayList<StringSelectableItem> items = new ArrayList<>();
    private TextView title;
    private RecyclerView recycler;

    public MultipleStringChooser(PushCallback<ArrayList<StringSelectableItem>> callback) {
        super(callback, true);
        setView(R.layout.string_multiple_chooser_dialog);
        View v = getView();

        title = v.findViewById(R.id.title);
        recycler = v.findViewById(R.id.recycler);
        View ok = v.findViewById(R.id.ok);

        ok.setOnClickListener(view -> pushItem(items));
    }

    public void setTitle(@StringRes int title){
        this.title.setText(title);
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setArray(ArrayList<StringSelectableItem> items){
        this.items = items;
        recycler.pushValue(items);
    }

}
