package com.vipapp.appmark2.items.setting.type.pickable;

import android.annotation.SuppressLint;

import com.vipapp.appmark2.items.setting.type.PickableSetting;
import com.vipapp.appmark2.picker.Picker;
import com.vipapp.appmark2.picker.SimpleIntPicker;
import com.vipapp.appmark2.utils.wrapper.mSharedPreferences;
import com.vipapp.appmark2.widget.TextView;

public class SimpleIntSetting extends PickableSetting<Integer> {

    private int minValue;
    private int maxValue;

    public SimpleIntSetting(int minValue, int maxValue){
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @SuppressLint("SetTextI18n")
    public void setValue(Integer value, TextView tv) {
        tv.setText(value.toString());
    }

    public Picker<Integer> getPicker() {
        SimpleIntPicker picker = new SimpleIntPicker(null, minValue, maxValue);
        picker.setValue(get());
        return picker;
    }

    public void save(Integer value) {
        mSharedPreferences.putInteger(item.getSettingName(), value);
    }
    public Integer get() {
        return mSharedPreferences.getInteger(item.getSettingName(), item.default_value);
    }

}
