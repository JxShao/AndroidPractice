package com.example.service_test;

import android.os.Binder;

public class MyBinder extends Binder {  //用于Activity与Service之间传递信息
    private int progress;
    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
