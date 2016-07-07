package com.example.smart_campus.smartcampus.hod;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.example.smart_campus.smartcampus.API.ProgressWheel;
import com.example.smart_campus.smartcampus.API.Submissions;
import com.example.smart_campus.smartcampus.R;

import java.util.ArrayList;
import java.util.List;

public class hod_submission extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String selctedsub, selcedexp, selcedexpid;
    int flag;
    ListView subexplst;
    ProgressWheel pdw;
    Spinner sub_spinner;
    String selected_sub;
    ArrayList<String> explst = new ArrayList<String>();
    ArrayList<String> obid = new ArrayList<String>();
    ArrayList<String> sub_ary = new ArrayList<String>();
    ArrayAdapter<String> subexpary,sub_adpt;
    QueryOptions qry;
    BackendlessDataQuery bqry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hod_submission);
        pdw=(ProgressWheel)findViewById(R.id.progress_wheel);
        sub_ary.add("Java");
        sub_ary.add("Management");
        sub_ary.add("Embedded");
        sub_ary.add("Dbms");

        sub_spinner=(Spinner)findViewById(R.id.sub_spiner);
        sub_adpt=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,sub_ary);
        sub_adpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub_spinner.setAdapter(sub_adpt);
        sub_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_sub=parent.getSelectedItem().toString();
                sub_spinner.setEnabled(false);
                pdw.spin();
                updatelist();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        subexplst = (ListView) findViewById(R.id.dls);
        subexpary = new ArrayAdapter<String>(getApplicationContext(), R.layout.lecture_list_item, R.id.rowItem, explst);
//        subexpary.setNotifyOnChange(true);
        subexplst.setAdapter(subexpary);
        subexplst.setOnItemClickListener(this);
//        showChangeLangDialog("Exp 1 AJP");

    }

    public void showChangeLangDialog(final String expname, final String objectid) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.itemclickeddialog, null);
        dialogBuilder.setView(dialogView);
        final TextView expnm = (TextView) dialogView.findViewById(R.id.expname);
        expnm.setText(expname);
        final CheckBox per = (CheckBox) dialogView.findViewById(R.id.performed);
        /*per.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if ((isChecked) && (flag == 1)) {
                    setExpPerformed(expname,objectid);
                    Toast.makeText(getApplicationContext(), "Performed " + expname, Toast.LENGTH_SHORT).show();
                }
                if ((isChecked) && (flag == 0)) {
                    Log.i("Exp with End", "P & C So No Changes ");
                }
                if ((!isChecked) && (flag == 1)) {
                    setExpPerformedOut(expname,objectid);
                    Toast.makeText(getApplicationContext(), "Unperformed " + expname, Toast.LENGTH_SHORT).show();
                }
                if ((isChecked) && (flag == 2)) {
                    setExpPerformed(expname, objectid);
                    Toast.makeText(getApplicationContext(), "Performed " + expname, Toast.LENGTH_SHORT).show();
                }
            }
        });*/
        final CheckBox checki = (CheckBox) dialogView.findViewById(R.id.checked);
        checki.setClickable(false);
        checki.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setExpCheked(expname,objectid);
                }
                if (!isChecked) {
                    setExpChekedOut(expname,objectid);
                }
                Toast.makeText(getApplicationContext(), "Checked " + expname, Toast.LENGTH_SHORT).show();
            }
        });
        if (expname.endsWith("P")) {
            flag = 1;
            per.setChecked(true);
            per.setClickable(false);
            checki.setClickable(true);
            Log.i("Exp with End", "P");
        } else {
            flag = 2;
        }
        if (expname.endsWith("C")) {
            int a, b, c;
            if (expname.endsWith("P C")) {
                flag = 0;
                per.setChecked(true);
                checki.setChecked(true);
                per.setClickable(false);
                checki.setClickable(false);
                Log.i("Exp with End", "P C--");
            } else {
                checki.setChecked(true);
                Log.i("Exp with End", "C");
            }
        } else {
            flag = 2;
        }
        per.setVisibility(View.GONE);
        checki.setVisibility(View.GONE);
        dialogBuilder.setTitle("Experiment Status");
        dialogBuilder.setMessage("Mark Status For");

        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //refresh list with status update
