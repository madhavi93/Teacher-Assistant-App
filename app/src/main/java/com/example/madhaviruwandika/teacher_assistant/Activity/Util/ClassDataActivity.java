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

public class ClassDataActivity extends BaseActivity {

    private Button addStd ;
    private Button addSyl ;
    private Button addClass ;
    private Button addExClass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_class_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.onCreate(savedInstanceState);

        OnAddSylButtonClickListner();
        OnAddStudentButtonClickListner();
        OnAddClassButtonClickListner();
        OnAddEXClassButtonClickListner();

    }

    public void OnAddSylButtonClickListner(){

        addSyl = (Button) findViewById(R.id.btnAddSyl);

        addSyl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Activity.SyllabusTrackerActivity.AddSylActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnAddStudentButtonClickListner(){

        addStd = (Button) findViewById(R.id.btnAddStd);

        addStd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.Student.StudentActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnAddClassButtonClickListner(){

        addClass = (Button) findViewById(R.id.btnAddClass);

        addClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.Util.addClass");
                        startActivity(intent);
                    }
                }

        );

    }


    public void OnAddEXClassButtonClickListner(){

        addExClass = (Button) findViewById(R.id.btnAddEXCls);

        addExClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.Util.add_ExtraClass");
                        startActivity(intent);
                    }
                }

        );

    }




}
