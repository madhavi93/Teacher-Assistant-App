package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeeOverallClassPerfomanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button SeePerformance;
    Spinner spinnerClass;
    Spinner spinnerExam;

    int studentClssIDPos;
    int ExamID;
    List<TutionClass> classList;
    List<Map<String,String>> ExamList;

    ClassController cldc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_overall_class_perfomance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        cldc = new ClassController(this);   // initialize class controller
        classList= cldc.getClassList();

        List<String> categories = new ArrayList<String>();
        categories.add("");
        //set class list for spinner
        for(int i=0;i<classList.size();i++){
            categories.add(classList.get(i).getClassName());
        }

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
            studentClssIDPos = cldc.getClassIDBySpinnerItemSelected(position);

            ExamList = cldc.getExamListByID(studentClssIDPos);
            ArrayList<String> examlist = new ArrayList<>();
            examlist.add("");

            for (int i=0;i< ExamList.size();i++) {
                examlist.add(ExamList.get(i).get("date")+ExamList.get(i).get("lesson")+ExamList.get(i).get("Etype"));
            }

            spinnerExam = (Spinner) findViewById(R.id.spinnerInclass);
            // Spinner click listener
            spinnerExam.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,examlist );
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerExam.setAdapter(dataAdapter);
        }
        else if(spinner.getId() == R.id.spinnerStudent){

            if(position != 0) {
                ExamID = Integer.parseInt(ExamList.get(position-1).get("ExamID"));
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void OnPerfomanceButtonClickListner(){

        SeePerformance = (Button) findViewById(R.id.buttonSeeP);

        SeePerformance.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity.PerfomanceReportClassActivity");
                        Bundle bundle = new Bundle();
                        intent.putExtra("ClassID",studentClssIDPos);
                        intent.putExtra("ExamID",ExamID);
                        startActivity(intent);
                    }
                }

        );

    }
}
