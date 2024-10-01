package com.example.myapplication.Activity;

import android.content.Intent;
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
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Adapter.MyStudentAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.StudentViewModelFactory;
import com.example.myapplication.ViewModel.ViewModel_Student;

public class Student_List_Activity extends AppCompatActivity {
    ListView lv;
    MyStudentAdapter myStudentAdapter;
    Button b;
    Intent r;
    private ViewModel_Student viewModel_student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.student_list_activity);
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
        viewModel_student=new ViewModelProvider(Student_List_Activity.this, new StudentViewModelFactory(r)).get(ViewModel_Student.class);
        myStudentAdapter=new MyStudentAdapter(Student_List_Activity.this,R.layout.layout_student,viewModel_student.getMyList());
        lv.setAdapter(myStudentAdapter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel_student.passingStudentList(r);
                setResult(11,r);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        viewModel_student.passingStudentList(r);
        setResult(11,r);
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
            Intent r= new Intent(Student_List_Activity.this, Add_Student.class);
            startActivityForResult(r,99);
        }
        else{
            viewModel_student.deleteStudent();
            myStudentAdapter=new MyStudentAdapter(Student_List_Activity.this,R.layout.layout_student,viewModel_student.getMyList());
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
            viewModel_student.updateList(data);
            myStudentAdapter=new MyStudentAdapter(Student_List_Activity.this,R.layout.layout_student,viewModel_student.getMyList());
            lv.setAdapter(myStudentAdapter);
        }
    }
}

    /*@Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myList.clear();
        for (int i=0;i<savedInstanceState.getInt("numlist");i++){
            myList.add((Student) savedInstanceState.getSerializable("item"+i));
        }
        myStudentAdapter=new MyStudentAdapter(Student_List_Activity.this,R.layout.layout_student,myList);
        lv.setAdapter(myStudentAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i=0;i<myList.size();i++){
            outState.putSerializable("item"+i,myList.get(i));
        }
        outState.putInt("numlist",myList.size());
    }*/