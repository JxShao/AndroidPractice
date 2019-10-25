package com.example.listview_test;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowAllUserActivity extends AppCompatActivity {
    private ListView showAllUserView;
    List<User> user = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alluser_layout);      //注意：要先设置ContendView后才能findView
        showAllUserView = findViewById(R.id.all_lv);


        //模拟产生数据
        for(int i=0;i<100;i++)
        {
            User u = new User();
            u.setName("Jerry"+i);
            u.setAge(i);
            user.add(u);
        }

        adapter = new MyAdapter(this,R.layout.user_layout,user);
        showAllUserView.setAdapter(adapter);  //将填充数据的Adapter绑定到ListView 上





    }
}
