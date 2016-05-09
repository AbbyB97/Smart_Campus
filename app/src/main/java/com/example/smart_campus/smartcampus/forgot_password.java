package com.example.smart_campus.smartcampus;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class forgot_password extends AppCompatActivity {
    EditText valid_username_ETobj;
    Button recover_pwBobj;
    String valid_username_stV;
    ProgressDialog loadingdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        valid_username_ETobj = (EditText) findViewById(R.id.valid_usernameET);
        recover_pwBobj = (Button) findViewById(R.id.recovery_button);
        loadingdialog = new ProgressDialog(this);
        loadingdialog.setCancelable(false);
        loadingdialog.setMessage("please wait");
        loadingdialog.setTitle("Recovering Account");


        recover_pwBobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valid_username_stV = valid_username_ETobj.getText().toString();
                if (valid_username_stV.isEmpty()) {

                    Toast.makeText(forgot_password.this, "Please the credentials properly", Toast.LENGTH_SHORT).show();
                } else {
                    loadingdialog.show();
                    Backendless.UserService.restorePassword(valid_username_stV, new AsyncCallback<Void>() {

                        public void handleResponse(Void response) {
                            loadingdialog.cancel();
                            Toast.makeText(forgot_password.this, "recovery link sent to your email", Toast.LENGTH_SHORT).show();
                            Log.i("recovery successful", "");

                            // Backendless has completed the operation - an email has been sent to the user
                        }

                        public void handleFault(BackendlessFault fault) {
                            loadingdialog.cancel();

                            Log.i("recovery failed", "" + fault.getMessage());
                            // password revovery failed, to get the error code call fault.getCode()
                        }
                    });


                }
            }
        });


    }

}
