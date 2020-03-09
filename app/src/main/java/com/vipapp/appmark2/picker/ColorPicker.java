package com.vipapp.appmark2.picker;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.SeekBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.util.ColorUtils;
import com.vipapp.appmark2.widget.EditText;
import com.vipapp.appmark2.widget.TextView;

import java.util.Objects;

import static com.vipapp.appmark2.util.ColorUtils.toARGB;

public class ColorPicker extends DefaultPicker<Integer> {
    private boolean textInput = true;

    private int color = 0xff000000;

    private View background;
    private TextView ok;
    private EditText colorTv;
    private TextView red;
    private TextView green;
    private TextView blue;
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar ;
    private SeekBar blueSeekBar;


    public ColorPicker(PushCallback<Integer> callback) {
        super(callback, true);
        setView(R.layout.color_picker_dialog);
        findViews(getView());
        setCallbacks();
        updateUI();
    }

    private void findViews(View v){
        background = v.findViewById(R.id.background);
        colorTv = v.findViewById(R.id.color);
        red = v.findViewById(R.id.red);
        green = v.findViewById(R.id.green);
        blue = v.findViewById(R.id.blue);
        redSeekBar = v.findViewById(R.id.redSeekBar);
        greenSeekBar = v.findViewById(R.id.greenSeekBar);
        blueSeekBar = v.findViewById(R.id.blueSeekBar);
        ok = v.findViewById(R.id.ok);
    }

    private void setCallbacks(){
        redSeekBar.setOnSeekBarChangeListener(new mSeekBarListener());
        greenSeekBar.setOnSeekBarChangeListener(new mSeekBarListener());
        blueSeekBar.setOnSeekBarChangeListener(new mSeekBarListener());
        colorTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(textInput) {
                    textInput = false;
                    String colorText = charSequence.toString();
                    try {
                        setValue(Color.parseColor("#" + colorText));
                    } catch (Exception ignored) {}
                    textInput = true;
                }
            }
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            public void afterTextChanged(Editable editable) {}
        });
        ok.setOnClickListener(view -> pushItem(color));
    }

    @SuppressLint("SetTextI18n")
    private void updateUI(){
        String colorText = Objects.requireNonNull(colorTv.getText()).toString();
        int colorLen = colorText.length();
        String colorTextNew = ColorUtils.toString(color).substring(colorLen == 6? 3: 1);
        if(!colorText.equalsIgnoreCase(colorTextNew))
            colorTv.setText(colorTextNew);

        background.setBackgroundColor(color);
        int[] argb = toARGB(color);
        red.setText(Integer.toString(argb[1]));
        green.setText(Integer.toString(argb[2]));
        blue.setText(Integer.toString(argb[3]));
        redSeekBar.setProgress(argb[1]);
        greenSeekBar.setProgress(argb[2]);
        blueSeekBar.setProgress(argb[3]);
    }

    public void setValue(int color){
        this.color = color;
        updateUI();
    }

    private class mSeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            int[] argb = toARGB(color);
            int colorChange = 0;
            switch (seekBar.getId()){
                case R.id.redSeekBar:
                    colorChange = 1;
                    break;
                case R.id.greenSeekBar:
                    colorChange = 2;
                    break;
                case R.id.blueSeekBar:
                    colorChange = 3;
                    break;
            }
            argb[colorChange] = i;
            setValue(Color.argb(argb[0], argb[1], argb[2], argb[3]));
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}
        public void onStopTrackingTouch(SeekBar seekBar) {}
    }

}
