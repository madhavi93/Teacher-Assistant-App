package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.HomeActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class MyProgressActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    Button AddMyWork;
    Button addSyl;
    Button viewSyl;

    private Spinner spinner;

    int ClssIDPos;
    String ClassName;
    List<TutionClass> classList;

    ClassController cldc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_my_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);


        cldc = new ClassController(this);   // initialize student controller

        spinner = (Spinner)findViewById(R.id.spinner);
        classList= cldc.getClassList();

        List<String> categories = new ArrayList<String>();
        categories.add("");
        //set class list for spinner
        for(int i=0;i<classList.size();i++){
            categories.add(classList.get(i).getClassName());
        }
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        OnAddMyWorkButtonClickListner();
        OnAddSylButtonClickListner();
        OnViewButtonClickListner();
    }




    public void OnAddSylButtonClickListner(){

        addSyl = (Button) findViewById(R.id.addSyl);

        addSyl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ClssIDPos != 0) {
                            Intent intent = new Intent(".Activity.SyllabusTrackerActivity.AddSylActivity");
                            intent.putExtra("ClassID", ClssIDPos);
                            intent.putExtra("ClassName", ClassName);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MyProgressActivity.this," Please select class .",Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );

    }

    public void OnViewButtonClickListner(){

        viewSyl = (Button) findViewById(R.id.ViewSyl);

        viewSyl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ClssIDPos != 0) {
                            Intent intent = new Intent(".Activity.SyllabusTrackerActivity.ViewSylActivity");
                            intent.putExtra("ClassID", ClssIDPos);
                            intent.putExtra("ClassName", ClassName);
                            intent.putExtra("LoadFrom",1);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MyProgressActivity.this," Please select class .",Toast.LENGTH_LONG).show();
                        }
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
                        if(ClssIDPos != 0){
                            Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.MyWorkActivity");
                            intent.putExtra("ClassID",ClssIDPos);
                            intent.putExtra("ClassName",ClassName);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MyProgressActivity.this," Please select class .",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ClssIDPos = position;
        if(position != 0) {
            ClassName = classList.get(position-1).getClassName();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }




}
