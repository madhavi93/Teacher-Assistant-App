package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class UpdateStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    StudentController sdc;
    ClassController cldc;

    EditText EAddress;
    TextView EDoB;
    EditText pName;
    EditText pTP_no;
    EditText pEmail;
    Spinner spinnerclass;
    Spinner spinnerstudent;
    ImageButton datePcker;

    int StudentIDPos = 0;
    int ClassIDPos = 0;

    Map<String,String> student;
    List<String[][]> studentList;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID=1;
    private int mYear, mMonth, mDay,mHour,mMinute;

    public UpdateStudentActivity() {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get values at the form
        EAddress = (EditText)findViewById(R.id.cAddress);
        EDoB = (TextView)findViewById(R.id.cDoB);
        pName = (EditText) findViewById(R.id.cParentName);
        pTP_no = (EditText) findViewById(R.id.cTpNo);
        pEmail = (EditText) findViewById(R.id.cEmail);
        spinnerclass = (Spinner)findViewById(R.id.cname);
        spinnerstudent = (Spinner)findViewById(R.id.spinnerStudent);
        datePcker = (ImageButton)findViewById(R.id.imageButtonDate);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        sdc = new StudentController(this);  // initialize student controller
        cldc = new ClassController(this);   // initialize student controller

        List<String> classList = sdc.getClassListForSpinner();
        // Spinner click listener
        spinnerclass.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerclass.setAdapter(dataAdapter);

        datePcker.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Show the DatePickerDialog
                showDialog(DATE_DIALOG_ID);
            }
        });

        OnUpdateButtonClickListner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner)parent;

        if(spinner.getId() == R.id.cname) {
            ClassIDPos = cldc.getClassIDBySpinnerItemSelected(position);

            studentList = cldc.getStudentListByClassID(ClassIDPos);

            ArrayList<String> students = new ArrayList<>();
            students.add("");

            for (int i=0;i< studentList.size();i++) {
                students.add(studentList.get(i)[0][1]);
            }
            // Spinner click listener
            spinnerstudent.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,students );
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerstudent.setAdapter(dataAdapter);

        }
        else  if(spinner.getId() == R.id.spinnerStudent){
            if(position != 0) {
                StudentIDPos = Integer.parseInt(studentList.get(position - 1)[0][0]);
                student = sdc.getStudentBySid(StudentIDPos);
                EAddress.setText(student.get("address"));
                pName.setText(student.get("ParentName"));
                EDoB.setText(student.get("DOB"));
                pTP_no.setText(student.get("TPNo"));
                pEmail.setText(student.get("email"));
            }

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), ClassDataActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // Method automatically gets Called when you call showDialog()  method
        switch (id) {
            // create a new DatePickerDialog with values you want to show
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
            // create a new TimePickerDialog with values you want to show

        }
        return null;
    }
    public void OnUpdateButtonClickListner(){
        Button update = (Button) findViewById(R.id.btnDelete);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                student.put("address", EAddress.getText().toString());
                student.put("ParentName",pName.getText().toString());
                student.put("DOB",EDoB.getText().toString());
                student.put("TPNo", pTP_no.getText().toString());
                student.put("email",pEmail.getText().toString());

                if(sdc.UpdateStudent(student)==1){
                    Toast.makeText(UpdateStudentActivity.this, "Details are succesfully updated", Toast.LENGTH_LONG).show();
                    clearInput();
                }
                else
                    Toast.makeText(UpdateStudentActivity.this, "Details are not updated.Try again", Toast.LENGTH_LONG).show();

            }
        });
    }

    /*
    method for clear inputs
     */
    public void clearInput(){
        // clear form values
        spinnerstudent.setSelection(0);
        spinnerclass.setSelection(0);
        EDoB.setText("");
        EAddress.setText("");
        pName.setText("");
        pEmail.setText("");
        pTP_no.setText("");
    }


}
