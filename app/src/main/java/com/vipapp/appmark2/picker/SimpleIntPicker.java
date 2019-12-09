package com.vipapp.appmark2.picker;

import android.view.View;
import android.widget.NumberPicker;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;

public class SimpleIntPicker extends DefaultPicker<Integer> {
    private NumberPicker picker;

    public SimpleIntPicker(PushCallback<Integer> callback, int minValue, int maxValue) {
        super(callback);
        setView(R.layout.simple_int_picker_dialog);
        findViews(getView());
        setup(minValue, maxValue);
    }

    private void findViews(View v) {
        this.picker = v.findViewById(R.id.picker);
    }

    private void setup(int min, int max) {
        this.picker.setMinValue(min);
        this.picker.setMaxValue(max);
    }

    public void setValue(int value) {
        this.picker.setValue(value);
    }

    public void onDismiss() {
        pushItem(this.picker.getValue());
    }
}