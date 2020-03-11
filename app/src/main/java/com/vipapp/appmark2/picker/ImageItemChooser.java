package com.vipapp.appmark2.picker;

import android.view.View;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.ImageItem;
import com.vipapp.appmark2.widget.RecyclerView;
import com.vipapp.appmark2.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import androidx.annotation.StringRes;

public class ImageItemChooser extends DefaultPicker<ImageItem> {
    private RecyclerView chooser;
    private TextView title;

    public ImageItemChooser(PushCallback<ImageItem> callback) {
        super(callback);
        setView(R.layout.image_item_chooser);
        findViews(getView());
        setCallbacks();
    }

    private void findViews(View v){
        chooser = v.findViewById(R.id.chooser);
        title = v.findViewById(R.id.title);
    }
    private void setCallbacks(){
        chooser.addOnPushCallback(item -> {
            cancel();
            pushItem((ImageItem) item.getInstance());
        });
    }

    public void setArray(ArrayList<ImageItem> items){
        chooser.pushValue(items);
    }

    public void setTitle(String text){
        title.setText(text);
    }
    public void setTitle(@StringRes int res_id){
        title.setText(res_id);
    }

}
