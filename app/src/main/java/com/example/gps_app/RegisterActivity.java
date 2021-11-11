package com.example.gps_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText et_email,et_username,et_phone,et_password,et_confirm_password;
        et_email=findViewById(R.id.et_email) ;
        et_username=findViewById(R.id.et_username) ;
        et_phone=findViewById(R.id.et_phone) ;
        et_password=findViewById(R.id.et_password) ;
        et_confirm_password=findViewById(R.id.et_confirm_password) ;
        FirebaseAuth fAuth;
        fAuth=FirebaseAuth.getInstance() ;
        Button button_signin;
        button_signin=findViewById(R.id.button_signin);

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    et_email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    et_password.setError("password is required");
                    return;
                }
                if (password.length() < 6) {
                    et_password.setError("password Must be 6 characters");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Successful", Toast.LENGTH_LONG).show();
                                    Intent t = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(t);

                                } else {
                                    Toast.makeText(RegisterActivity.this, "Error !", Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                );











            }
        });

    }}