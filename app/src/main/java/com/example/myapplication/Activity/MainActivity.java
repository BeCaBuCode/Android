package com.example.myapplication.Activity;

import android.content.Intent;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.Adapter.MyClassAdapter;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.ViewModel_Main;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    private MyClassAdapter myClassAdapter;
    int selectedindex;
    private ViewModel_Main viewModelMain;
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
        viewModelMain =new ViewModelProvider(MainActivity.this).get(ViewModel_Main.class);
        lv=findViewById(R.id.lv1);
        myClassAdapter=new MyClassAdapter(MainActivity.this,R.layout.layout_class, viewModelMain.getMyList());
        lv.setAdapter(myClassAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedindex=position;
                Intent r=new Intent(MainActivity.this, Student_List_Activity.class);
                viewModelMain.PassingInfo(r,position);
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
            Intent r=new Intent(MainActivity.this, Add_Class.class);
            startActivityForResult(r,100);
        }
        else{
            viewModelMain.deleteClass();
            myClassAdapter=new MyClassAdapter(MainActivity.this,R.layout.layout_class, viewModelMain.getMyList());
            lv.setAdapter(myClassAdapter);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==RESULT_OK)
        {
            viewModelMain.AddNewClass(data);
            myClassAdapter=new MyClassAdapter(MainActivity.this,R.layout.layout_class, viewModelMain.getMyList());
            lv.setAdapter(myClassAdapter);
        }
        if (requestCode==314 && resultCode==11)
        {
            viewModelMain.ReceiveBack(data, selectedindex);
            myClassAdapter=new MyClassAdapter(MainActivity.this,R.layout.layout_class,viewModelMain.getMyList());
            lv.setAdapter(myClassAdapter);
        }
    }
     /*@Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myList.clear();
        for (int i=0;i<savedInstanceState.getInt("num");i++){
            myList.add((SClass)savedInstanceState.getSerializable("model"+i));
        }
        myClassAdapter=new MyClassAdapter(MainActivity.this,R.layout.layout_class,myList);
        lv.setAdapter(myClassAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for (int i=0;i<myList.size();i++){
            outState.putSerializable("model"+i,myList.get(i));
        }
        outState.putInt("num",myList.size());
    }*/
}