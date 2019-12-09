package com.vipapp.appmark2.util.wrapper;

import android.content.res.AssetManager;

public class mAssets {
    public static AssetManager get(){
        return mActivity.get().getAssets();
    }

}
