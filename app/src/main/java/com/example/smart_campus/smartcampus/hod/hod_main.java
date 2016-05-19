package com.example.smart_campus.smartcampus.hod;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.smart_campus.smartcampus.R;
import com.example.smart_campus.smartcampus.login;
import com.example.smart_campus.smartcampus.student.student_schedule;

public class hod_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }
    public void Attendance(View v){
        Toast.makeText(this, "Attendance is clicked", Toast.LENGTH_SHORT).show();}
    public void Manual(View v){Toast.makeText(this, "Manual is clicked", Toast.LENGTH_SHORT).show();}
    public void Schedule(View v){
        Intent i=new Intent(this,hod_schedule.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        Toast.makeText(this, "Schedule is clicked", Toast.LENGTH_SHORT).show();
    }
    public void Notice(View v){
        Intent i=new Intent(this,hod_notice_upl.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
        Toast.makeText(this, "Notice is clicked", Toast.LENGTH_SHORT).show();}
    public void Teacher(View v){Toast.makeText(this, "Teacher is clicked", Toast.LENGTH_SHORT).show();}
    public void H_O_D(View v){Toast.makeText(this, "H.O.D is clicked", Toast.LENGTH_SHORT).show();}
    @Override
    public void onBackPressed() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("Warning");
        builder.setMessage("Do you really want to logout")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // clearing sharedpreferences' data after user clicks logout
                        SharedPreferences.Editor shared_editor = getSharedPreferences("Login_status", MODE_PRIVATE).edit();
                        shared_editor.clear();
                        shared_editor.putString("account_type",null);
                        shared_editor.putBoolean("firstlogin",true);
                        shared_editor.apply();
                        /*Intent nextact = new Intent(getApplicationContext(), login.class);
                        startActivity(nextact);*/
                        hod_main.this.finish();
                        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        android.app.AlertDialog alert = builder.create();
        alert.show();
    }

}
