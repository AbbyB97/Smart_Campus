package com.example.smart_campus.smartcampus.faculty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.smart_campus.smartcampus.API.Notice;
import com.example.smart_campus.smartcampus.R;
import com.example.smart_campus.smartcampus.hod.hod_notice_list;

public class faculty_notice_upl extends AppCompatActivity {

    EditText title,content;
    Button uplbtn,gotonoticelist;
    BackendlessUser user;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_notice_upl);
        user = Backendless.UserService.CurrentUser();
        if( user != null )
        {
            name = (String) user.getProperty("name");
            Log.i("User ","got "+name);
//            Toast.makeText(hod_notice_upl.this, String.format("phone number: %s", phoneNumber ), Toast.LENGTH_SHORT ).show();
        }
        else
        {
//            Toast.makeText(faculty_notice_upl.this, "User hasn't been logged", Toast.LENGTH_SHORT ).show();
        }
        title=(EditText)findViewById(R.id.titleEDTX);
        content=(EditText)findViewById(R.id.contEDTX);
        uplbtn=(Button)findViewById(R.id.hodnotice_uplBTN);
        gotonoticelist=(Button)findViewById(R.id.hodnotice_uplBTN_toList);
        uplbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String titileV=title.getText().toString();
                final String contentV=content.getText().toString();
                Notice not=new Notice();
                not.setTitle(titileV);
                not.setContent(contentV);
                String nm="Ankit";
                not.setFrom(nm);
                not.saveAsync(new AsyncCallback<Notice>() {
                                  @Override
                                  public void handleResponse(Notice notice) {
                                      Toast.makeText(faculty_notice_upl.this, "Notice Uploaded", Toast.LENGTH_SHORT).show();

                                  }
                                  @Override public void handleFault(BackendlessFault backendlessFault) {

                                  }
                              }
                );
            }
        });
        gotonoticelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(faculty_notice_upl.this,hod_notice_list.class);
                startActivity(i);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }
}
