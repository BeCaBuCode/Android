package com.example.myapplication;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MyStudentAdapter extends ArrayAdapter<Student> {
    Activity context;
    int idLayout;
    ArrayList<Student> myList;

    public MyStudentAdapter(Activity context1, int idLayout, ArrayList<Student> myList) {
        super(context1, idLayout,myList);
        this.context = context1;
        this.idLayout = idLayout;
        this.myList=new ArrayList<>();
        this.myList.addAll(myList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myFlater=context.getLayoutInflater();
        convertView=myFlater.inflate(idLayout,null);
        Student s=myList.get(position);
        TextView t1=convertView.findViewById(R.id.textView2);
        t1.setText(s.getMSSV());
        TextView t2=convertView.findViewById(R.id.textView);
        t2.setText(s.getName());
        TextView t3=convertView.findViewById(R.id.textView3);
        t3.setText(s.getBirthday());
        CheckBox b=convertView.findViewById(R.id.checkBox);
        b.setChecked(s.IsSelected());
        b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getItem(position).setSelected(isChecked);
                notifyDataSetChanged();
            }
        });
        b.setText("");
        return convertView;
    }
}
