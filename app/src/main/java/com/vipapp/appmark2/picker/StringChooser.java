package com.vipapp.appmark2.picker;

import androidx.annotation.StringRes;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.RecyclerView;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;

public class StringChooser extends DefaultPicker<Item<String>> {

    private TextView title;
    private RecyclerView chooser;

    public StringChooser(PushCallback<Item<String>> callback) {
        super(callback);
        setView(R.layout.string_chooser_dialog);
        findViews(getView());
        setCallbacks();
    }

    private void findViews(View view){
        title = view.findViewById(R.id.title);
        chooser = view.findViewById(R.id.chooser);
    }
    private void setCallbacks(){
        chooser.addOnPushCallback(item -> {
            cancel();
            //noinspection unchecked
            pushItem(item);
        });
    }

    public void setArray(ArrayList<Item<String>> array){
        chooser.pushValue(array);
    }
    public void setTitle(String text){
        title.setVisibility(text == null || text.equals("")? View.GONE: View.VISIBLE);
        title.setText(text);
    }
    public void setTitle(@StringRes int res){
        setTitle(Str.get(res));
    }

}
