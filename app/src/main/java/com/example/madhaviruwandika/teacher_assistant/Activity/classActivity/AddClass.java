package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

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
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ClassController cldc;
    private EditText className;
    private TextView classtime;
    private Spinner classday;
    private TextView startingDate;
    private TextView endDate;
    private EditText fee;
    private ImageButton IstartDate;
    private ImageButton IendDate;
    private ImageButton Ifrom;
    private ImageButton ITo;
    private TextView From;
    private TextView To;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    private int mYear, mMonth, mDay,mHour,mMinute;

    int booleanStartDate = 0;
    int booleanEndDate = 0;
    int booleaFrom = 0;
    int booleanTo = 0;
    int dayID = 0;
    List<String> days;

    public AddClass(){

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
                    if(booleanStartDate == 1 ){
                        String year = String.valueOf(yearSelected);
                        String month = String.valueOf(monthOfYear+1);
                        if (month.length()==1){
                            month = "0"+month;
                        }
                        String day = String.valueOf(dayOfMonth);
                        if(day.length()== 1){
                            day = "0"+day;
                        }
                        // Set the Selected Date in Select date Button
                        startingDate.setText(day + "-" + month + "-" + year);
                        booleanStartDate = 0;
                    }
                    else if(booleanEndDate == 1){
                        String year = String.valueOf(yearSelected);
                        String month = String.valueOf(monthOfYear+1);
                        String day = String.valueOf(dayOfMonth);

                        if (month.length()==1){
                            month = "0"+month;
                        }
                        if(day.length()== 1){
                            day = "0"+day;
                        }
                        // Set the Selected Date in Select date Button
                        endDate.setText(day + "-" + month + "-" + year);
                        booleanEndDate = 0;
                    }
                }
            };



    // Register  TimePickerDialog listener
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {
                    String timeString;
                    if(hourOfDay >12){
                        hourOfDay = hourOfDay - 12;
                        timeString = "pm";
                    }
                    else  if(hourOfDay == 00){
                        hourOfDay = 12;
                        timeString = "am";
                    }
                    else {
                        timeString = "am";
                    }


                    if (booleaFrom == 1){
                        booleaFrom = 0;
                        String hour = String.valueOf(hourOfDay);
                        String minut = String.valueOf(min);
                        if (minut.length() == 1){
                            minut = "0"+minut;
                        }
                        From.setText(hour+"."+min+""+timeString);
                        classtime.setText(From.getText());

                    }
                    else  if(booleanTo == 1){
                        booleanTo = 0;
                        String hour = String.valueOf(hourOfDay);
                        String minut = String.valueOf(min);
                        if (minut.length() == 1){
                            minut = "0"+minut;
                        }
                        To.setText(hour+"."+min+""+timeString);
                        classtime.setText(From.getText()+"-"+To.getText());
                    }
                }
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);
        //set home button and back arrow to toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        // initialize class Controller
        cldc = new ClassController(this);
        // get values at the form
        className = (EditText) findViewById(R.id.editTextname);
        classday = (Spinner) findViewById(R.id.spinnerDay);
        fee = (EditText) findViewById(R.id.editTextfee);
        startingDate = (TextView) findViewById(R.id.StartDate);
        endDate = (TextView)findViewById(R.id.EndDate);
        classtime = (TextView) findViewById(R.id.editTextTime);
        From = (TextView)findViewById(R.id.textViewFromTime);
        To = (TextView)findViewById(R.id.textViewToTime);
        IendDate = (ImageButton)findViewById(R.id.imageButtonEndDate);
        IstartDate = (ImageButton)findViewById(R.id.imageButtonSdate);
        Ifrom = (ImageButton)findViewById(R.id.imageButtonFrom);
        ITo = (ImageButton)findViewById(R.id.imageButtonTo);


        days = new ArrayList<>();
        days.add("");
        days.add("Monday");
        days.add("TuesDay");
        days.add("WednessDay");
        days.add("Thursday");
        days.add("Friday");
        days.add("Saturday");
        days.add("Sunday");


        // Spinner click listener
        classday.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        classday.setAdapter(dataAdapter);
        // Set ClickListener on btnSelect start Date
        IstartDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                booleanStartDate = 1;
                // Show the DatePickerDialog
                showDialog(DATE_DIALOG_ID);
            }
        });

        // Set ClickListener on btnSelect end Date
        IendDate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                booleanEndDate = 1;
                // Show the DatePickerDialog
                showDialog(DATE_DIALOG_ID);
            }
        });

        // set Click listner for button From and To
        Ifrom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                booleaFrom = 1;
                showDialog(TIME_DIALOG_ID);
            }
        });

        ITo.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                booleanTo = 1;
                showDialog(TIME_DIALOG_ID);
            }
        });

    }


    public void onClick(View v){

        // call add class method
        if(addClassDetails()){
            Toast.makeText(AddClass.this, "Class Details are succesfully added.", Toast.LENGTH_LONG).show();
            clearInput();

        }
        else {
            Toast.makeText(AddClass.this, "Class Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
        }

    }


    public boolean addClassDetails() {

        Boolean validateCheck = true;
        /*
         *get values from the edit text views and assign them to variables
         * Check validation of the input
        */
        String ClassName = className.getText().toString();

        String time = classtime.getText().toString();
        /* if(!InputValidator.isTimeperiodValid(time)){
            classtime.setError("Not Valied Input");
            validateCheck =false;
        } */

        String day = days.get(dayID);
        if(!InputValidator.isValidLetters(day)){
            validateCheck = false;
        }

        String StartDate = startingDate.getText().toString();
        if(!InputValidator.isValidDate(StartDate)){
            startingDate.setError("INVALID INPUT"+StartDate);
            validateCheck = false;
        }


        String EndDate = endDate.getText().toString();
        if(!InputValidator.isValidDate(EndDate)){
            endDate.setError("INVALID INPUT");
            validateCheck = false;
        }

        double classfee=0;
        if(!InputValidator.ClassFeeValidator(fee.getText().toString())){
            fee.setError("Not Valied Input");
            validateCheck = false;
        }
        else {
             classfee = Double.parseDouble(fee.getText().toString());
        }

        if (validateCheck) {
            // pass class values for add to database and get student id
            if(cldc.AddClass(ClassName,time,day,StartDate,EndDate,classfee) != 0 );
                return true;

        } else {
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
                return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, false);
        }
        return null;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), ClassDataActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    /*
    method for clear input values
     */
    public void clearInput(){
        className.setText("");
        classtime.setText("");
        classday.setSelection(0);
        startingDate.setText("");
        endDate.setText("");
        fee.setText("");
        To.setText("");
        From.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0){
            dayID = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
