package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Adapter.Util.StudentRegisterAdapter;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.CommunicationController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.Model.AppConstant;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemRegisterName;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkAttendenceActivity extends AppCompatActivity {

    private ListView v ;
    private TextView className ;
    private ArrayList<ItemRegisterName> register;
    private StudentRegisterAdapter adapter;
    private Button finishMarking;

    private List<String[][]> studentList ;
    private int classID = 0;
    private ClassController classController;
    private CommunicationController communicationController;
    private Bundle myBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendence);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize home and back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialize widgets in the form
        className = (TextView) findViewById(R.id.textViewClass);
        v = (ListView) findViewById(R.id.register);
        finishMarking = (Button)findViewById(R.id.buttonFinishMarking);

        // initialize conrollers
        classController = new ClassController(this);
        communicationController = new CommunicationController(this);

        // initialize bundle for get passed data from the previous activity
        myBundle = getIntent().getExtras();
        classID = myBundle.getInt("ClassID");

        // get tutino class details
        Map<String,String> today_Class = new HashMap<>();
        if(classID != 0) {
            today_Class = classController.getTutionClassByID(classID);
        }


        className.setText(today_Class.get("ClassName"));
        studentList = classController.getStudentListByClassID(classID);


        register = new ArrayList<>();
        for(int i=0;i<studentList.size();i++){
            register.add(new ItemRegisterName(Integer.parseInt(studentList.get(i)[0][0]),studentList.get(i)[0][1],false));
        }

        adapter = new StudentRegisterAdapter(this,register);
        v.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        v.setAdapter(adapter);
        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (view != null) {

                    CheckBox checkBox = (CheckBox)view.findViewById(R.id.attendence);

                    if (checkBox.isChecked()) {
                        register.get(position).setAttendence(false);
                        checkBox.setChecked(false);
                    }
                    else if (!checkBox.isChecked()) {
                        register.get(position).setAttendence(true);
                        checkBox.setChecked(true);
                    }
                }
            }

        });

        finishAttendenceMarking();
    }

    private void finishAttendenceMarking() {
        finishMarking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(studentList.size() != 0) {
                    if (classController.addAttendence(register, classID) == 1) {
                        Toast.makeText(MarkAttendenceActivity.this, "Attendence are added successfully", Toast.LENGTH_LONG).show();
                        communicationController.sendSMSAfterFinishingAttendence(classID, register);
                    } else {
                        Toast.makeText(MarkAttendenceActivity.this, "Adding attendence failed", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(MarkAttendenceActivity.this, "No student has been registerd yet.", Toast.LENGTH_LONG).show();
                    AppConstant.getInstance().setMarkedAttendence(true);
                }

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), TodaysClassActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}
