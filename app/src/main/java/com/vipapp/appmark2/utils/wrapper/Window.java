package com.vipapp.appmark2.utils.wrapper;

public class Window {
    public static android.view.Window get(){
        return mActivity.get().getWindow();
    }
}
