package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity;

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

public class MyProgressActivity extends BaseActivity {

    Button AddMyWork;
    Button btnSyl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_my_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        OnAddMyWorkButtonClickListner();
        OnAddSylButtonClickListner();
    }

    public void OnAddSylButtonClickListner(){

        btnSyl = (Button) findViewById(R.id.add_viewSyl);

        btnSyl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Activity.SyllabusTrackerActivity.AddSylActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    public void OnAddMyWorkButtonClickListner(){

        AddMyWork = (Button) findViewById(R.id.AddWork);

        AddMyWork.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.MyWorkActivity");
                        startActivity(intent);
                    }
                }
        );
    }

}
