package com.example.myapplication.ViewModel;

import android.content.Intent;

import com.example.myapplication.Class.SClass;
import com.example.myapplication.Class.Student;

import java.util.ArrayList;

public class ViewModel_Main extends androidx.lifecycle.ViewModel {
    public ArrayList<Student> s1,s2,s3;
    private ArrayList<SClass> myList;
    public ViewModel_Main() {
        Initial();
    }
    public ArrayList<SClass> getMyList(){
        return myList;
    }
    public void Initial(){
        s1=new ArrayList<>();
        s2=new ArrayList<>();
        s3=new ArrayList<>();
        s1.add(new Student("Student 1","23520950","26/26/1205"));
        s1.add(new Student("Student 2","23520906","18/08/2005"));
        s1.add(new Student("Student 3","23520911","26/26/2105"));

        s2.add(new Student("Student 4","23520950","26/26/1205"));
        s2.add(new Student("Student 5","23520912","18/08/2005"));
        s2.add(new Student("Student 6","23520977","26/26/2105"));

        s3.add(new Student("Student 7","23520950","26/26/1205"));
        s3.add(new Student("Student 8","23520906","18/08/2005"));
        s3.add(new Student("Student 9","23520911","26/26/2105"));
        myList=new ArrayList<>();
        myList.add(new SClass("1","Ky thuat phan mem",s1));
        myList.add(new SClass("2","Khoa hoc may tinh",s2));
        myList.add(new SClass("3","Mang may tinh",s3));
    }
    public void AddNewClass(Intent data)
    {
        String ID=data.getStringExtra("ID");
        String name=data.getStringExtra("Name");
        ArrayList<Student> temp=new ArrayList<>();
        temp.add(new Student("New Student 1","2352xxxx","xx/xx/xxxx"));
        temp.add(new Student("New Student 2","2352xxxx","xx/xx/xxxx"));
        temp.add(new Student("New Student 3","2352xxxx","xx/xx/xxxx"));
        myList.add(new SClass(ID,name,temp));

    }
    public void ReceiveBack(Intent data,int selectedindex){
        for (int i=0;i<myList.size();i++)
        {
            myList.get(i).setSelected(false);
        }
        ArrayList<Student> newlist=new ArrayList<>();
        for (int i=0;i<data.getIntExtra("size",3);i++){
            newlist.add((Student)data.getSerializableExtra("model"+i));
        }
        myList.get(selectedindex).setMyList(newlist);
    }
    public void PassingInfo(Intent r,int position){
        for (int i = 0; i< myList.get(position).getMyList().size(); i++)
        {
            r.putExtra("model"+i, myList.get(position).getMyList().get(i));
        }
        r.putExtra("NumList", myList.get(position).getMyList().size());
    }
    public void deleteClass()
    {
        ArrayList<Integer> array=new ArrayList<Integer>();
        for (int i = 0; i< myList.size(); i++){
            if (myList.get(i).IsSelected()){
                array.add(i);
            }
        }
        if (array.isEmpty()) return;
        int j=0;
        int numremove=0;
        int sizeOri= myList.size();
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
