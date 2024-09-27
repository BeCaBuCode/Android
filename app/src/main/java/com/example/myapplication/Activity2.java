package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Activity2 extends AppCompatActivity {
    ListView lv;
    ArrayList<Student> myList;
    MyStudentAdapter myStudentAdapter;
    Button b;
    Intent r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        androidx.appcompat.widget.Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar1);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Danh Sach Sinh Vien");
        lv=findViewById(R.id.lv2);
        b=findViewById(R.id.button2);
        r=getIntent();
        int numlist=r.getIntExtra("NumList",3);

        /*SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("DATA",MODE_PRIVATE);


            Gson gson=new Gson();
            String json=sharedPreferences.getString("student_data",null);
            Type type=new TypeToken<ArrayList<Student>>(){}.getType();
            myList=gson.fromJson(json,type);*/

            myList = new ArrayList<>();
            for (int i = 0; i < numlist; i++)
            {
                myList.add((Student) r.getSerializableExtra("model" + i));
            }

        myStudentAdapter=new MyStudentAdapter(Activity2.this,R.layout.layout_student,myList);
        lv.setAdapter(myStudentAdapter);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passing();
                finish();
            }
        });
    }
    private void passing(){
        for (int i=0;i<myList.size();i++){
            r.putExtra("model"+i,myList.get(i));
        }
        r.putExtra("size",myList.size());
        setResult(11,r);
    }
    @Override
    public void onBackPressed() {
        passing();
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater infalter=getMenuInflater();
        infalter.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        if (item.getItemId()==R.id.item1)
        {
            Intent r= new Intent(Activity2.this, StudentInfo.class);
            startActivityForResult(r,99);
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
            myStudentAdapter=new MyStudentAdapter(Activity2.this,R.layout.layout_student,myList);
            lv.setAdapter(myStudentAdapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==99 && resultCode==RESULT_OK)
        {
            String MSSV=data.getStringExtra("MSSV");
            String name=data.getStringExtra("Name");
            String birth=data.getStringExtra("Birth");
            myList.add(new Student(name,MSSV,birth));
            myStudentAdapter=new MyStudentAdapter(Activity2.this,R.layout.layout_student,myList);
            lv.setAdapter(myStudentAdapter);
        }
    }
}