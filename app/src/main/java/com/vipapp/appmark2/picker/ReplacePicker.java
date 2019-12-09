package com.vipapp.appmark2.picker;

import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.TransformedItem;
import com.vipapp.appmark2.widget.EditText;
import com.vipapp.appmark2.widget.TextView;

import java.util.Objects;

public class ReplacePicker extends DefaultPicker<TransformedItem<String, String>> {

    private EditText replaceable;
    private EditText replacement;
    private TextView ok;

    public ReplacePicker(PushCallback<TransformedItem<String, String>> callback) {
        super(callback);
        setView(R.layout.replace_picker_dialog);
        findViews(getView());
        setCallbacks();
    }

    private void findViews(View v){
        replaceable = v.findViewById(R.id.replaceable);
        replacement = v.findViewById(R.id.replacement);
        ok = v.findViewById(R.id.ok);
    }
    private void setCallbacks(){
        ok.setOnClickListener(view -> {
            pushItem(new TransformedItem<>(Objects.requireNonNull(replaceable.getText()).toString(), Objects.requireNonNull(replacement.getText()).toString()));
            cancel();
        });
    }

}
