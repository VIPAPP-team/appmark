package com.vipapp.appmark2.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.utils.TextUtils;
import com.vipapp.appmark2.utils.Toast;
import com.vipapp.appmark2.widget.TextView;

public class DebugActivity extends BaseActivity {

    String error_text;
    TextView error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        error_text = getIntent().getStringExtra("error");
        findViews();
        setupViews();
        setCallbacks();
    }

    public void findViews(){
        error = findViewById(R.id.error);
    }
    public void setupViews(){
        error.setText(error_text);
    }
    public void setCallbacks(){
        error.setOnClickListener(view -> {
            TextUtils.copyToClipboard(error_text);
            Toast.show(R.string.successfully_copied);
        });
    }

}
