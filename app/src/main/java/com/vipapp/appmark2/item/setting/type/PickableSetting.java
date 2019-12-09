package com.vipapp.appmark2.item.setting.type;

import android.content.SharedPreferences;
import android.view.View;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.item.SettingsType;
import com.vipapp.appmark2.item.SettingsItem;
import com.vipapp.appmark2.picker.DefaultPicker;
import com.vipapp.appmark2.widget.TextView;

@SuppressWarnings("WeakerAccess")
public abstract class PickableSetting<T> extends SettingsType<SettingsItem<T>> {

    public SettingsItem<T> item;

    public PickableSetting(){
        super(R.layout.setting_pickable_default);
    }
    public PickableSetting(int xml_resource) {
        super(xml_resource);
    }

    public void setupView(View v, SharedPreferences prefs, SettingsItem<T> item) {
        this.item = item;
        // find views
        TextView title = v.findViewById(R.id.title);
        TextView subtitle = v.findViewById(R.id.subtitle);
        TextView value = v.findViewById(R.id.value);
        // setup views
        title.setText(item.getSettingTitle(v.getContext()));
        subtitle.setText(item.getSettingSubtitle(v.getContext()));
        setValue(get(), value);
        // set callbacks
        v.setOnClickListener(view -> {
            DefaultPicker<T> picker = getPicker();
            picker.addCallback(result -> {
                setValue(result, value);
                save(result);
            });
            picker.show();
        });
    }

    public abstract void setValue(T value, TextView tv);
    public abstract DefaultPicker<T> getPicker();

    public abstract void save(T value);
    public abstract T get();

}
