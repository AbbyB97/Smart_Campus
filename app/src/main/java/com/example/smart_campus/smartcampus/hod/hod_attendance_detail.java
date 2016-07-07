package com.example.smart_campus.smartcampus.hod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.smart_campus.smartcampus.API.Attendance;
import com.example.smart_campus.smartcampus.API.ProgressWheel;
import com.example.smart_campus.smartcampus.R;

import java.util.ArrayList;
import java.util.List;

public class hod_attendance_detail extends AppCompatActivity {
    Spinner lectspin;
    ListView attednc_dtl;
    ProgressWheel pdw;
    ArrayList<String> attednc_dtlary=new ArrayList<String>();
    ArrayList<String> lect_ary=new ArrayList<String>();
    ArrayList<String> object_id=new ArrayList<String>();
    ArrayAdapter<String> lecadapt;
    ArrayAdapter<String> attedncadapt;
    String lect_selected;
    String idval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_attendance_detail);
        lect_ary.add("Java");
        lect_ary.add("Management");
        lect_ary.add("Embedded");
        lect_ary.add("Dbms");

        pdw=(ProgressWheel)findViewById(R.id.progress_wheel);

        savedInstanceState=getIntent().getExtras();
        idval=savedInstanceState.getString("student_objectID");

        attednc_dtl=(ListView)findViewById(R.id.attendance_listview);
        attedncadapt=new ArrayAdapter<String>(this, R.layout.lecture_list_item, R.id.rowItem,attednc_dtlary);
        attednc_dtl.setAdapter(attedncadapt);

        lectspin=(Spinner)findViewById(R.id.subject_Spinner);
        lecadapt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,lect_ary);
        lecadapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lectspin.setAdapter(lecadapt);
        lectspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pdw.spin();
                lect_selected=parent.getSelectedItem().toString();
//                lectspin.setEnabled(false);
//                lecadapt.clear();
                attedncadapt.clear();
                if(attedncadapt.isEmpty()){
                    Backendless.Persistence.of(Attendance.class).findById(idval, new AsyncCallback<Attendance>() {
                        @Override
                        public void handleResponse(Attendance attendance) {
                          switch (lect_selected){
                              case "Java":
                                  processAttendance(attendance.getJava());
                                  pdw.stopSpinning();
                                  break;
                              case "Management":
                                  processAttendance(attendance.getManagment());
                                  pdw.stopSpinning();
                                  break;
                              case "Embedded":
                                  processAttendance(attendance.getEmbedded());
                                  pdw.stopSpinning();
                                  break;
                              case "Dbms":
                                  processAttendance(attendance.getDbms());
                                  pdw.stopSpinning();
                                  break;
                          }
                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        /*Backendless.Persistence.of( Attendance.class ).find(new AsyncCallback<BackendlessCollection<Attendance>>() {
            @Override
            public void handleResponse( BackendlessCollection<Attendance> response )
            {
                List<Attendance> not=response.getCurrentPage();
//                for(Attendance a:not){
//                    attedncadapt.add(a.getJava());
//                    attedncadapt.notifyDataSetChanged();
                pdw.stopSpinning();
                processAttendance(not.get(0).getJava());
//                    object_id.add(a.getObjectId());
            }
            // a Contact instance has been found by ObjectId
//            }
            @Override
            public void handleFault( BackendlessFault fault ) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        } );*/
    }
    public void processAttendance(String data){
        for (String a:data.split("_",Integer.MAX_VALUE)){
            attedncadapt.add(a);
            attedncadapt.notifyDataSetChanged();
        }
    }
}
