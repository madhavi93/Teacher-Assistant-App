package com.example.madhaviruwandika.teacher_assistant.Activity.Util;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.madhaviruwandika.teacher_assistant.R;

public class SettingsActivity extends BaseActivity{

    Button deleteClass;
    Button deleteStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        OnDeleteClassButtonClickListner();
        OnDeleteStudentButtonClickListner();
    }

    public void OnDeleteClassButtonClickListner(){

        deleteStudent = (Button) findViewById(R.id.buttonDeleteClass);

        deleteStudent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.DeleteClassActivity");
                        startActivity(intent);
                    }
                }

        );

    }


    public void OnDeleteStudentButtonClickListner(){

        deleteClass = (Button) findViewById(R.id.buttonDeleteStudent);

        deleteClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity.StudentActivity");
                        startActivity(intent);
                    }
                }

        );

    }


}
