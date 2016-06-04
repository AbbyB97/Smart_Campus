package com.example.smart_campus.smartcampus.hod;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.example.smart_campus.smartcampus.API.ProgressWheel;
import com.example.smart_campus.smartcampus.API.schedule;
import com.example.smart_campus.smartcampus.R;

import java.util.ArrayList;
import java.util.List;

public class hod_schedule extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView edp;
    ListView lecturels;
    Spinner daysls;
    ProgressWheel pwd;
    ArrayList<String> lecturelsarry=new ArrayList<String>();
    ArrayList<String> daylsarry=new ArrayList<String>();
    ArrayAdapter<String> lectureadap,dayadapt;
    String f,sb = "",selectedperiod,dayselected;
    ArrayList<String> datas=new ArrayList<String>();
    QueryOptions qry;
    BackendlessDataQuery bqry;
    public static int data_changed=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hod_schedule);

        pwd=(ProgressWheel)findViewById(R.id.progress_wheel);
        edp=(TextView)findViewById(R.id.rowItem);
        daysls=(Spinner)findViewById(R.id.subject_Spinner);
        daylsarry.add("Monday");
        daylsarry.add("Tuesday");
        daylsarry.add("Wednesday");
        daylsarry.add("Thursday");
        daylsarry.add("Friday");
        daylsarry.add("Saturday");
        dayadapt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,daylsarry);
        dayadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daysls.setAdapter(dayadapt);
        daysls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pwd.spin();
                dayselected=parent.getSelectedItem().toString();
                daysls.setEnabled(false);
                lectureadap.clear();
                if(lectureadap.isEmpty()) {

                    qry=new QueryOptions();
                    qry.addSortByOption("SEQUENCE");
                    bqry=new BackendlessDataQuery(qry);
                    Backendless.Persistence.of(schedule.class).find( bqry,new AsyncCallback<BackendlessCollection<schedule>>(){
                        @Override
                        public void handleResponse( BackendlessCollection<schedule> bansode)
                        {
                            List<schedule> a= bansode.getCurrentPage();
                            for (int i=0;i<a.size();i++){
                                switch (dayselected) {
                                    case "Monday": {
                                        String tp = a.get(i).getMonday();
                                        lectureadap.add(tp);
                                        lectureadap.notifyDataSetChanged();
                                        pwd.stopSpinning();
                                        break;
                                    }
                                    case "Tuesday": {
                                        String tp = a.get(i).getTuesday();
                                        lectureadap.add(tp);
                                        lectureadap.notifyDataSetChanged();
                                        pwd.stopSpinning();
                                        break;
                                    }
                                    case "Wednesday": {
                                        String tp = a.get(i).getWednesday();
                                        lectureadap.add(tp);
                                        lectureadap.notifyDataSetChanged();
                                        pwd.stopSpinning();
                                        break;
                                    }
                                    case "Thursday": {
                                        String tp = a.get(i).getThursday();
                                        lectureadap.add(tp);
                                        lectureadap.notifyDataSetChanged();
                                        pwd.stopSpinning();
                                        break;
                                    }
                                    case "Friday": {
                                        String tp = a.get(i).getFriday();
                                        lectureadap.add(tp);
                                        lectureadap.notifyDataSetChanged();
                                        pwd.stopSpinning();
                                        break;
                                    }
                                    case "Saturday": {
                                        String tp = a.get(i).getSaturday();
                                        lectureadap.add(tp);
                                        lectureadap.notifyDataSetChanged();
                                        pwd.stopSpinning();
                                        break;
                                    }
                                }
                                String tp1=a.get(i).getTime();
                                datas.add(tp1);
                            }
                            System.out.println("data : "+f);
                            Log.i("DATA RETRIVAL :","Data get is --"+a.size());
                            daysls.setEnabled(true);
                        }
                        @Override
                        public void handleFault( BackendlessFault fault )
                        {
                            Log.i("data retrival",""+fault.getMessage());
                            // an error has occurred, the error code can be retrieved with fault.getCode()
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lecturels =(ListView)findViewById(R.id.daylistid);
        lectureadap =new ArrayAdapter<String>(this, R.layout.lecture_list_item, R.id.rowItem,lecturelsarry);
        lectureadap.setNotifyOnChange(true);
        lecturels.setAdapter(lectureadap);
        lecturels.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedperiod=parent.getItemAtPosition(position).toString();

        int indextime=(int)parent.getItemIdAtPosition(position);
        String time=datas.get(indextime);
        Log.i("Time got :-",""+time);
        Log.i("Value selected :",""+String.valueOf(indextime));
        AlertDialog.Builder data=new AlertDialog.Builder(this);
        data.setTitle("Lecture Selected").setMessage("\t\t\tPeriod :\t\t\t\t"+selectedperiod+"\n\t\t\tTime :\t\t\t\t"+time).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog adb=data.create();
        adb.setCancelable(false);
        adb.show();
    }
}
