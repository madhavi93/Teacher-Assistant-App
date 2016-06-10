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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Validator.InputValidator;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.Calendar;
import java.util.List;

public class Add_ExtraClass extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ClassController cldc;
    EditText ClassID;
    TextView date;
    TextView Time;
    RadioGroup radio_g;
    RadioButton radio_b;
    Button btnAdd ;
    Spinner spinner_class;
    ImageButton datePcker;
    ImageButton Ifrom;
    ImageButton ITo;
    TextView From;
    TextView To;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    private int mYear, mMonth, mDay,mHour,mMinute;

    int booleanStartDate = 0;
    int booleanEndDate = 0;
    int booleaFrom = 0;
    int booleanTo = 0;
    int SpinnerClassid = 0;

    public Add_ExtraClass(){

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
                    if (month.length()==1){
                        month = "0"+month;
                    }
                    if(day.length()== 1){
                        day = "0"+day;
                    }
                    // Set the Selected Date in Select date Button
                    date.setText(day + "-" + month + "-" + year);
                }
            };

    // Register  TimePickerDialog listener
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {

                    String timeString;
                    // change selected value of time picker to 12 hours time format
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
                    // set selected time to 'From' text view
                    if (booleaFrom == 1){
                        booleaFrom = 0;
                        String hour = String.valueOf(hourOfDay);
                        String minut = String.valueOf(min);
                        if (minut.length() == 1){
                            minut = '0'+minut;
                        }
                        From.setText(hour+"."+minut+""+timeString);
                        Time.setText(From.getText());

                    }
                    // set selected time to 'To' text view
                    else  if(booleanTo == 1){
                        booleanTo = 0;
                        String hour = String.valueOf(hourOfDay);
                        String minut = String.valueOf(min);
                        if (minut.length() == 1){
                            minut = '0'+minut;
                        }
                        To.setText(hour+"."+minut+""+timeString);
                        Time.setText(From.getText() + "-" + To.getText());
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add__extra_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        // set home button and back arrow in tool bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialize view items
        radio_g = (RadioGroup)findViewById(R.id.ClassType);
        btnAdd = (Button)findViewById(R.id.btnDelete);
        Time = (TextView)findViewById(R.id.textViewTime);
        date = (TextView)findViewById(R.id.textViewDate);
        datePcker = (ImageButton)findViewById(R.id.imageButtonDate);
        Ifrom = (ImageButton)findViewById(R.id.imageButtonFrom);
        ITo = (ImageButton)findViewById(R.id.imageButtonTo);
        From = (TextView)findViewById(R.id.textViewFrom);
        To = (TextView) findViewById(R.id.textViewTo);
        spinner_class = (Spinner)findViewById(R.id.spinner);


        // initialize student controller
        cldc = new ClassController(this);
        List<String> categories = cldc.getClassListForSpinner();

        // Spinner click listener
        spinner_class.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_class.setAdapter(dataAdapter);

        //set click listner for date picker
        datePcker.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the DatePickerDialog
                showDialog(DATE_DIALOG_ID);
            }
        });

        //set click listner for time pickers
        Ifrom.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                booleaFrom = 1;
                showDialog(TIME_DIALOG_ID);
            }
        });
        ITo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                booleanTo = 1;
                showDialog(TIME_DIALOG_ID);
            }
        });

        // initialize click listner for add data button.
        onAddButtonClick();

    }

    public void onAddButtonClick(){
        btnAdd.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        boolean inputValidation = true;
                        //get selected redio button
                        int selected_id = radio_g.getCheckedRadioButtonId();
                        radio_b = (RadioButton) findViewById(selected_id);

                        // set class type according to selected redio button
                        String classType = "";
                        if(radio_b != null){
                            classType = radio_b.getText().toString();
                        }
                        else {
                            inputValidation = false;
                        }

                        // set date of the class
                        String dateOfClass = date.getText().toString();
                        if(!InputValidator.isValidDate(dateOfClass) || dateOfClass==""){
                            inputValidation = false;
                            date.setError("Not a valid Date");
                        }
                        if(InputValidator.isDateisPast(dateOfClass)){
                            inputValidation = false;
                            date.setError("Not a valid Date");
                        }

                        //set time of the class
                        String time = Time.getText().toString();

                        // check whether the class is selected
                        if(SpinnerClassid != 0 ) {
                            // check whether there are any invalid input
                            if(inputValidation == true) {
                                if (cldc.ExtraAddClass(SpinnerClassid, dateOfClass, time, classType) == 1) {
                                    // call add class method
                                    Toast.makeText(Add_ExtraClass.this, "Class Details are succesfully added.", Toast.LENGTH_LONG).show();
                                    clearView();
                                } else {
                                    Toast.makeText(Add_ExtraClass.this, "Class Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
                                    clearView();
                                }
                            }
                            else {
                                Toast.makeText(Add_ExtraClass.this, "You have entered some invalid inputs.Please correct them and retry", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(Add_ExtraClass.this, "Please select Class.", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );


        };


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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // set class id according to selected item in the spinner
        if(position != 0) {
            SpinnerClassid = cldc.getClassIDBySpinnerItemSelected(position);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // set action when home button is clicked
        Intent myIntent = new Intent(getApplicationContext(), ClassDataActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    /*
    *method for clear data co the view
     */
    public void clearView(){
        Time.setText("");
        date.setText("");
        To.setText("");
        From.setText("");
        spinner_class.setSelection(0);
    }
}

