package com.example.service_test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    int progress=0;
    MyBinder myBinder=new MyBinder();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Service","执行OnCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String uname = intent.getStringExtra("uname");
        Log.i("Service","执行OnStartCommand"+uname);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Service","执行OnDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {    //当有Activity与此Service绑定时调用，并给返回给Activity一个IBinder
        Log.i("Service","执行OnBind");
        new Thread(){
            @Override
            public void run() {
                while(true)
                {
                    myBinder.setProgress(++progress);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Service","执行OnUnBind");
        return super.onUnbind(intent);
    }
}
