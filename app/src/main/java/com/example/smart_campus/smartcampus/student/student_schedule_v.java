package com.example.smart_campus.smartcampus.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.smart_campus.smartcampus.API.schedule;
import com.example.smart_campus.smartcampus.R;

import java.util.ArrayList;
import java.util.List;

public class student_schedule_v extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String retrieveddata, tmp, selected_day = "";
    int flag_dropdown = 0;
    ArrayList<String> ar = new ArrayList<String>();
    ArrayList<String> DDday = new ArrayList<String>();
    Spinner dropdown;
    ArrayAdapter adapter;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule_v);
        lv = (ListView) findViewById(R.id.listView_schedule);
        DDday.add("please select the day");
        DDday.add("Monday");
        DDday.add("Tuesday");
        DDday.add("Wednesday");
        DDday.add("Thursday");
        DDday.add("Friday");
        DDday.add("Saturday");
        adapter= new ArrayAdapter<String>(this, R.layout.listxml, ar);
        lv.setAdapter(adapter);
        dropdown = (Spinner) findViewById(R.id.spinnerday);
        dropdown.setOnItemSelectedListener(this);
        //creating adapter for list

        //Creating the ArrayAdapter instance having the day list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, DDday);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        dropdown.setAdapter(aa);

/*        Backendless.Persistence.of(schedule.class).find(new AsyncCallback<BackendlessCollection<schedule>>() {
            @Override
            public void handleResponse(BackendlessCollection<schedule> data) {
                // all Contact instances have been found
                List<schedule> firstPage = data.getCurrentPage();
                for (int i = 0; i < firstPage.size(); i++) {
                    tmp = firstPage.get(i).getMonday();
                    ar.add(tmp);
                    setOrd(tmp);
                    System.out.println("name - " + firstPage.get(i).getMonday());
                }

                Toast.makeText(student_schedule_v.this, "data obtained", Toast.LENGTH_SHORT).show();
                Log.i("retrieved successfully", "yaaehh");
                listset();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.i("data retrieval failed", "" + fault.getMessage());
            }
        });*/


    }


    public void setOrd(String tmp) {
        retrieveddata = retrieveddata + " " + " " + tmp;
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (flag_dropdown == 0) {
            DDday.remove("please select the day");
            DDday.add(0, "");
            flag_dropdown = 1;

        } else {

            selected_day = dropdown.getSelectedItem().toString();
        }

        if (selected_day.equals("")) {
            Toast.makeText(student_schedule_v.this, "please select the day", Toast.LENGTH_SHORT).show();

        } else {



                Backendless.Persistence.of(schedule.class).find(new AsyncCallback<BackendlessCollection<schedule>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<schedule> data) {
                        // all Contact instances have been found
                        List<schedule> firstPage = data.getCurrentPage();


                        if (selected_day.equals("Monday"))
                        {
                            for (int i = 0; i < firstPage.size(); i++)
                            {
                                tmp = firstPage.get(i).getMonday();
                                ar.add(tmp);
                                setOrd(tmp);

                                
                                System.out.println("day - " + firstPage.get(i).getMonday());
                                Toast.makeText(student_schedule_v.this, "data obtained", Toast.LENGTH_SHORT).show();
                                Log.i("retrieved successfully", "yaaehh");

                            }
                        }
                        if (selected_day.equals("Tuesday")) {

                            for (int i = 0; i < firstPage.size(); i++) {
                                tmp = firstPage.get(i).getTuesday();
                                ar.add(tmp);
                                setOrd(tmp);
                                System.out.println("day - " + firstPage.get(i).getTuesday());
                            }
                        }

                        if (selected_day.equals("Wednesday")) {

                            for (int i = 0; i < firstPage.size(); i++) {
                                tmp = firstPage.get(i).getWednesday();
                                ar.add(tmp);
                                setOrd(tmp);
                                System.out.println("day - " + firstPage.get(i).getWednesday());
                            }
                        }




                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                        Log.i("data retrieval failed", "" + fault.getMessage());
                    }
                });




        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
