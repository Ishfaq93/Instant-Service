package com.example.instantservice.ForgetPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.instantservice.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgetPassword extends AppCompatActivity {

    private Button btn;
    TextInputLayout text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        btn=(Button)findViewById(R.id.forget_password_next_btn);
        text=findViewById(R.id.forget_password_phone_number);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String phonenumber =text.getEditText().getText().toString();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

                Query checkUser = reference.orderByChild("phoneNo").equalTo(phonenumber);

                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            text.setError(null);
                            text.setErrorEnabled(false);
                            Intent intent2 = new Intent(ForgetPassword.this, otpforgetpassword.class);
                           intent2.putExtra("phoneNo", phonenumber);
                           startActivity(intent2);

                            //Intent intent2 = new Intent(ForgetPassword.this, SetNewPassword.class);
                            //intent2.putExtra("phoneNo", phonenumber);
                           // Intent intent = new Intent(ForgetPassword.this, SetNewPassword.class);
                           // intent.putExtra("phoneNo", phonenumber);
                           // startActivity(intent);
                        }else {
                            text.setError("No user found");
                            text.requestFocus();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }
}