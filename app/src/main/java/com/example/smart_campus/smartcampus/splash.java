package com.example.smart_campus.smartcampus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.smart_campus.smartcampus.faculty.faculty_main;
import com.example.smart_campus.smartcampus.hod.hod_main;
import com.example.smart_campus.smartcampus.student.student_main;


public class splash extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        tv=(TextView)findViewById(R.id.splashtxt);
        tv.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/prototype.ttf"));
        final SharedPreferences shared_editor = this.getSharedPreferences("Login_status", MODE_PRIVATE);

        //creating thread for splash screen
        Thread startTimer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                    String type=shared_editor.getString("account_type", null);
                    boolean firstlogin=shared_editor.getBoolean("firstlogin",true);
                    if(type==null && firstlogin)
                    {
                        Intent gotologin=new Intent(getApplicationContext(),login.class);
                        startActivity(gotologin);
                        overridePendingTransition(R.anim.fadin, R.anim.fadout);
                        finish();
                    }

                    if(type!=null){

                        if(type.equals("HOD"))
                        {
                            Intent gotologin=new Intent(getApplicationContext(),hod_main.class);
                            startActivity(gotologin);
                            overridePendingTransition(R.anim.fadin, R.anim.fadout);
                            finish();

                        }
                        if(type.equals("Faculty"))
                        {
                            Intent gotologin=new Intent(getApplicationContext(),faculty_main.class);
                            startActivity(gotologin);
                            overridePendingTransition(R.anim.fadin, R.anim.fadout);
                            finish();

                        }
                        if(type.equals("Student"))
                        {
                            Intent gotologin=new Intent(getApplicationContext(),student_main.class);
                            startActivity(gotologin);
                            overridePendingTransition(R.anim.fadin, R.anim.fadout);
                            finish();

                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        startTimer.start();
    }
    }

