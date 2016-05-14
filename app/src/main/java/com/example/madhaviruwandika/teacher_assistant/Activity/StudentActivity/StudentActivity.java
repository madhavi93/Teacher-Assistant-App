package com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.view.MotionEvent;
import android.widget.Spinner;
import android.widget.Toast;

import static android.view.GestureDetector.*;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Validator.InputValidator;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.View.addStudentView;

import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends BaseActivity implements addStudentView, OnGestureListener,OnDoubleTapListener,AdapterView.OnItemSelectedListener {


    StudentController sdc;
    ClassController cldc;

    EditText Ename;
    EditText Ecname;
    EditText EAddress;
    EditText EDoB;
    EditText pName;
    EditText pTP_no;
    EditText pEmail;
    Spinner spinner;

    int studentClssIDPos;
    List<TutionClass> classList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        sdc = new StudentController(this);  // initialize student controller
        cldc = new ClassController(this);   // initialize student controller

        spinner = (Spinner)findViewById(R.id.cname);
        classList= cldc.getClassList();
        List<String> categories = new ArrayList<String>();
        categories.add("");
        //set class list for spinner
        for(int i=0;i<classList.size();i++){
            categories.add(classList.get(i).getClassName());
        }

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
         studentClssIDPos = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View v){

        if(addStudentDetails()){                                    // call add student method

            Toast.makeText(StudentActivity.this, "Student Details are succesfully added.", Toast.LENGTH_LONG).show();
            Ecname.setText("");                                     // clear form values
            Ename.setText("");
            EDoB.setText("");
            EAddress.setText("");
            pName.setText("");
            pEmail.setText("");
            pTP_no.setText("");

        }
        else {
            Toast.makeText(StudentActivity.this, "Student Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean addStudentDetails() {

        Boolean validateCheck = true;

        // get values at the form
        Ename = (EditText) findViewById(R.id.txt1);
        Ecname = (EditText) findViewById(R.id.txt1);
        EAddress = (EditText)findViewById(R.id.cAddress);
        EDoB = (EditText)findViewById(R.id.cDoB);
        pName = (EditText) findViewById(R.id.cParentName);
        pTP_no = (EditText) findViewById(R.id.cTpNo);
        pEmail = (EditText) findViewById(R.id.cEmail);

        /*
         *get values from the edit text views and assign them to variables
         * Check validation of the input
        */
        String name = Ename.getText().toString();
        if(!InputValidator.isValidLetters(name)){
            Ename.setError("INVALID INPUT");
        }


        String address = EAddress.getText().toString();

        String DoB = EDoB.getText().toString();
        if(!InputValidator.isValidDate(DoB)){
            EDoB.setError("INVALID INPUT");
            validateCheck = false;
        }

        String pname = pName.getText().toString();
        if(!InputValidator.isValidLetters(pname)){
            Ename.setError("INVALID INPUT");
            validateCheck = false;
        }


        String  pTP = "";
        if(!InputValidator.isValidDigits(pTP_no.getText().toString()) || pTP_no.getText().toString().length()!= 10  ){
            pTP_no.setError("INVALID INPUT");
            validateCheck = false;
        }

        String email = pEmail.getText().toString();
        if(!InputValidator.isValidEmail(email)){
            pEmail.setError("INVALID INPUT");
            validateCheck = false;
        }

        if(studentClssIDPos!=0) {

            int studentClssID = classList.get(studentClssIDPos-1).getClassID();

            if (validateCheck) {
                // pass student values for add to database and get student id
                int SID = sdc.addStudent(name, DoB, address, studentClssID);

                //add parent details ad class which is student attending to the database
                if (SID != 0) {
                    sdc.addParent(SID, pname, pTP, email);
                    sdc.addStudentClass(SID, studentClssID);
                    return true;
                } else {
                    Toast.makeText(StudentActivity.this, "Student Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
                    return false;
                }
            } else {
                return false;
            }
        }else {

            Toast.makeText(StudentActivity.this, "Student Details are not added succesfully.Select Class and Try Again", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }


}
