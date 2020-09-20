package com.example.instantservice.logIn_SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.instantservice.ForgetPassword.SetNewPassword;
import com.example.instantservice.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity {

    TextInputLayout ename,eusername,eEmail,ePhoneno,esdress,ePass;
    private Button btn1;


    //condition of Signup
    private Boolean validateName() {
        String val = ename.getEditText().getText().toString();

        if (val.isEmpty()) {
            ename.setError("Enter Name");
            return false;
        }
        else {
            ename.setError(null);
            ename.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = eusername.getEditText().getText().toString();

        if (val.isEmpty()) {
            eusername.setError("Enter UserName");
            return false;
        } else if (val.length() >= 8) {
            eusername.setError("Long UserName");
            return false;
        }
         else {
            eusername.setError(null);
            eusername.setErrorEnabled(false);
            return true;
        }
    }
        private Boolean validateEmail() {
            String val = eEmail.getEditText().getText().toString();
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if (val.isEmpty()) {
                eEmail.setError("Enter Email");
                return false;
            } else if (!val.matches(emailPattern)) {
                eEmail.setError("Invalid email address");
                return false;
            } else {
                eEmail.setError(null);
                eEmail.setErrorEnabled(false);
                return true;
            }
        }
    private Boolean validatePhoneNo() {
        String val = ePhoneno.getEditText().getText().toString();

        if (val.isEmpty()) {
            ePhoneno.setError("Enter Phone No");
            return false;
        }else if (val.length() >= 11) {
            eusername.setError("Enter 11 digits phoneNumber");
            return false;
        }
            else {
            ePhoneno.setError(null);
            ePhoneno.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validadress() {
        String val = esdress.getEditText().getText().toString();

        if (val.isEmpty()) {
            esdress.setError("Enter Your Adress");
            return false;
        }
        else {
            esdress.setError(null);
            esdress.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = ePass.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            ePass.setError("Enter Password");
            return false;
        } else if (!val.matches(passwordVal)) {
            ePass.setError("Password is too weak");
            return false;
        } else {
            ePass.setError(null);
            ePass.setErrorEnabled(false);
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btn1=(Button)findViewById(R.id.button_1);


        ename=findViewById(R.id.name);
        eusername=findViewById(R.id.username);
        eEmail=findViewById(R.id.email);
        ePhoneno=findViewById(R.id.phoneNo);
        esdress=findViewById(R.id.location);
        ePass=findViewById(R.id.password);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validadress() | !validateUsername()){
                    return;
                }
                //get data from user
                String name = ename.getEditText().getText().toString();
                String username = eusername.getEditText().getText().toString();
                String email = eEmail.getEditText().getText().toString();
                String phoneNo = ePhoneno.getEditText().getText().toString();
                String adress = esdress.getEditText().getText().toString();
                String password = ePass.getEditText().getText().toString();

                //Call the next activity and pass phone no with it
                Intent intent = new Intent(signUp.this, OTPverfication.class);
                intent.putExtra("phoneNo", phoneNo);
                startActivity(intent);

                //database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users");

                UserDataClass dataclass =new UserDataClass(name, username, email, phoneNo,adress, password);
                myRef.child(phoneNo).setValue(dataclass);
                //Toast.makeText(signUp.this, "Account cretaed Succesfully", Toast.LENGTH_SHORT).show();
            }
        });

    }

}