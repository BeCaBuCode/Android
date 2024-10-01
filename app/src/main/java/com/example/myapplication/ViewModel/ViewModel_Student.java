package com.example.myapplication.ViewModel;

import android.content.Intent;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Class.Student;

import java.util.ArrayList;


public class ViewModel_Student extends ViewModel implements ViewModelProvider.Factory {
    ArrayList<Student> myList;

    public ViewModel_Student(Intent r){
        int numlist=r.getIntExtra("NumList",3);
        myList = new ArrayList<>();
        for (int i = 0; i < numlist; i++)
        {
            myList.add((Student) r.getSerializableExtra("model" + i));
        }
    }


    public ArrayList<Student> getMyList()
    {
        return myList;
    }

    public void passingStudentList(Intent r){
        for (int i=0; i<myList.size(); i++)
        {
            myList.get(i).setSelected(false);
        }
        for (int i=0;i<myList.size();i++){
            r.putExtra("model"+i,myList.get(i));
        }
        r.putExtra("size",myList.size());
    }

    public void updateList(Intent data){
        String MSSV=data.getStringExtra("MSSV");
        String name=data.getStringExtra("Name");
        String birth=data.getStringExtra("Birth");
        myList.add(new Student(name,MSSV,birth));
    }
    public void deleteStudent(){
        ArrayList<Integer> array=new ArrayList<Integer>();
        for (int i=0;i<myList.size();i++){
            if (myList.get(i).IsSelected()){
                array.add(i);
            }
        }
        if (array.isEmpty()) return;
        int j=0;
        int numremove=0;
        int sizeOri=myList.size();
        for (int i=0;i<sizeOri;i++)
        {
            if (i==array.get(j))
            {
                myList.remove(i);
                if (j<array.size()-1) j++;
                numremove++;
                array.set(j, array.get(j)-numremove);
                i--;
                sizeOri--;
            }
        }
    }
}
