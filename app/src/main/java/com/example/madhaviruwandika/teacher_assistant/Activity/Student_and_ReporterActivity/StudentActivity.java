package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Validator.InputValidator;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;


import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.Calendar;
import java.util.List;

public class StudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    StudentController sdc;
    ClassController cldc;

    EditText Ename;
    EditText Ecname;
    EditText EAddress;
    TextView EDoB;
    EditText pName;
    EditText pTP_no;
    EditText pEmail;
    Spinner spinner;
    ImageButton datePcker;

    int studentClssIDPos;
    List<String> classList;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    private int mYear, mMonth, mDay,mHour,mMinute;

    public StudentActivity(){
        // initialize variable to current date and time
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
    }


    // Register  DatePickerDialog listener
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                // the callback received when the user "sets" the Date in the DatePickerDialog
                public void onDateSet(DatePicker view, int yearSelected,
                                      int monthOfYear, int dayOfMonth) {
                    String year = String.valueOf(yearSelected);
                    String month = String.valueOf(monthOfYear+1);
                    String day = String.valueOf(dayOfMonth);
                    // Set the Selected Date in Select date Button
                    EDoB.setText(day + "-" + month + "-" + year);
                }
            };

    // Register  TimePickerDialog listener
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {

                }
            };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        // get values at the form
        Ename = (EditText) findViewById(R.id.editTextSName);
        EAddress = (EditText)findViewById(R.id.cAddress);
        EDoB = (TextView)findViewById(R.id.cDoB);
        pName = (EditText) findViewById(R.id.cParentName);
        pTP_no = (EditText) findViewById(R.id.cTpNo);
        pEmail = (EditText) findViewById(R.id.cEmail);
        spinner = (Spinner)findViewById(R.id.cname);
        datePcker = (ImageButton)findViewById(R.id.imageButtonDate);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        sdc = new StudentController(this);  // initialize student controller
        cldc = new ClassController(this);   // initialize student controller

        List<String> classList = sdc.getClassListForSpinner();

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        datePcker.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the DatePickerDialog
                showDialog(DATE_DIALOG_ID);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         studentClssIDPos = sdc.getClassIDBySpinnerItemSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View v){

        if(addStudentDetails()){                                    // call add student method

            Toast.makeText(StudentActivity.this, "Student Details are succesfully added.", Toast.LENGTH_LONG).show();
            clearInput();

        }
        else {
            Toast.makeText(StudentActivity.this, "Student Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
        }

    }


    public boolean addStudentDetails() {

        Boolean validateCheck = true;
        /*
         *get values from the edit text views and assign them to variables
         * Check validation of the input
        */
        String name = Ename.getText().toString();
        if(!InputValidator.isValidLetters(name) || name == ""){
            Ename.setError("INVALID INPUT");
            validateCheck = false;
        }

        String address = EAddress.getText().toString();


        String DoB = EDoB.getText().toString();
        if(!InputValidator.isValidDate(DoB) || DoB == ""){
            EDoB.setError("INVALID INPUT");
            validateCheck = false;
        }
        if(!InputValidator.isDateisPast(DoB)){
            EDoB.setError("INVALID INPUT");
            validateCheck = false;
        }


        String pname = pName.getText().toString();
        if(!InputValidator.isValidLetters(pname) || pname == "" ){
            Ename.setError("INVALID INPUT");
            validateCheck = false;
        }

        String  pTP = pTP_no.getText().toString();
        if(!InputValidator.isValidDigits(pTP_no.getText().toString()) || pTP_no.getText().toString().length()!= 10 ){
            pTP_no.setError("INVALID INPUT");
            validateCheck = false;
        }
        if(pTP == ""){
            validateCheck = false;
        }

        String email = pEmail.getText().toString();
        if(!InputValidator.isValidEmail(email) || email ==""){
            pEmail.setError("INVALID INPUT");
            validateCheck = false;
        }

        if(studentClssIDPos!=0) {
            if (validateCheck) {
                // pass student values for add to database and get student id
                int SID = sdc.addStudent(name, DoB, address, studentClssIDPos);

                //add parent details ad class which is student attending to the database
                if (SID != 0) {
                    sdc.addParent(SID, pname, pTP, email);
                    sdc.addStudentClass(SID, studentClssIDPos);
                    return true;
                } else {
                    Toast.makeText(StudentActivity.this, "Student Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                Toast.makeText(StudentActivity.this, "There are some invalid inputs.please correct them and retry.", Toast.LENGTH_LONG).show();
                return false;
            }
        }else {

            Toast.makeText(StudentActivity.this, "Student Details are not added successfully.Select Class and Try Again", Toast.LENGTH_LONG).show();
            return false;
        }

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // Method automatically gets Called when you call showDialog()  method
        switch (id) {
            // create a new DatePickerDialog with values you want to show
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            // create a new TimePickerDialog with values you want to show
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, mHour, mMinute, false);

        }
        return null;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), ClassDataActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public void clearInput(){
        // clear form values
        Ename.setText("");
        EDoB.setText("");
        EAddress.setText("");
        pName.setText("");
        pEmail.setText("");
        pTP_no.setText("");
    }


}
