package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class AddPaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ClassController classController;

    int StudentIDPos = 0;
    int ClassIDPos = 0;
    String monthP = "";

    List<String> tutionClasses ;
    List<String[][]> studentList;
    Bundle myBundle;
    ArrayList<String> monthList;

    Spinner spinnerstd;
    Spinner spinnercls;
    Spinner month;
    Button AddPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set home button enable
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialize class controller
        classController = new ClassController(this);

        // initialize form widgets
        spinnerstd = (Spinner) findViewById(R.id.spinnerSName) ;
        spinnercls = (Spinner) findViewById(R.id.spinnerClass);
        month = (Spinner)findViewById(R.id.spinnerFor_month);

        // set tution class list for the use of class spinner
        tutionClasses = classController.getClassListForSpinner();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tutionClasses);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnercls.setAdapter(dataAdapter);

        // create array list for the use of month spinner
        monthList = new ArrayList<>();
        monthList.add("");
        monthList.add("January");
        monthList.add("February");
        monthList.add("March");
        monthList.add("April");
        monthList.add("May");
        monthList.add("June");
        monthList.add("July");
        monthList.add("August");
        monthList.add("September");
        monthList.add("November");
        monthList.add("December");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthList);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        month.setAdapter(dataAdapter2);

        // initialize the bundle and retrieve valus from the bundle
        myBundle = getIntent().getExtras();
        if(myBundle != null){
            // get class id that is passed from the previous activity
            ClassIDPos = myBundle.getInt("ClassID");
            spinnercls.setSelection(ClassIDPos);
            spinnercls.setEnabled(false);
        }
        spinnercls.setOnItemSelectedListener(this);
        // set click listner on the add button
        onAddPaymentClickListner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner)parent;
        // set action if selected item is from the class spinner
        if(spinner.getId() == R.id.spinnerClass) {
            ClassIDPos = classController.getClassIDBySpinnerItemSelected(position);

            studentList = classController.getStudentListByClassID(ClassIDPos);

            ArrayList<String> students = new ArrayList<>();
            students.add("");

            for (int i=0;i< studentList.size();i++) {
                students.add(studentList.get(i)[0][1]);
            }
            // Spinner click listener
            spinnerstd.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,students );
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerstd.setAdapter(dataAdapter);

        }
        // set action if selected item is from the student name spinner
        else  if(spinner.getId() == R.id.spinnerSName){

            if(position != 0) {
                StudentIDPos = Integer.parseInt(studentList.get(position-1)[0][0]);
            }
        }

        // set action if selected item is from the month spinner
        else if(spinner.getId() == R.id.spinnerFor_month){
           if(position != 0){
            monthP = monthList.get(position);
           }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    /*
    method that initialize click listner on add button
     */
    public void onAddPaymentClickListner(){
        AddPayment = (Button)findViewById(R.id.btnAdd);

        AddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPayment(StudentIDPos, ClassIDPos);
            }
        });

    }

    /*
    method that pass data to controller for the purpose of adding to the database
     */
    public int addPayment(int s_id,int c_id){

        if((!monthP.equals("") )|| (c_id!=0)||(s_id!=0)) {
            if (classController.addPayment(s_id, c_id,monthP) == 1) {
                Toast.makeText(AddPaymentActivity.this, "Payment added successfully.", Toast.LENGTH_LONG).show();
                clearInterface();
                return 1;
            } else {
                Toast.makeText(AddPaymentActivity.this, "Adding Payment failed.Try again.", Toast.LENGTH_LONG).show();
                return 0;
            }
        }
        else {
            Toast.makeText(AddPaymentActivity.this, "Missing data.please check again", Toast.LENGTH_LONG).show();
            return 0;
        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //set action when home button is selected
        Intent myIntent = new Intent(getApplicationContext(), TodaysClassActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    /*
    method that clear all the inputs
     */
    public void clearInterface(){
        spinnercls.setSelection(0);
        spinnerstd.setSelection(0);
        month.setSelection(0);
    }
}
