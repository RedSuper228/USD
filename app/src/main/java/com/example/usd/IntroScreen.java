package com.example.usd;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class IntroScreen extends AppCompatActivity  {
    Handler handler;
    ImageView pic;
    FirebaseAuth auth;
    ProgressDialog progressDialog;
    Dialog d;
    FirebaseUser firebaseUser;
    Button btn_intro_login, btn_intro_signup;// buttons in screen
    Button btn_signup_dialog, btn_login_dialog;// buttons in dialogs
    TextView signupErrorTextView, loginErrorTextView;
    EditText et_login_email, et_login_pass, et_signup_email, et_signup_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        btn_intro_login = findViewById(R.id.btn_intro_login);
        btn_intro_signup = findViewById(R.id.btn_intro_signup);

        pic = findViewById(R.id.iv_logo);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*Intent intent = new Intent(IntroScreen.this,
                        MainActivity.class);
                startActivity(intent);
                finish();*/
                btn_intro_signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createSignupDialog();
                    }
                });

                btn_intro_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createLoginDialog();
                    }
                });

            }
        }, 3000);

        Animation animation = AnimationUtils.
                loadAnimation(getApplicationContext(),
                        R.anim.mixed_anim);
        pic.startAnimation(animation);



    }



    private void signUp() {
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(et_signup_email.getText().toString(),
                        et_signup_pass.getText().toString()).
                addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    signupErrorTextView.setText(
                                            "Successfully registered");

                                    Log.d("tag", "yes");
                                    moveOn();

                                } else {
                                    signupErrorTextView.setText(
                                            "Registration Error");
                                    Log.d("tag", task.getException().getMessage());
                                }
                                d.dismiss();
                                progressDialog.dismiss();
                            }
                        });

    }

    private void moveOn() {
        Intent intent = new Intent(IntroScreen.this,
                MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void signIn() {
        progressDialog.setMessage("SignIn Please Wait...");
        progressDialog.show();

        auth.signInWithEmailAndPassword(et_login_email.getText().toString(),
                        et_login_pass.getText().toString())
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    loginErrorTextView.setText("auth_success");
                                    moveOn();

                                } else {
                                    loginErrorTextView.setText("auth_failed");
                                }
                                d.dismiss();
                                progressDialog.dismiss();

                            }
                        });

    }

    private void createSignupDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.signup_dialog);
        d.setTitle("Signup");
        d.setCancelable(true);
        signupErrorTextView = d.findViewById(R.id.signupErrorTextView);
        et_signup_email = d.findViewById(R.id.et_signup_email);
        et_signup_pass = d.findViewById(R.id.et_signup_pass);
        btn_signup_dialog = d.findViewById(R.id.btn_signup_dialog);
        btn_signup_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        d.show();
    }

    private void createLoginDialog() {
        d = new Dialog(this);
        d.setContentView(R.layout.login_dialog);
        d.setTitle("Signin");
        d.setCancelable(true);
        loginErrorTextView = d.findViewById(R.id.loginErrorTextView);
        et_login_email = d.findViewById(R.id.et_login_email);
        et_login_pass = d.findViewById(R.id.et_login_pass);
        btn_login_dialog = d.findViewById(R.id.btn_login_dialog);
        btn_login_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        d.show();
    }
}