package com.example.instantservice.ForgetPassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.instantservice.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpforgetpassword extends AppCompatActivity {

    Button verify_btn;
    EditText phoneNoEnteredByTheUser;
    ProgressBar progressBar;
    String verificationCodeBySystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpforgetpassword);

        verify_btn = findViewById(R.id.verify_btn);
        phoneNoEnteredByTheUser = findViewById(R.id.verification_code_entered_by_user);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        Intent intent=getIntent();
        String phonenumber = intent.getStringExtra("phoneNo");
        sendVerificationCodeToUser(phonenumber);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = phoneNoEnteredByTheUser.getText().toString();

                if (code.isEmpty() || code.length() < 6) {
                    phoneNoEnteredByTheUser.setError("Wrong OTP...");
                    phoneNoEnteredByTheUser.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);

            }
        });



    }
    private void progress(){
        progressBar.setVisibility(View.GONE);
    }

    private void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+880" + phoneNo,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,   // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }
    public void data(){
        Intent intent2=getIntent();
        String phonenumber = intent2.getStringExtra("phoneNo");

        Intent intent3=new Intent(otpforgetpassword.this, SetNewPassword.class);
        intent3.putExtra("phoneNo",phonenumber);
        startActivity(intent3);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    verificationCodeBySystem = s;
                }

                @Override
                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(FirebaseException e) {
                    Toast.makeText(otpforgetpassword.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            };

    private void verifyCode(String codeByUser) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
        signInTheUserByCredentials(credential);

    }

    private void signInTheUserByCredentials(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(otpforgetpassword.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            data();
                            //Perform Your required action here to either let the user sign In or do something required
                            // Intent intent = new Intent(getApplicationContext(), login.class);
                            // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //startActivity(intent);

                        } else {
                            Toast.makeText(otpforgetpassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}