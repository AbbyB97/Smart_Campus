package com.example.smart_campus.smartcampus.faculty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.smart_campus.smartcampus.API.Notice;
import com.example.smart_campus.smartcampus.API.ProgressWheel;
import com.example.smart_campus.smartcampus.R;
import com.example.smart_campus.smartcampus.hod.hod_notice_dash;

import java.util.ArrayList;
import java.util.List;

public class faculty_notice_list extends AppCompatActivity {

    ListView noticels;
    ArrayAdapter<String> noticeadapt;
    ArrayList<String> noticearry=new ArrayList<>();
    ArrayList<String> object_id=new ArrayList<>();
    TextView rowitem;
    ProgressWheel pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faculty_notice_list);
        pw=(ProgressWheel)findViewById(R.id.progress_wheel);

        rowitem=(TextView)findViewById(R.id.rowItem);
        noticels=(ListView)findViewById(R.id.noticeLIST);
        Backendless.Persistence.of( Notice.class ).find(new AsyncCallback<BackendlessCollection<Notice>>() {
            @Override
            public void handleResponse( BackendlessCollection<Notice> response )
            {
                List<Notice> not=response.getCurrentPage();
                for(Notice a:not){
                    noticeadapt.add(a.getTitle());
                    noticeadapt.notifyDataSetChanged();
                    pw.stopSpinning();
                    object_id.add(a.getObjectId());
                }
                // a Contact instance has been found by ObjectId
            }
            @Override
            public void handleFault( BackendlessFault fault ) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        } );
        noticeadapt=new ArrayAdapter<String>(this, R.layout.lecture_list_item, R.id.rowItem,noticearry);
        noticeadapt.setNotifyOnChange(true);
        noticels.setAdapter(noticeadapt);
        noticels.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String temp=parent.getItemAtPosition(position).toString();
                Toast.makeText(faculty_notice_list.this, "Clicked : "+temp, Toast.LENGTH_SHORT).show();
                int indexObid=(int)parent.getItemIdAtPosition(position);
                String onidstr= object_id.get(indexObid);
                Intent intent = new Intent(getApplicationContext(),faculty_notice_dash.class);
                intent.putExtra("objkey",onidstr);
                startActivity(intent);
                Log.i("Item Index :--","Index :--"+indexObid);
                Log.i("Value @ Item Index :--","Value :--"+onidstr);
            }
        });
    }
}
