package com.example.instantservice.ForgetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.instantservice.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetNewPassword extends AppCompatActivity {
    TextInputLayout text1,text2;
    private Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        text1=findViewById(R.id.new_password);
        text2=findViewById(R.id.confirm_password);
        update=(Button)findViewById(R.id.set_new_password_btn);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = text1.getEditText().getText().toString();
                String name2 = text2.getEditText().getText().toString();
                if (name1.equals(name2)) {
                    Intent intent2=getIntent();
                    String phonenumber = intent2.getStringExtra("phoneNo");
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("users");
                    myRef.child(phonenumber).child("password").setValue(name1);
                    Intent intent=new Intent(SetNewPassword.this, ForgetPasswordSuccessMessage.class);
                    startActivity(intent);
                    Toast.makeText(SetNewPassword.this, "Your Password has been updated successfully", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(SetNewPassword.this, "password not matched", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}