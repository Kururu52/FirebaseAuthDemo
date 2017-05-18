package com.example.android.firebaseauthdemo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ButtonRegister;
    private EditText EditTextMail ;
    private EditText EditTextPassword ;
    private TextView TextViewSignUp ;
    private ProgressDialog ProgressDialog ;
    private FirebaseAuth FirebaseAuth ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth = FirebaseAuth.getInstance() ;
        ProgressDialog = new ProgressDialog(this) ;
        ButtonRegister = (Button) findViewById(R.id.ButtonRegister);
        EditTextMail = (EditText) findViewById(R.id.EditTextMail);
        EditTextPassword = (EditText) findViewById(R.id.EditTextPassword);
        TextViewSignUp =(TextView) findViewById(R.id.TextViewSignUp) ;

        ButtonRegister.setOnClickListener(this);
        TextViewSignUp.setOnClickListener(this);


    }

    private void RegisterUser() {
        String email = EditTextMail.getText().toString().trim();
        String password = EditTextPassword.getText().toString().trim() ;

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "please insert email ",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "please insert password ",Toast.LENGTH_SHORT).show();
            return;
        }

        ProgressDialog.setMessage("registering user");
        ProgressDialog.show();
        FirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Register succesfull", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "cpould not register try againt", Toast.LENGTH_SHORT).show();

                }

            }
        }) ;

    }

    @Override
    public void onClick(View view) {
            if (view == ButtonRegister) {
                RegisterUser() ;
            }
        if (view == TextViewSignUp) {

        }
    }


}
