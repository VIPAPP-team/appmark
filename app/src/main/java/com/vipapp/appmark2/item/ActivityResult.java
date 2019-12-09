package com.vipapp.appmark2.item;

import android.content.Intent;

public class ActivityResult {
    private Intent data;
    private int requestCode;
    private int resultCode;

    public ActivityResult(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public Intent getData() {
        return this.data;
    }
}