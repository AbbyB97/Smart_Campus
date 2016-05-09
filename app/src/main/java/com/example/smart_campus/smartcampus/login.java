package com.example.smart_campus.smartcampus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button loginBobj;
    EditText username_ETobj,password_ETobj;
static String username_st, password_st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loginBobj=(Button)findViewById(R.id.login_Button);
        username_ETobj=(EditText)findViewById(R.id.login_username_ET);
        password_ETobj=(EditText)findViewById(R.id.login_passwordET);


        loginBobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_st=username_ETobj.getText().toString();
                password_st=password_ETobj.getText().toString();


                Toast.makeText(login.this, "logged in as "+username_st, Toast.LENGTH_SHORT).show();

            }
        });




    }

}
