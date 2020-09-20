package com.example.instantservice.logIn_SignUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.instantservice.ForgetPassword.ForgetPassword;
import com.example.instantservice.R;
import com.example.instantservice.map.MainActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    TextInputLayout username,password;
    private Button btn;
    private Button btn2;
    private Button btn1;

    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();

        if (val.isEmpty()) {
            username.setError("Enter UserName");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();

        if (val.isEmpty()) {
            password.setError("Enter Password");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    private void isUser() {
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("phoneNo").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);
                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(userEnteredUsername).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);
                        Intent intent = new Intent(login.this, MainActivity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    } else {
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                } else {
                    username.setError("No User Found");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        btn=(Button)findViewById(R.id.button3);
        btn2=(Button)findViewById(R.id.button2);
        btn1=(Button)findViewById(R.id.button1);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(login.this, signUp.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                    return;
                }else{
                    isUser();
                }
            }
        });



    }
}