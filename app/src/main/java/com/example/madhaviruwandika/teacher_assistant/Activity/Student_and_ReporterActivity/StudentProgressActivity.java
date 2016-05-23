package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.R;

public class StudentProgressActivity extends BaseActivity {

    Button SeePerformanceClass;
    Button SeePerformanceStudent;
    Button AddPerformance;
    Button AddExam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_student_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        OnSeePerfomanceOFClassButtonClickListner();
        OnaddPerfomanceButtonClickListner();
        OnAddExamButtonClickListner();
        OnSeePerfomanceOfStudentButtonClickListner();

    }

    public void OnSeePerfomanceOFClassButtonClickListner(){

        SeePerformanceClass = (Button) findViewById(R.id.btnSeePerfomanceClass);

        SeePerformanceClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity.SeeOverallClassPerfomanceActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnSeePerfomanceOfStudentButtonClickListner(){

        SeePerformanceStudent = (Button) findViewById(R.id.btnSeeStudentPerfomance);

        SeePerformanceStudent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity.SeePerfomanceActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnaddPerfomanceButtonClickListner(){

        AddPerformance = (Button) findViewById(R.id.btnAddPerfomance);

        AddPerformance.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity.AddPerformanceActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnAddExamButtonClickListner(){

        AddExam = (Button) findViewById(R.id.btnAddExam);

        AddExam.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.AddExamRecordsActivity");
                        startActivity(intent);
                    }
                }

        );

    }


}
