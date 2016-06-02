package com.example.smart_campus.smartcampus.student;

import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.smart_campus.smartcampus.API.Notice;
import com.example.smart_campus.smartcampus.API.ProgressWheel;
import com.example.smart_campus.smartcampus.R;

public class student_notice_dash extends AppCompatActivity {
    TextView ttl,cont,frm,tv1,tv2,tv3;
    ProgressWheel p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_notice_dash);
        p=(ProgressWheel)findViewById(R.id.progress_wheel);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        tv3=(TextView)findViewById(R.id.textView3);
        if (tv1 != null) {
            tv1.setPaintFlags(tv1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }if (tv2 != null) {
            tv2.setPaintFlags(tv2.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }if (tv3 != null) {
            tv3.setPaintFlags(tv3.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        }
        ttl=(TextView)findViewById(R.id.ttlTV);
        cont=(TextView)findViewById(R.id.contTV);
        cont.setAutoLinkMask(Linkify.ALL);
        frm=(TextView)findViewById(R.id.fromTV);
        Bundle bundle = getIntent().getExtras();
        String tempkey = bundle.getString("objkey");
        Log.i("Intent data :","Value : "+tempkey);
        Backendless.Persistence.of( Notice.class ).findById( tempkey, new AsyncCallback<Notice>() {
            @Override
            public void handleResponse( Notice response )
            {
                final String ttilv=response.getTitle();
                final String contv=response.getContent();
                final String fromv=response.getFrom();
                p.stopSpinning();
                ttl.setText(ttilv);
                cont.setText(contv);
                frm.setText(fromv);
                Log.i("Notice :","Data Displyed");
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {
                // an error has occurred, the error code can be retrieved with fault.getCode()
            }
        } );


    }
}