//                pdw.spin();
//                updatelist();
//                Toast.makeText(getApplicationContext(), "Status Updated", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    public void setExpPerformed(final String val,String objectid) {
        if (val.endsWith("P")) {
            Log.i("Exp Stat That:", "Exp Has Status");
        } else {
            final StringBuilder valpl = new StringBuilder(val);
            valpl.append(" P");
            //add code for getting exact object and update it
            Backendless.Persistence.of( Submissions.class ).findById( objectid, new AsyncCallback<Submissions>() {
                @Override
                public void handleResponse( Submissions response )
                {
                    switch (selected_sub){
                        case "Java":
                            response.setJava(valpl.toString());
                            break;
                        case "Management":
                            response.setManagement(valpl.toString());
                            break;
                        case "Embedded":
                            response.setEmbedded(valpl.toString());
                            break;
                        case  "Dbms":
                            response.setDbms(valpl.toString());
                            break;
                    }
                    response.saveAsync(new AsyncCallback<Submissions>() {
                        @Override
                        public void handleResponse(Submissions notice) {
                            Log.i("Exp Stat 2:", " response Updated P");
                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {

                        }
                    });
                    // a Contact instance has been found by ObjectId
                }
                @Override
                public void handleFault( BackendlessFault fault )
                {
                    Log.i("Exp Stat 2:", "Update P failed");
                    // an error has occurred, the error code can be retrieved with fault.getCode()
                }
            } );
            Log.i("Exp Stat 2:", "Updated P function done");
        }
    }

    public void setExpCheked(final String val,String objectid) {
        if (val.endsWith("C")) {
            Log.i("Exp Stat That:", "Exp Has Status");
        } else {
            final StringBuilder valpl = new StringBuilder(val);
            valpl.append(" C");
            //add code for getting exact object and update it
            Backendless.Persistence.of( Submissions.class ).findById( objectid, new AsyncCallback<Submissions>() {
                @Override
                public void handleResponse( Submissions response )
                {
                    switch (selected_sub){
                        case "Java":
                            response.setJava(valpl.toString());
                            break;
                        case "Management":
                            response.setManagement(valpl.toString());
                            break;
                        case "Embedded":
                            response.setEmbedded(valpl.toString());
                            break;
                        case  "Dbms":
                            response.setDbms(valpl.toString());
                            break;
                    }
                    response.saveAsync(new AsyncCallback<Submissions>() {
                        @Override
                        public void handleResponse(Submissions notice) {
                            Log.i("Exp Stat 2:", "response Updated C");
                        }

                        @Override
                        public void handleFault(BackendlessFault backendlessFault) {

                        }
                    });
                    // a Contact instance has been found by ObjectId
                }
                @Override
                public void handleFault( BackendlessFault fault )
                {
                    Log.i("Exp Stat 2:", "Update C failed");
                    // an error has occurred, the error code can be retrieved with fault.getCode()
                }
            } );
            Log.i("Exp Stat 2:", "Updated C function done");
        }
    }

    public void setExpPerformedOut(final String val, String indexid) {
        final String finalval = val.substring(0, val.length() - 2);
        //add code for getting exact object and update it
        Log.i("Exp Stat 2:", "Updated P Out");
    }

    public void setExpChekedOut(final String val, String indexid) {
        final String finalval = val.substring(0, val.length() - 2);
        //add code for getting exact object and update it
        Log.i("Exp Stat 2:", "Updated C Out");
    }

    public void updatelist() {
        subexpary.clear();
        if (subexpary.isEmpty()) {
            qry=new QueryOptions();
            qry.addSortByOption("sortit");
            bqry=new BackendlessDataQuery(qry);
            Backendless.Persistence.of( Submissions.class).find(bqry,new AsyncCallback<BackendlessCollection<Submissions>>(){
                @Override
                public void handleResponse( BackendlessCollection<Submissions> foundContacts )
                {
                    List<Submissions> a=foundContacts.getCurrentPage();
                    for(Submissions n:a){
                        switch (selected_sub) {
                            case "Java":
                                subexpary.add(n.getJava());
                                subexpary.notifyDataSetChanged();
                                break;
                            case "Management":
                                subexpary.add(n.getManagement());
                                subexpary.notifyDataSetChanged();
                                break;
                            case "Embedded":
                                subexpary.add(n.getEmbedded());
                                subexpary.notifyDataSetChanged();
                                break;
                            case "Dbms":
                                subexpary.add(n.getDbms());
                                subexpary.notifyDataSetChanged();
                                break;
                        }
                        pdw.stopSpinning();
                        obid.add(n.getObjectId());
                    }
                    sub_spinner.setEnabled(true);
                    // all Contact instances have been found
                }
                @Override
                public void handleFault( BackendlessFault fault )
                {
                    // an error has occurred, the error code can be retrieved with fault.getCode()
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String tempexp = parent.getItemAtPosition(position).toString();
        int indexid =(int)parent.getItemIdAtPosition(position);
        String objectid=obid.get(indexid);
        showChangeLangDialog(tempexp,objectid);
//        Toast.makeText(this, "id : "+objectid, Toast.LENGTH_SHORT).show();
    }
}
