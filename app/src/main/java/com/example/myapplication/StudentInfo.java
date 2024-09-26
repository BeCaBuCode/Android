package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StudentInfo extends AppCompatActivity {
    EditText t1,t2,t3;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        t1=findViewById(R.id.editTextText);
        t2=findViewById(R.id.editTextText2);
        t3=findViewById(R.id.editTextText3);
        b=findViewById(R.id.button1);
        Intent r=getIntent();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MSSV=t1.getText().toString();
                String Name=t2.getText().toString();
                String Birth=t3.getText().toString();
                r.putExtra("MSSV",MSSV);
                r.putExtra("Name",Name);
                r.putExtra("Birth",Birth);
                setResult(RESULT_OK,r);
                finish();
            }
        });
    }
}