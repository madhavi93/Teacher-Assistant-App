package com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.R;

public class SeePerfomanceActivity extends BaseActivity {

    Button SeePerformance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_see_perfomance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        OnaddPerfomanceButtonClickListner();





    }


    public void OnaddPerfomanceButtonClickListner(){

        SeePerformance = (Button) findViewById(R.id.buttonSeeP);

        SeePerformance.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity.PerformanceReportActivity");
                        startActivity(intent);
                    }
                }

        );

    }

}
