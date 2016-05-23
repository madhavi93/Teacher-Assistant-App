package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class UpdateClassDActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private ClassController cldc;
    private Spinner className;
    private TextView classtime;
    private EditText classday;
    private TextView startingDate;
    private TextView endDate;
    private EditText fee;
    private Button Update;
    private ImageButton IstartDate;
    private ImageButton IendDate;
    private ImageButton Ifrom;
    private ImageButton ITo;
    private TextView From;
    private TextView To;
    private int SpinnerClassid;

    Map<String,String> tutionClass;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    private int mYear, mMonth, mDay,mHour,mMinute;

    int booleanStartDate = 0;
    int booleanEndDate = 0;
    int booleaFrom = 0;
    int booleanTo = 0;

    public UpdateClassDActivity() {

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
                        String day = String.valueOf(dayOfMonth);

                        if (month.length()==1){
                            month = "0"+month;
                        }
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

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_class_d);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialize controller
        cldc = new ClassController(this);

        // get values at the form
        className = (Spinner) findViewById(R.id.editTextname);

        classday = (EditText) findViewById(R.id.editTextDay);
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
        Update = (Button)findViewById(R.id.btnUpdate);


        List<String> categories = cldc.getClassListForSpinner();
        // Spinner click listener
        className.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        className.setAdapter(dataAdapter);

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

        // set click listner for update button
        onUpdateButtonClick();

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        SpinnerClassid = cldc.getClassIDBySpinnerItemSelected(position);

        tutionClass = cldc.getTutionClassByID(SpinnerClassid);
        if(tutionClass != null) {
            classtime.setText(tutionClass.get("Time"));
            classday.setText(tutionClass.get("Day"));
            startingDate.setText(tutionClass.get("StartDate"));
            endDate.setText(tutionClass.get("EndDate"));
            fee.setText(String.valueOf(tutionClass.get("fee")));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void onUpdateButtonClick(){

        Update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tutionClass.put("Day", classday.getText().toString());
                        tutionClass.put("StartDate", startingDate.getText().toString());
                        tutionClass.put("EndDate", endDate.getText().toString());
                        tutionClass.put("Time", classtime.getText().toString());
                        tutionClass.put("fee",fee.getText().toString());

                        if (cldc.UpdateClass(tutionClass) == 1) {
                            Toast.makeText(UpdateClassDActivity.this, "Class Details are updated succesfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(UpdateClassDActivity.this, "Class Details are not Updated succesfully.Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                 });
        }
    }
