package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class Add_Class extends AppCompatActivity {
    EditText t1,t2;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_class);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        b=findViewById(R.id.button);
        t1=findViewById(R.id.editTextText4);
        t2=findViewById(R.id.editTextText5);
        Intent r=getIntent();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=t1.getText().toString();
                String name=t2.getText().toString();
                if (id.isEmpty() || name.isEmpty()){
                    Toast.makeText(Add_Class.this, "Invalid Information", Toast.LENGTH_SHORT).show();
                    return;
                }
                r.putExtra("ID",id);
                r.putExtra("Name",name);
                setResult(RESULT_OK,r);
                finish();
            }
        });
    }
}