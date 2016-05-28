package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class SeePerfomanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button SeePerformance;
    Spinner spinnerClass;
    Spinner spinnerStudent;

    int studentClssIDPos;
    int StudentID;
    String name;
    List<String[][]> studentList;

    StudentController stcr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_see_perfomance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        OnPerfomanceButtonClickListner();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        // initialize class controller
        stcr = new StudentController(this);

        List<String> categories = stcr.getClassListForSpinner();
        spinnerClass = (Spinner) findViewById(R.id.spinnerClass);
        // Spinner click listener
        spinnerClass.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerClass.setAdapter(dataAdapter);

    }


    public void OnPerfomanceButtonClickListner(){

        SeePerformance = (Button) findViewById(R.id.buttonSeeP);
        SeePerformance.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity.PerformanceReportActivity");
                        // bind values with new activity
                        Bundle bundle = new Bundle();
                        intent.putExtra("ClassID",studentClssIDPos);
                        intent.putExtra("StudentID",StudentID);
                        intent.putExtra("name",name);
                        startActivity(intent);
                    }
                }
        );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), StudentProgressActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner)parent;


        if(spinner.getId() == R.id.spinnerClass) {
            studentClssIDPos = stcr.getClassIDBySpinnerItemSelected(position);
            // load student list when class is selected from spinner
            studentList = stcr.getStudentListByClassID(studentClssIDPos);
            ArrayList<String> students = new ArrayList<>();
            students.add("");

            for (int i=0;i< studentList.size();i++) {
                students.add(studentList.get(i)[0][1]);
            }

            spinnerStudent = (Spinner) findViewById(R.id.spinnerStudent);
            // Spinner click listener
            spinnerStudent.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,students );
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerStudent.setAdapter(dataAdapter);
        }
        else if(spinner.getId() == R.id.spinnerStudent){

            if(position != 0) {
                StudentID = Integer.parseInt(studentList.get(position-1)[0][0]);
                name = studentList.get(position-1)[0][1];
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
