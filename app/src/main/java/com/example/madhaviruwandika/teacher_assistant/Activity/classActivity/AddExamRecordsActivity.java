package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity.StudentProgressActivity;
import com.example.madhaviruwandika.teacher_assistant.Validator.InputValidator;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddExamRecordsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ClassController classController;

    private Spinner spinnerClass;
    private Spinner spinnerEType;
    private ImageButton Buttondate;
    private TextView dateText;
    private EditText lesson;
    private Button Add;


    List<TutionClass> tutionClasses;
    List<String> examType;

    String  ExamType;
    int ClassID;
    String Lesson;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    private int mYear, mMonth, mDay,mHour,mMinute;

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
                    dateText.setText(day+"-" + month + "-" + year);
                }
            };

    // Register  TimePickerDialog listener
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                // the callback received when the user "sets" the TimePickerDialog in the dialog
                public void onTimeSet(TimePicker view, int hourOfDay, int min) {

                }
            };


    public AddExamRecordsActivity(){

        // initialize variable to current date and time
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam_records);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        spinnerClass = (Spinner)findViewById(R.id.spinnerClass);
        spinnerEType = (Spinner)findViewById(R.id.spinnerExamType);
        Buttondate= (ImageButton)findViewById(R.id.imageButtondate);
        dateText = (TextView) findViewById(R.id.textViewDate);
        lesson = (EditText) findViewById(R.id.editTextLesson);


        classController = new ClassController(this);

        List<String> categoriesClasses = classController.getClassListForSpinner();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoriesClasses);
        // Drop down layout style - list view with radio button
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerClass.setAdapter(dataAdapter1);

        examType = new ArrayList<>();
        examType.add("");
        examType.add("Written");
        examType.add("verbal");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, examType);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerEType.setAdapter(dataAdapter2);


        // Spinner click listener
        spinnerClass.setOnItemSelectedListener(this);
        spinnerEType.setOnItemSelectedListener(this);

        // Set ClickListener on btnSelectDate
        Buttondate.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Show the DatePickerDialog
                showDialog(DATE_DIALOG_ID);
            }
        });


        // listner for add Button
        onAddExamDetails();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

       Spinner s = (Spinner)parent;

        if (s.getId() == R.id.spinnerClass){
            ClassID = classController.getClassIDBySpinnerItemSelected(position);

        }
        else if(s.getId() == R.id.spinnerExamType){
            ExamType  = s.getSelectedItem().toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // Method automatically gets Called when you call showDialog()  method
            switch (id) {
                case DATE_DIALOG_ID:
                    // create a new DatePickerDialog with values you want to show
                    return new DatePickerDialog(this,
                            mDateSetListener,
                            mYear, mMonth, mDay);
                // create a new TimePickerDialog with values you want to show
                case TIME_DIALOG_ID:
                    return new TimePickerDialog(this,
                            mTimeSetListener, mHour, mMinute, false);

            }
            return null;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), StudentProgressActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


    public void onAddExamDetails(){

        Add = (Button)findViewById(R.id.buttonAdd);

        Add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if ( addExams()) {
                            Toast.makeText(AddExamRecordsActivity.this, "Exam Details are added succesfully.", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddExamRecordsActivity.this, "Exam Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
                        }

                    }
                }
        );
    }

    public void ClearText(){

        spinnerClass.setSelection(0);
        spinnerEType.setSelection(0);
        dateText.setText("");
        lesson.setText("");
    }

    public Boolean addExams(){

        boolean validateCheck = true;
        String  date = dateText.getText().toString();

        if(!InputValidator.isValidDate(date)){
            dateText.setError("Input is Not Valid");
            validateCheck=false;
        }

        if(validateCheck){
            if(classController.addExams(ClassID, dateText.getText().toString(), ExamType, Lesson)==1){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
