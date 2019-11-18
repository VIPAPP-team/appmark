package com.vipapp.appmark2.utils.wrapper;

import android.content.res.AssetManager;

public class mAssets {
    public static AssetManager get(){
        return mActivity.get().getAssets();
    }

}
