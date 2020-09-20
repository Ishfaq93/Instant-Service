package com.example.instantservice.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.instantservice.R;
import com.example.instantservice.logIn_SignUp.login;

public class ForgetPasswordSuccessMessage extends AppCompatActivity {


    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_success_message);
        b1=(Button)findViewById(R.id.success_message_btn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForgetPasswordSuccessMessage.this, login.class);
                startActivity(intent);
            }
        });
    }
}