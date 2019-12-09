package com.vipapp.appmark2.menu;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import com.vipapp.appmark2.adapter.DefaultAdapter;
import com.vipapp.appmark2.item.Item;

import java.util.ArrayList;

public abstract class DefaultMenu<ListItemType, ViewHolderType extends RecyclerView.ViewHolder> {
    private ArrayList<ListItemType> array = new ArrayList<>();
    private DefaultAdapter adapter = null;

    public void onAdapterReceived(DefaultAdapter adapter){
        this.adapter = adapter;
    }
    public void onValueReceived(Item item){}

    public int getItemViewType(int position){ return 0; }
    public ViewHolderType getViewHolder(ViewGroup parent, int itemType){ return null; }

    void pushArray(ArrayList<ListItemType> arrayList){
        pushArray(arrayList, true);
    }
    void pushArray(ArrayList<ListItemType> arrayList, boolean need_to_notify){
        this.array = arrayList;
        adapter.pushArray(array, need_to_notify);
    }
    void pushItem(Item item){
        adapter.onPush(item);
    }
    public int size(){
        return array.size();
    }

    void notifyRemoved(int pos){
        adapter.notifyItemRemoved(pos);
    }
    void notifyChanged(int pos){
        adapter.notifyItemChanged(pos);
    }
    void notifyInserted(int pos){
        adapter.notifyItemInserted(pos);
    }

    @Nullable
    RecyclerView getRecyclerView(){
        return adapter != null? adapter.getRecyclerView(): null;
    }

    public abstract ArrayList<ListItemType> list(Context context);
    public abstract void bind(ViewHolderType vh, ListItemType item, int i);
}
