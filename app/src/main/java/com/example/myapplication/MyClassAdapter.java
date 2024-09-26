package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyClassAdapter extends ArrayAdapter<SClass> {
    Activity content;
    int idLayout;
    ArrayList<SClass> myList;

    public MyClassAdapter(Activity content, int idLayout, ArrayList<SClass> myList) {
        super(content, idLayout,myList);
        this.content = content;
        this.idLayout = idLayout;
        this.myList = new ArrayList<>();
        this.myList.addAll(myList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlater=content.getLayoutInflater();
        convertView=myFlater.inflate(idLayout,null);
        SClass myClass=myList.get(position);
        TextView t1=convertView.findViewById(R.id.textView4);
        t1.setText(myClass.getId()+"");
        TextView t2=convertView.findViewById(R.id.textView5);
        t2.setText(myClass.getClassName());
        CheckBox b=convertView.findViewById(R.id.checkBox2);
        b.setChecked(myClass.IsSelected());
        b.setText("");
        b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getItem(position).setSelected(isChecked);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
