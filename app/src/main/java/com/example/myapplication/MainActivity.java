package com.example.myapplication;

import static java.lang.System.in;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<SClass> myList;
    MyClassAdapter myClassAdapter;
    ArrayList<Student> s1,s2,s3;
    int selectedindex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
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

        lv=findViewById(R.id.lv1);
        myList=new ArrayList<>();
        myList.add(new SClass("1","Ky thuat phan mem",s1));
        myList.add(new SClass("2","Khoa hoc may tinh",s2));
        myList.add(new SClass("3","Mang may tinh",s3));

        myClassAdapter=new MyClassAdapter(MainActivity.this,R.layout.layout_class,myList);
        lv.setAdapter(myClassAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedindex=position;
                Intent r=new Intent(MainActivity.this,Activity2.class);
                for (int i=0;i<myList.get(position).getMyList().size();i++){
                    r.putExtra("model"+i,myList.get(position).getMyList().get(i));
                }
                r.putExtra("NumList",myList.get(position).getMyList().size());
                startActivityForResult(r,314);
            }
        });

        androidx.appcompat.widget.Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Danh Sach Lop");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater infalter=getMenuInflater();
        infalter.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.item1){
            Intent r=new Intent(MainActivity.this,Add_Class.class);
            startActivityForResult(r,100);
        }
        else{
            ArrayList<Integer> array=new ArrayList<Integer>();
            for (int i=0;i<myList.size();i++){
                if (myList.get(i).IsSelected()){
                    array.add(i);
                }
            }
            if (array.isEmpty()) return true;
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
            myClassAdapter=new MyClassAdapter(MainActivity.this,R.layout.layout_class,myList);
            lv.setAdapter(myClassAdapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==RESULT_OK){
            String ID=data.getStringExtra("ID");
            String name=data.getStringExtra("Name");
            ArrayList<Student> temp=new ArrayList<>();
            temp.add(new Student("New Student 1","2352xxxx","xx/xx/xxxx"));
            temp.add(new Student("New Student 2","2352xxxx","xx/xx/xxxx"));
            temp.add(new Student("New Student 3","2352xxxx","xx/xx/xxxx"));
            myList.add(new SClass(ID,name,temp));
            myClassAdapter=new MyClassAdapter(MainActivity.this,R.layout.layout_class,myList);
            lv.setAdapter(myClassAdapter);
        }
        if (requestCode==314 && resultCode==11){
            ArrayList<Student> newlist=new ArrayList<>();
            for (int i=0;i<data.getIntExtra("size",3);i++){
                newlist.add((Student)data.getSerializableExtra("model"+i));
            }
            myList.get(selectedindex).setMyList(newlist);
        }
    }

}