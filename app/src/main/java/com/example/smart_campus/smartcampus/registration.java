package com.example.smart_campus.smartcampus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



    public class registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        static String[] account_type = { "Student", "HOD", "Faculty"  };
        static String user_nameV,email_adressV,passwordV;
        EditText username_ETobj,email_adressETobj,password_ETobj;
        Button registerB;

        Spinner dropdown;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            username_ETobj=(EditText)findViewById(R.id.ET_name_id);
            email_adressETobj=(EditText)findViewById(R.id.ET_emailadress_id);
            password_ETobj=(EditText)findViewById(R.id.ET_password_id);
            registerB=(Button)findViewById(R.id.register_button);
            dropdown = (Spinner) findViewById(R.id.spinner1);
            dropdown.setOnItemSelectedListener(this);
            //Creating the ArrayAdapter instance having the user category list
            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,account_type);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            dropdown.setAdapter(aa);




            registerB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    user_nameV=username_ETobj.getText().toString();
                    email_adressV=email_adressETobj.getText().toString();
                    passwordV=password_ETobj.getText().toString();

                    Toast.makeText(registration.this, ""+passwordV, Toast.LENGTH_SHORT).show();
                }
            });

        }


        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {


        }}

