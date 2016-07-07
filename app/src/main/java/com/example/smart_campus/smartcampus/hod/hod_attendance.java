package com.example.smart_campus.smartcampus.hod;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.smart_campus.smartcampus.API.Attendance;
import com.example.smart_campus.smartcampus.API.ProgressWheel;
import com.example.smart_campus.smartcampus.R;

import java.util.ArrayList;
import java.util.List;

public class hod_attendance extends AppCompatActivity {
    ListView student_list;
    ArrayAdapter<String> stud_adapt;
    ProgressWheel pdw;
    ArrayList<String> stu_list=new ArrayList<>();
    ArrayList<String> stud_templist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_attendance);
        student_list=(ListView)findViewById(R.id.studLIST);
        pdw=(ProgressWheel)findViewById(R.id.progress_wheel);
        stud_adapt=new ArrayAdapter<String>(this, R.layout.lecture_list_item, R.id.rowItem,stu_list);
        student_list.setAdapter(stud_adapt);
        //get current user and compare it with student list
        // if student match then get student id from table row and add row data to the list adn display it with listview
        Backendless.Persistence.of(Attendance.class).find(new AsyncCallback<BackendlessCollection<Attendance>>() {
            @Override
            public void handleResponse(BackendlessCollection<Attendance> attendanceBackendlessCollection) {
                pdw.stopSpinning();
                List<Attendance> a=attendanceBackendlessCollection.getCurrentPage();
                for(Attendance a1:a){
                    stud_templist.add(a1.getObjectId());
                    stu_list.add(a1.getStudent());
                    stud_adapt.notifyDataSetChanged();
                }
            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {

            }
        });
        student_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int stud_id= (int) parent.getSelectedItemId();
                final String stud_idval=stud_templist.get(stud_id);
                Toast.makeText(hod_attendance.this, ""+stud_idval, Toast.LENGTH_SHORT).show();
                //pass object row id to detail page and fetch all the data in one list
                Intent n=new Intent(hod_attendance.this,hod_attendance_detail.class);
                n.putExtra("student_objectID",stud_idval);
                startActivity(n);
            }
        });
    }
}
