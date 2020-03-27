package com.vipapp.appmark2.menu;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.Item;
import com.vipapp.appmark2.util.ArrayUtils;
import com.vipapp.appmark2.util.TextUtils;
import com.vipapp.appmark2.widget.CodeText;
import com.vipapp.appmark2.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.vipapp.appmark2.util.Const.MATCHES_HINT_COLOR;

public class HintsMenu extends DefaultMenu<CodeText.Hint, HintsMenu.HintsHolder> {
    public static int ARRAY_PUSHED = 0;
    public static int TEXT_CHANGED = 1;
    public static int TEXT_INSERTED = 2;
    public static int SHOW_POPUP = 3;

    private String word = "";

    private ArrayList<CodeText.Hint> hints;
    @Override
    public ArrayList<CodeText.Hint> list(Context context) {
        return hints;
    }

    @Override
    public void bind(HintsHolder vh, CodeText.Hint item, int i) {
        Spannable text = new SpannableString(item.getBody());
        TextUtils.applyPatternUI(Pattern.compile(word, Pattern.LITERAL), MATCHES_HINT_COLOR, text);

        vh.hint_text.setText(text);
        vh.itemView.setOnClickListener(view -> pushItem(new Item<>(TEXT_INSERTED, item.getInsertValue())));
        vh.hint_icon.setImageResource(item.getImage());
    }

    @Override
    public void onValueReceived(Item item) {
        super.onValueReceived(item);
        if(item.getType() == ARRAY_PUSHED) {
            //noinspection unchecked
            hints = (ArrayList<CodeText.Hint>) item.getInstance();
        }
        if(item.getType() == TEXT_CHANGED){
            if(hints != null) {
                String word = (String) item.getInstance();
                this.word = word;
                ArrayList<CodeText.Hint> filtered = ArrayUtils.filter(hints, hint -> hint.getBody().contains(word));
                Collections.sort(filtered, (item1, item2) -> {
                    String body1 = item1.getBody();
                    String body2 = item2.getBody();
                    if(body1.startsWith(word) && !body2.startsWith(word))
                        return -1;
                    if(body2.startsWith(word) && !body1.startsWith(word))
                        return 1;
                    return body1.compareTo(body2);
                });
                pushArray(filtered, true, true);
                pushItem(new Item<>(SHOW_POPUP, filtered.size() > 0 && !word.equals("")));
            }
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.hints_default;
    }

    @Override
    public HintsHolder getViewHolder(ViewGroup parent, int itemType) {
        return new HintsHolder(inflate(parent));
    }

    static class HintsHolder extends RecyclerView.ViewHolder {
        public ImageView hint_icon;
        public TextView hint_text;

        public HintsHolder(@NonNull View itemView) {
            super(itemView);
            hint_icon = itemView.findViewById(R.id.hint_icon);
            hint_text = itemView.findViewById(R.id.hint_text);
        }
    }
}
