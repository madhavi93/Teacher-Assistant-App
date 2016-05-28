package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SeeOverallClassPerfomanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Button SeePerformance;
    Spinner spinnerClass;
    Spinner spinnerExam;
    BarChart chart;
    int studentClssIDPos;
    int ExamID;
    List<TutionClass> classList;
    List<Map<String,String>> ExamList;


    StudentController studentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_overall_class_perfomance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chart = (BarChart)findViewById(R.id.chart);
        chart.setNoDataText("Select class and Exam. \n \n CLICK HERE TO SHOW DATA");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

       // initialize Student controller
        studentController = new StudentController(this);


        List<String> categories = studentController.getClassListForSpinner();


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
            studentClssIDPos = studentController.getClassIDBySpinnerItemSelected(position);

            ClassController cldc = new ClassController(this);
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
        else if(spinner.getId() == R.id.spinnerInclass){

            if(position != 0) {

                try {
                    Thread.sleep(5000);                 //2000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }



                ExamID = Integer.parseInt(ExamList.get(position-1).get("ExamID"));
                List<Integer> chartData = studentController.getDataForGraphOverallPerfomanceOfClass(ExamID);
                ArrayList<BarEntry> entries = new ArrayList<>();

                for (int i=0;i<chartData.size(); i++) {
                    entries.add(new BarEntry( chartData.get(i), i));

                }



                BarDataSet dataset = new BarDataSet(entries, "NO. of student");
                ArrayList<String> labels = new ArrayList<String>();
                labels.add("0-10");
                labels.add("10-20");
                labels.add("20-30");
                labels.add("30-40");
                labels.add("40-50");
                labels.add("50-60");
                labels.add("60-70");
                labels.add("70-80");
                labels.add("80-90");
                labels.add("90-100");

                BarData data = new BarData(labels, dataset);
                chart.setData(data);
                chart.setDescription("MARK RANGE");
                dataset.setColors(ColorTemplate.COLORFUL_COLORS);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
