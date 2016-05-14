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

import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class SyllabusActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button addSyl;
    Button viewSyl;

    private Spinner spinner;


    int ClssIDPos;
    List<TutionClass> classList;

    ClassController cldc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        OnAddSylButtonClickListner();
        OnViewButtonClickListner();


    }


    public void OnAddSylButtonClickListner(){

        addSyl = (Button) findViewById(R.id.addSyl);

        addSyl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Activity.SyllabusTrackerActivity.AddSylActivity");
                        Bundle bundle = new Bundle();
                        intent.putExtra("ClassID",ClssIDPos);
                        startActivity(intent);
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
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.ViewSylActivity");
                        Bundle bundle = new Bundle();
                        intent.putExtra("ClassID",ClssIDPos);
                        startActivity(intent);
                    }
                }

        );

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ClssIDPos = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
