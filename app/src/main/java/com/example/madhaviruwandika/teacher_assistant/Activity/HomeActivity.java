package com.example.madhaviruwandika.teacher_assistant.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;

import com.example.madhaviruwandika.teacher_assistant.Database.DBCreator;
import com.example.madhaviruwandika.teacher_assistant.Model.AppConstant;
import com.example.madhaviruwandika.teacher_assistant.Model.PasswardEncryptor;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeActivity extends BaseActivity {

    Bundle bundle;
    int fragmentID = 0;
    Button statrClass;
    Button myProgress;
    Button stdPrefm;
    Button message;
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        //create database
        DBCreator dbCreator= new DBCreator(this);

        statrClass = (Button)findViewById(R.id.btnStart);
        message = (Button)findViewById(R.id.btnmessage);
        stdPrefm = (Button)findViewById(R.id.btnSperfomance);
        myProgress = (Button)findViewById(R.id.btnMyProgress);

        LoadSelection(0);

        OnstartClassClickListner();
        OnMessageClickListner();
        OnMyProgressClickListner();
        OnStdPClickListner();

    }

    private void LoadSelection(int i){
        if(i== 1){
            Intent intent = new Intent(".Activity.classFagments.ClassManegeActivity");
            startActivity(intent);
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }


    public void OnstartClassClickListner(){

        statrClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.TodaysClassActivity");
                        startActivity(intent);
                    }
                }

        );
    }

    public void OnStdPClickListner(){

        stdPrefm.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity.StudentProgressActivity");
                        startActivity(intent);
                    }
                }

        );
    }

    public void OnMessageClickListner(){

        message.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity.SendMessageActivity");
                        startActivity(intent);
                    }
                }

        );
    }

    public void OnMyProgressClickListner(){

        myProgress.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.MyProgressActivity");
                        startActivity(intent);

                    }
                }

        );
    }






}

