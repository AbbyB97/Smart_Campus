package com.example.smart_campus.smartcampus;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.property.UserProperty;
import com.example.smart_campus.smartcampus.faculty.faculty_main;
import com.example.smart_campus.smartcampus.hod.hod_main;
import com.example.smart_campus.smartcampus.student.student_main;

import java.util.List;

public class login extends AppCompatActivity {
    ProgressDialog loading_dialog;
    Button loginBobj;
    TextView forgot_PW_Tobj;
    EditText username_ETobj, password_ETobj;
    static String username_stV, password_stV;
    public android.app.AlertDialog.Builder ex;


    @Override
    public void onBackPressed() {
ex.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loginBobj = (Button) findViewById(R.id.login_Button);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/prototype.ttf");
        loginBobj.setTypeface(type);

        username_ETobj = (EditText) findViewById(R.id.login_username_ET);
        password_ETobj = (EditText) findViewById(R.id.login_passwordET);
        forgot_PW_Tobj = (TextView) findViewById(R.id.forgot_password_T);

        //Progress dialog to show loading of log in process

        loading_dialog = new ProgressDialog(this);
        loading_dialog.setCancelable(false);
        loading_dialog.setMessage("logging in");
        loading_dialog.setTitle("Please wait");

//Shared preferences for maintaining user login
       final SharedPreferences.Editor shared_editor = getSharedPreferences("Login_status", MODE_PRIVATE).edit();




//Loggin in with registered account
        loginBobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username_stV = username_ETobj.getText().toString();
                password_stV = password_ETobj.getText().toString();
                //checking if the values are empty
                if (username_stV.isEmpty() || password_stV.isEmpty()) {

                    Toast.makeText(login.this, "Please the credentials properly", Toast.LENGTH_SHORT).show();
                } else {
                    loading_dialog.show();
                    Backendless.UserService.login(username_stV, password_stV, new AsyncCallback<BackendlessUser>() {

                        public void handleResponse(BackendlessUser user) {
                            loading_dialog.cancel();


                          //retrieving user account type

                            //HOD account login
                            registration.account_categoryV= (String) user.getProperty("category");

                            if(registration.account_categoryV.equals("HOD"))
                            {
                                shared_editor.putString("account_type",registration.account_categoryV);
                                shared_editor.putBoolean("firstlogin",false);
                                shared_editor.apply();
                                Intent nextact_hodM=new Intent( getApplicationContext(),hod_main.class);
                                startActivity(nextact_hodM);
                                overridePendingTransition(R.anim.fadin, R.anim.fadout);

                            }



                            //Faculty account login
                            if(registration.account_categoryV.equals("Faculty"))
                            {
                                shared_editor.putString("account_type",registration.account_categoryV);
                                shared_editor.putBoolean("firstlogin",false);
                                shared_editor.apply();
                                Intent nextact_faculty_M=new Intent( getApplicationContext(),faculty_main.class);
                                startActivity(nextact_faculty_M);
                                overridePendingTransition(R.anim.fadin, R.anim.fadout);
                            }


                            //Student account login
                            if(registration.account_categoryV.equals("Student"))
                            {
                                shared_editor.putString("account_type",registration.account_categoryV);
                                shared_editor.putBoolean("firstlogin",false);
                                shared_editor.apply();
                                Intent nextact_student_M=new Intent( getApplicationContext(),student_main.class);
                                startActivity(nextact_student_M);
                                overridePendingTransition(R.anim.fadin, R.anim.fadout);

                            }

                            Toast.makeText(login.this, "Logged in as " + user.getProperty("name")+" with Account Type:"+registration.account_categoryV, Toast.LENGTH_LONG).show();
                            Log.i("log in successful", "" + user.getProperty("name"));
                            // user has been logged in


                        }

                        public void handleFault(BackendlessFault fault) {
                            loading_dialog.cancel();
                            Toast.makeText(login.this, "log in failed", Toast.LENGTH_SHORT).show();
                            Log.i("log in successful", "" + fault.getMessage());
                            // login failed, to get the error code call fault.getCode()
                        }
                    });
                }
            }
        }




        );

        //to go to forgot password
        forgot_PW_Tobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextact = new Intent(getApplicationContext(), forgot_password.class);
                startActivity(nextact);
                //fading activity animation
                overridePendingTransition(R.anim.fadin, R.anim.fadout);
            }
        });

        ex=new android.app.AlertDialog.Builder(this).setTitle("Exit ?").setMessage("Do You Want To Exit").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


    }

    public void register_act(View v){
        Intent reg_act= new Intent( this,registration.class);
        startActivity(reg_act);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }


}
