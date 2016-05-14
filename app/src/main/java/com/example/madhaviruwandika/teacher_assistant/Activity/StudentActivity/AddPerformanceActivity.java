package com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.R;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import java.util.ArrayList;
import java.util.List;

public class AddPerformanceActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{


    private Button startClass;
    private Spinner spinner;
    private LinearLayout linearLayout;


    int studentClssIDPos;
    List<TutionClass> classList;

    ClassController cldc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add_performance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        cldc = new ClassController(this);   // initialize student controller

        spinner = (Spinner)findViewById(R.id.cname);
        linearLayout = (LinearLayout)findViewById(R.id.firstrow);
        LinearLayout newL = new LinearLayout(this);

        TextView firstRowCol1 = new TextView(this);
        firstRowCol1.setText("Name");
        firstRowCol1.setBackgroundColor(0x88FF);
        TextView firstRowCol2 = new TextView(this);
        firstRowCol2.setBackgroundColor(0x88FF);
        firstRowCol2.setText("Mark");

        newL.addView(firstRowCol1);
        newL.addView(firstRowCol2);

        linearLayout.addView(newL);
        classList= cldc.getClassList();

        List<String> categories = new ArrayList<String>();
        categories.add("");
        //set class list for spinner
        for(int i=0;i<classList.size();i++){
            categories.add(classList.get(i).getClassName());
        }

        spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {






    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
