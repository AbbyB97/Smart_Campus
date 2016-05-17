package com.example.smart_campus.smartcampus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.ArrayList;


public class registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> account_type = new ArrayList<String>();
    //static String[] account_type = { "please select account type","Student", "HOD", "Faculty"  };
    static String user_nameV, email_adressV, passwordV, account_categoryV;
    EditText username_ETobj, email_adressETobj, password_ETobj;
    Button registerB;
    TextView loginB;
    Spinner dropdown;
    ProgressDialog loading_dialog;
    int flag_dropdown=0;

    @Override
    public void onBackPressed() {
        Intent backact= new Intent( getApplicationContext(),login.class);
        startActivity(backact);

        overridePendingTransition(R.anim.right_left, R.anim.left_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username_ETobj = (EditText) findViewById(R.id.ET_name_id);
        email_adressETobj = (EditText) findViewById(R.id.ET_emailadress_id);
        password_ETobj = (EditText) findViewById(R.id.ET_password_id);
        account_type.add("please select account type");
        account_type.add("Student");
        account_type.add("HOD");
        account_type.add("Faculty");
        loginB = (TextView) findViewById(R.id.already_registered_TV);

        registerB = (Button) findViewById(R.id.register_button);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/prototype.ttf");
        registerB.setTypeface(type);

        dropdown = (Spinner) findViewById(R.id.spinner1);
        dropdown.setOnItemSelectedListener(this);

        //Progress dialog to show loading of registration process
        loading_dialog = new ProgressDialog(this);
        loading_dialog.setCancelable(false);
        loading_dialog.setMessage("Registering User");
        loading_dialog.setTitle("Please wait");
        account_categoryV="please select account type";
       final  String registration_error="Unable to register user. User already exists.";

        //Creating the ArrayAdapter instance having the user category list

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, account_type);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        dropdown.setAdapter(aa);

//registering account
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                //saving user data in strings
                user_nameV = username_ETobj.getText().toString();
                email_adressV = email_adressETobj.getText().toString();
                passwordV = password_ETobj.getText().toString();

                //checking if the values are empty
                if (user_nameV.isEmpty() || email_adressV.isEmpty() || passwordV.isEmpty() || account_categoryV.isEmpty() ) {

                    Toast.makeText(registration.this, "Please enter the credentials properly", Toast.LENGTH_SHORT).show();
                    loading_dialog.cancel();
                }
                else if(account_categoryV=="please select account type"){
                    Toast.makeText(registration.this, "please select account type", Toast.LENGTH_SHORT).show();
                    loading_dialog.cancel();
                }

                else {
                    loading_dialog.show();
                    //setting the properties for backend server
                    BackendlessUser user = new BackendlessUser();
                    user.setProperty("email", email_adressV);
                    user.setPassword(passwordV);
                    user.setProperty("name", user_nameV);
                    user.setProperty("category", account_categoryV);
                    Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                        public void handleResponse(BackendlessUser registeredUser) {

                            loading_dialog.cancel();
                            Log.i("registration successful", "" + registeredUser.getEmail());
                            Toast.makeText(registration.this, "registration successful", Toast.LENGTH_SHORT).show();

                            Intent nextact_login=new Intent( getApplicationContext(), login.class);
                            startActivity(nextact_login);
                            overridePendingTransition(R.anim.right_left, R.anim.left_right);
                            Toast.makeText(registration.this, "Login in using the entered credentials", Toast.LENGTH_SHORT).show();


                            // user has been registered and now can login
                        }

                        public void handleFault(BackendlessFault fault) {
                            loading_dialog.cancel();

                            if(fault.getCode().equals("3033"))
                            {
                                Toast.makeText(registration.this, "user already exists", Toast.LENGTH_SHORT).show();
                            }
                           Toast.makeText(registration.this, "registration failed", Toast.LENGTH_SHORT).show();
                            Log.i("registration failed", "" + fault.getMessage());
                            // an error has occurred, the error code can be retrieved with fault.getCode()
                        }
                    });
                }
            }
        });

//to go to login activity
        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextact = new Intent(getApplicationContext(), login.class);
                startActivity(nextact);
                //right to left animation
                overridePendingTransition(R.anim.right_left, R.anim.left_right);

            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (flag_dropdown==0) {
            account_type.remove("please select account type");
            account_type.add(0,"");
            flag_dropdown=1;

        } else
            account_categoryV = dropdown.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

