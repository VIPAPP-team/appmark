package com.vipapp.appmark2.widget;


import android.content.Context;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.adapter.DefaultAdapter;
import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.items.Item;
import com.vipapp.appmark2.menu.DefaultMenu;
import com.vipapp.appmark2.menu.EmptyMenu;
import com.vipapp.appmark2.utils.ClassUtils;
import com.vipapp.appmark2.utils.Const;
import com.vipapp.appmark2.utils.ContextUtils;

public class RecyclerView extends androidx.recyclerview.widget.RecyclerView {
    DefaultAdapter adapter;
    TypedArray array;

    public void update(){
        adapter.update();
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public RecyclerView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public void init(Context context, AttributeSet attrs){
        setHasFixedSize(false);
        if(context != null) {
            if(attrs != null) {
                TypedArray array = context.getTheme().obtainStyledAttributes(
                        attrs, R.styleable.RecyclerView, 0, 0);
                setupFromValues(array);
                array.recycle();
            }
        }
    }

    @NonNull
    @Override
    public DefaultAdapter getAdapter() {
        return adapter;
    }

    public void setupFromValues(TypedArray array){
        //setLayoutManager(RecyclerView.getLayoutManager(array));
        this.array = array;
        adapter = getAdapter(array);
        adapter.onRecyclerPushed(this);
        setAdapter(adapter);
    }

    public TypedArray getAttributes() {
        return array;
    }

    public void pushValue(Object value){
        pushValue(Const.ANY, value);
    }
    public void pushValue(int type, Object value){
        adapter.transferObjectToMenu(new Item<>(type, value));
    }

    public void addOnPushCallback(PushCallback<Item> callback){
        adapter.addOnPushCallback(callback);
    }

    /* public static LayoutManager getLayoutManager(TypedArray array){
        int result = array.getInteger(R.styleable.RecyclerView_columns, -1);
        return result == -1? new LinearLayoutManager(ContextUtils.context):
                new GridLayoutManager(ContextUtils.context, result);
    } */

    public static String getViewHolder(TypedArray array) {
        String result = array.getString(R.styleable.RecyclerView_holder);
        return result == null?"":"com.vipapp.appmark2.holder." + result;
    }
    public static int getViewId(TypedArray array){
        return array.getResourceId(R.styleable.RecyclerView_default_view, 0);
    }
    public static DefaultMenu getMenu(TypedArray array){
        String result = array.getString(R.styleable.RecyclerView_menuClass);
        return result == null? new EmptyMenu():
                (DefaultMenu) ClassUtils.getInstance("com.vipapp.appmark2.menu." + result);
    }
    public static DefaultAdapter getAdapter(TypedArray array){
        String result = array.getString(R.styleable.RecyclerView_adapter);

        String viewHolder = getViewHolder(array);
        DefaultMenu menu = getMenu(array);
        int viewId = getViewId(array);

        return result == null? new DefaultAdapter(viewHolder, menu, viewId):
                (DefaultAdapter) ClassUtils.getInstance("com.vipapp.appmark2.adapter." + result,
                        String.class, DefaultMenu.class, Integer.class, viewHolder, menu, viewId);
    }

}
