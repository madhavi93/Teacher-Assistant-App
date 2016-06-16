package com.example.madhaviruwandika.teacher_assistant.Activity.Util;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.madhaviruwandika.teacher_assistant.Activity.HomeActivity;
import com.example.madhaviruwandika.teacher_assistant.R;

public class ClassDataActivity extends BaseActivity {

    private Button addStd ;
    private Button updateClass ;
    private Button addClass ;
    private Button addExClass ;
    private Button updateStudent;
    private Button updateExtraClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_class_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.onCreate(savedInstanceState);

        OnAddStudentButtonClickListner();
        OnAddClassButtonClickListner();
        OnAddEXClassButtonClickListner();
        OnUpdateClassButtonClickListner();
        OnUpdateStudentButtonClickListner();
        OnViewExtraClassButtonClickListner();

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


    public void OnUpdateClassButtonClickListner(){

        updateClass = (Button) findViewById(R.id.btnUpdateClass);

        updateClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.UpdateClassDActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnUpdateStudentButtonClickListner(){

        updateStudent = (Button) findViewById(R.id.btnUpdateStdDetails);

        updateStudent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity.UpdateStudentActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnViewExtraClassButtonClickListner(){

        updateExtraClass = (Button) findViewById(R.id.btnUpdateExtraClass);

        updateExtraClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent( "com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.ViewExtraClassActivity");
                        startActivity(intent);
                    }
                }

        );

    }



}
