package com.example.instantservice.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.instantservice.R;

public class notification extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        t1 = (TextView)findViewById(R.id.text1);
        t2=(TextView)findViewById(R.id.text2);
        t3=(TextView)findViewById(R.id.text3);
        t4=(TextView)findViewById(R.id.text4);
        t5=(TextView)findViewById(R.id.text5);

        Intent intent2=getIntent();
        String name = intent2.getStringExtra("name");
        String username = intent2.getStringExtra("cost");
        String phoneNo = intent2.getStringExtra("phoneNo");

        t1.setText(name);
        t2.setText(username);
        t3.setText(phoneNo);
        t4.setText("Rent");
        t5.setText(phoneNo);
    }
}
