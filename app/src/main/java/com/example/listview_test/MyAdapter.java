package com.example.listview_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<User> {
    private List<User> user;
    private int rs;

    public MyAdapter(@NonNull Context context, int resource, @NonNull List<User> objects) {
        //从外界传来的信息，resource为数据布局（即单一单元格中的布局），objects为数据
        super(context, resource);
        this.rs=resource;
        this.user=objects;

    }

    @Override
    public int getCount() {
        return user.size();
    }

    @Nullable
    @Override
    public User getItem(int position) {
        return user.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //在此重载函数中，使用LayoutInflater  实现View与数据的结合
        convertView = LayoutInflater.from(this.getContext()).inflate(this.rs,parent,false);
                      //infalte方法按要求吹塑出一个View
        TextView nameTv = convertView.findViewById(R.id.user_name);
        TextView ageTv = convertView.findViewById(R.id.user_age);
        User user = getItem(position);
        nameTv.setText(user.getName()); //填充数据
        ageTv.setText(user.getAge()+"");
        return convertView;
    }
}
