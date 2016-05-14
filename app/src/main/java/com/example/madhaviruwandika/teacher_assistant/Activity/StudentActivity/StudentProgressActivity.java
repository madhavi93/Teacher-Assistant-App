package com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.R;

public class StudentProgressActivity extends BaseActivity {

    Button SeePerformance;
    Button AddPerformance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_student_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        OnSeePerfomanceButtonClickListner();
        OnaddPerfomanceButtonClickListner();

    }

    public void OnSeePerfomanceButtonClickListner(){

        SeePerformance = (Button) findViewById(R.id.btnSeePerfomance);

        SeePerformance.setOnClickListener(
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

}
