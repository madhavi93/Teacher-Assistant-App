package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.SettingsActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeleteStudentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    StudentController sdc;
    ClassController cldc;

    TextView EAddress;
    TextView EDoB;
    TextView pName;
    TextView pTP_no;
    TextView pEmail;
    Spinner spinnerclass;
    Spinner spinnerstudent;

    int StudentIDPos = 0;
    int ClassIDPos = 0;

    Map<String,String> student;
    List<String[][]> studentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // get values at the form
        EAddress = (TextView)findViewById(R.id.cAddress);
        EDoB = (TextView)findViewById(R.id.cDoB);
        pName = (TextView) findViewById(R.id.cParentName);
        pTP_no = (TextView) findViewById(R.id.cTpNo);
        pEmail = (TextView) findViewById(R.id.cEmail);
        spinnerclass = (Spinner)findViewById(R.id.cname);
        spinnerstudent = (Spinner)findViewById(R.id.spinnerStudent);

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

        OnDeleteButtonClickListner();
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
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

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

    public void OnDeleteButtonClickListner(){
        Button delete = (Button) findViewById(R.id.btnDelete);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sdc.DeleteStudent(StudentIDPos) == 1) {
                    Toast.makeText(DeleteStudentActivity.this, "Details are succesfully Deleted", Toast.LENGTH_LONG).show();
                    clearInput();
                } else
                    Toast.makeText(DeleteStudentActivity.this, "Details are not Deleted.Try again", Toast.LENGTH_LONG).show();

            }
        });
    }
}
