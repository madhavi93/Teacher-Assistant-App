package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.List;

public class TodaysClassActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {

    private Button startClass;
    private Button markAttendence;
    private Button AddPayment;
    private Spinner spinner;

    private int studentClssIDPos;
    private ClassController cldc;
    boolean markStarting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setContentView(R.layout.activity_todays_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        // initialize Class controller
        cldc = new ClassController(this);
        // get list of class
        List<String> categories = cldc.getClassListForSpinner();


        spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        //initialize clickListner for the start button
        OnAdPaymentStudentButtonClickListner();
        OnMarkAttendenceButtonClickListner();
        OnStartClassStudentButtonClickListner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // set class is according to position of item selected from the spinner
        studentClssIDPos = cldc.getClassIDBySpinnerItemSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void OnStartClassStudentButtonClickListner(){

        startClass = (Button) findViewById(R.id.buttonStarting);
        startClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(studentClssIDPos != 0) {
                            markStarting = true;
                            if(cldc.MarkStartingOfTheClass(studentClssIDPos)==1){
                                Toast.makeText(TodaysClassActivity.this, "Class Starting Details are successfully added.", Toast.LENGTH_LONG).show();
                            }
                            else   Toast.makeText(TodaysClassActivity.this, "Class Starting Details are not added.Try Again.", Toast.LENGTH_LONG).show();

                        }
                    }
                }

        );

    }
    public void OnMarkAttendenceButtonClickListner(){

        markAttendence = (Button) findViewById(R.id.btnMarkAttendence);
        markAttendence.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(studentClssIDPos != 0 && markStarting == true) {
                            Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.MarkAttendenceActivity");
                           // Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.manegeClassActivity");
                            //initialize bundel and attach values to send to new activity
                            Bundle bundle = new Bundle();
                            intent.putExtra("ClassID",studentClssIDPos);

                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(TodaysClassActivity.this, "Pleace Select Class and make sure to \"Mark Starting\" Of the class.", Toast.LENGTH_LONG).show();

                        }
                    }
                }

        );

    }
    public void OnAdPaymentStudentButtonClickListner(){

        AddPayment = (Button) findViewById(R.id.buttonAddPayment);
        AddPayment.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (studentClssIDPos != 0) {
                            Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.AddPaymentActivity");
                            // Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.manegeClassActivity");
                            //initialize bundel and attach values to send to new activity
                            Bundle bundle = new Bundle();
                            intent.putExtra("ClassID", studentClssIDPos);
                            startActivity(intent);
                        }
                    }
                }

        );

    }


}
