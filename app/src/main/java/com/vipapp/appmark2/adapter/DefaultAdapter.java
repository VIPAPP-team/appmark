package com.vipapp.appmark2.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;

import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.items.Item;
import com.vipapp.appmark2.menu.DefaultMenu;
import com.vipapp.appmark2.utils.ClassUtils;
import com.vipapp.appmark2.utils.Thread;
import com.vipapp.appmark2.utils.wrapper.mActivity;
import com.vipapp.appmark2.utils.wrapper.mContext;

import java.util.ArrayList;

public class DefaultAdapter extends RecyclerView.Adapter {

    private String viewHolderName;
    private DefaultMenu menu;
    private ArrayList list = new ArrayList();
    private int xml_source;
    private RecyclerView recyclerView;

    private ArrayList<PushCallback<Item>> callbacks = new ArrayList<>();

    public DefaultAdapter(String viewHolderName, DefaultMenu menu, int xml_source){
        this.viewHolderName = viewHolderName;
        this.menu = menu;
        this.xml_source = xml_source;
        menu.onAdapterReceived(this);
        update();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (viewHolderName.equals("")) {
            return menu.getViewHolder(viewGroup, i);
        }
        return (RecyclerView.ViewHolder) ClassUtils.getInstance(viewHolderName, View.class,
                    mActivity.get().getLayoutInflater().inflate(xml_source, viewGroup, false));
    }

    private void pushArray(ArrayList arrayList){
        pushArray(arrayList, true);
    }
    public void pushArray(ArrayList arrayList, boolean need_to_notify){
        if(arrayList != null && !arrayList.equals(list)) {
            list.clear();
            //noinspection unchecked
            list.addAll(arrayList);
            if(need_to_notify)
                Thread.ui(this::notifyDataSetChanged);
        }
    }
    public void transferObjectToMenu(Item item){
        menu.onValueReceived(item);
    }

    public void onRecyclerPushed(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    @Nullable
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public ArrayList getList() {
        return list;
    }

    public DefaultMenu getMenu() {
        return menu;
    }

    public void update(){
        pushArray(menu.list(mContext.get()));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(list.size() > i) {
            menu.bind(viewHolder, list.get(i), i);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return menu.getItemViewType(position);
    }

    public void addOnPushCallback(PushCallback<Item> callback){
        callbacks.add(callback);
    }

    public void onPush(Item item){
        execCallbacks(item);
    }
    private void execCallbacks(Item item){
        for(PushCallback<Item> callback: callbacks){
            Thread.ui(() -> callback.onComplete(item));
        }
    }
}
