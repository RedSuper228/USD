package com.example.usd;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {

    EditText et_fullname,et_email,et_password,et_password2;
    Button btn_register;
    TextView tv_login;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        getSupportActionBar().hide();


        et_fullname   = findViewById(R.id.fullName);
        et_email      = findViewById(R.id.Email);
        et_password   = findViewById(R.id.password);
        et_password2  = findViewById(R.id.password2);
        btn_register= findViewById(R.id.registerBtn);
        tv_login   = findViewById(R.id.createText);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // Try to create account
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    et_email.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    et_password.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    et_password.setError("Password Must be >= 6 Characters");
                    return;
                }

                if(!password.equals(et_password2.getText().toString().trim())){
                    et_password2.setError("Password Must be the same in both fields");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                // Create account
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterScreen.this,"User Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {
                            Toast.makeText(RegisterScreen.this,"Error !" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });

        //Go to Login Screen
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginScreen.class));
            }
        });

    }
}
