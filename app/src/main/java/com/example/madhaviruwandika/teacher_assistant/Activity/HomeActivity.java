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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        //create database
        DBCreator dbCreator= new DBCreator(this);
        //initalise and store key in keysrtoe
        PasswardEncryptor passwardEncryptor = new PasswardEncryptor(this);
        passwardEncryptor.createNewKeys();
        LoadSelection(0);


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





}

