package com.example.smart_campus.smartcampus.faculty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.smart_campus.smartcampus.API.Attendance;
import com.example.smart_campus.smartcampus.API.Notice;
import com.example.smart_campus.smartcampus.API.ProgressWheel;
import com.example.smart_campus.smartcampus.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class faculty_attendance extends AppCompatActivity {
    Spinner sub_spinner;
    ListView stud_listview;
    EditText datebox;
    ProgressWheel pdw;
    Button get_date,Done_btn;
    int date,year,month;
    ArrayList<String> sub_array=new ArrayList<String>();
    ArrayList<String> object_id=new ArrayList<String>();
    ArrayList<String> stud_array=new ArrayList<String>();
    ArrayAdapter<String> sub_adpt,stud_adpt;
    String selected_sub;
    private String datepluse="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_attendance);

        pdw=(ProgressWheel)findViewById(R.id.progress_wheel);

        sub_spinner=(Spinner)findViewById(R.id.subject_Spinner);
        sub_array.add("Java");
        sub_array.add("Managment");
        sub_array.add("Embedded");
        sub_array.add("Dbms");
        sub_adpt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sub_array);
        sub_adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub_spinner.setAdapter(sub_adpt);
        sub_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //load list for selected subject
                selected_sub=parent.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        stud_listview=(ListView)findViewById(R.id.attendance_listview);
        stud_adpt=new ArrayAdapter<String>(this, R.layout.stud_list_row, R.id.ctV,stud_array);
        stud_listview.setAdapter(stud_adpt);
        stud_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //on student list item selected
                final String tmp=parent.getItemAtPosition(position).toString();
                final CheckedTextView ctv=(CheckedTextView)view.findViewById(R.id.ctV);
                if(ctv.isChecked()){
                    ctv.setChecked(false);
                    Toast.makeText(faculty_attendance.this, "unchecked "+tmp, Toast.LENGTH_SHORT).show();
                }
                else {
                    ctv.setChecked(true);
                    Toast.makeText(faculty_attendance.this, "checked "+tmp, Toast.LENGTH_SHORT).show();
                }
            }
        });
        Backendless.Persistence.of( Attendance.class ).find(new AsyncCallback<BackendlessCollection<Attendance>>() {
            @Override
            public void handleResponse( BackendlessCollection<Attendance> response )
            {
                List<Attendance> not=response.getCurrentPage();
                for(Attendance a:not){

                    stud_adpt.add(a.getStudent());
                    stud_adpt.notifyDataSetChanged();
                    pdw.stopSpinning();
                    Log.i("DATA ",""+a.getStudent());
                    object_id.add(a.getObjectId());
                }
                // a Contact instance has been found by ObjectId
            }
            @Override
            public void handleFault( BackendlessFault fault ) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        } );

    }
    public void getDate(View v){
        datebox=(EditText)findViewById(R.id.databox);
        Calendar c=Calendar.getInstance();
        date=c.get(Calendar.DAY_OF_MONTH);
        month=c.get(Calendar.MONTH);
        year=c.get(Calendar.YEAR);
        datepluse= String.valueOf(date)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year);
        datebox.setText(datepluse);


    }


    public void upload_attendance(View V){


        if(datepluse.equals(""))
        {

            Toast.makeText(faculty_attendance.this, "Please select date", Toast.LENGTH_SHORT).show();
        }

else    {
            Toast.makeText(faculty_attendance.this, "Class attendance uploaded for" + selected_sub, Toast.LENGTH_SHORT).show();
        }


    }
}
