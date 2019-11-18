package com.vipapp.appmark2.picker;

import androidx.annotation.StringRes;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callbacks.PushCallback;
import com.vipapp.appmark2.utils.ContextUtils;
import com.vipapp.appmark2.widget.EditText;
import com.vipapp.appmark2.widget.TextView;

import java.util.Objects;

public class StringPicker extends Picker<String> {

    private TextView title;
    private TextView ok;
    private EditText value;

    public StringPicker(PushCallback<String> callback)  {
        super(callback);
        setView(R.layout.string_picker_dialog);
        findViews(getView());
        setCallbacks();
    }

    private void findViews(View v){
        title = v.findViewById(R.id.title);
        ok = v.findViewById(R.id.ok);
        value = v.findViewById(R.id.value);
    }
    private void setCallbacks(){
        ok.setOnClickListener(view -> pushItem(Objects.requireNonNull(value.getText()).toString().trim()));
    }

    public void setTitle(String title){
        this.title.setText(title);
    }
    public void setTitle(@StringRes int res){
        this.title.setText(res);
    }

    public void setHint(String hint){
        this.value.setHint(hint);
    }
    public void setHint(@StringRes int hint){
        this.value.setHint(hint);
    }

    public void setText(String text){
        value.setText(text);
    }
    public void setText(@StringRes int text){
        value.setText(text);
    }

    public void setError(String error){
        value.setError(error);
    }
    public void setError(@StringRes int error){
        value.setError(ContextUtils.context.getString(error));
    }

}
