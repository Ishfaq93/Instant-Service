package com.example.instantservice.category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.instantservice.R;

public class Category extends AppCompatActivity {

    private Button b;
    private Button b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        b =(Button)findViewById(R.id.btn2);
        b2=(Button)findViewById(R.id.button4);


       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent= new Intent(Category.this , electric.class);
               startActivity(intent);
           }
       });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Category.this , other.class);
                startActivity(intent);
            }
        });
    }
}
