package com.example.service_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startMyServiceBtn;
    private Button stopMyServiceBtn;
    private  Button bindToMyServiceBtn;
    private Button unbindMyServiceBtn;
    private ProgressBar progressBar;

    private static boolean stop=false;
    private  MyBinder myBinder;
    private ServiceConnection serConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stopMyServiceBtn=findViewById(R.id.stopMyServiceBtn);
        startMyServiceBtn=findViewById(R.id.startMyServiceBtn);
        bindToMyServiceBtn=findViewById(R.id.bindToMyServiceBtn);
        unbindMyServiceBtn=findViewById(R.id.unbindToMyServiceBtn);

        progressBar=findViewById(R.id.pb);

        stopMyServiceBtn.setOnClickListener(this);
        startMyServiceBtn.setOnClickListener(this);
        bindToMyServiceBtn.setOnClickListener(this);
        unbindMyServiceBtn.setOnClickListener(this);

        serConn=new ServiceConnection() {          //使用内部类的方式构造ServiceConnection
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                  //此方法与Service绑定成功后自动调用，并且可从中获取Service回传的IBinder,Activity与Service之间通过iBinder传递信息
                Log.i("Activity","serviceConnected");
                myBinder=(MyBinder)iBinder;
                new Thread()
                {
                    @Override
                    public void run() {
                        while(!stop)  //后期如需停止就改变stop的值
                        {
                            int p=myBinder.getProgress();
                            progressBar.setProgress(p);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }.start();


            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.i("Service","serviceDisConnected");
            }
        };
    }

    public void startMyService()
    {
        Intent intent = new Intent(this,MyService.class);  //用上下文和Service类信息构造一个Intent
        intent.putExtra("uname","Jerry");
        startService(intent);   //用intend显式启动service；
    }
    public void stopMyService()
    {
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }

    public void bindToMyService()
    {
        Intent intent=new Intent(this,MyService.class);
        bindService(intent,serConn,BIND_AUTO_CREATE);   //通过绑定方式调用service，主要传入Intent对象和ServiceConnection对象
    }
    public void unbindToMyService()
    {
        unbindService(serConn);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.startMyServiceBtn:
                startMyService();
                break;
            case R.id.stopMyServiceBtn:
                stopMyService();
                break;
            case R.id.bindToMyServiceBtn:
                bindToMyService();
                break;
            case R.id.unbindToMyServiceBtn:
                unbindToMyService();
                break;
        }

    }
}
