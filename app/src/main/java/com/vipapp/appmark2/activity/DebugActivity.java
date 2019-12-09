package com.vipapp.appmark2.activity;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.util.TextUtils;
import com.vipapp.appmark2.util.Toast;
import com.vipapp.appmark2.widget.TextView;

public class DebugActivity extends BaseActivity {

    String error_text;
    TextView error;

    @Override
    public Integer onCreateView() {
        return R.layout.activity_debug;
    }
    @Override
    public void findViews(){
        error = f(R.id.error);
    }
    @Override
    public void setCallbacks(){
        error.setOnClickListener(view -> {
            TextUtils.copyToClipboard(error_text);
            Toast.show(R.string.successfully_copied);
        });
    }
    @Override
    public void init() {
        error_text = getIntent().getStringExtra("error");
    }
    @Override
    public void setup(){
        error.setText(error_text);
    }

}
