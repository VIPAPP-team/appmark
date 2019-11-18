package com.vipapp.appmark2.menu;

import android.content.Context;
import android.view.View;
import com.vipapp.appmark2.holder.ChooserHolder;
import com.vipapp.appmark2.items.Item;
import java.util.ArrayList;

public class ChooserMenu extends DefaultMenu<Item<String>, ChooserHolder> {
	public void bind(ChooserHolder chooserHolder, Item<String> item, int i) {
		chooserHolder.content.setText(item.getInstance());
		chooserHolder.itemView.setOnClickListener(view -> pushItem(item));
	}

	public ArrayList<Item<String>> list(Context context) {
		return null;
	}

	public void onValueReceived(Item item) {
		//noinspection unchecked
		pushArray((ArrayList)item.getInstance());
	}

}
