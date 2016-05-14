package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Validator.InputValidator;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.R;

public class AddClass extends BaseActivity {

    ClassController cldc;
    EditText className;
    EditText classtime;
    EditText classday;
    EditText startingDate;
    EditText endDate;
    EditText fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        cldc = new ClassController(this);


    }

    public void onClick(View v){

        if(addClassDetails()){                                    // call add class method

            Toast.makeText(AddClass.this, "Class Details are succesfully added.", Toast.LENGTH_LONG).show();
            className.setText("");                                     // clear form values
            classtime.setText("");
            classday.setText("");
            startingDate.setText("");
            endDate.setText("");
            fee.setText("");

        }
        else {
            Toast.makeText(AddClass.this, "Class Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
        }

    }


    public boolean addClassDetails() {

        Boolean validateCheck = true;

        // get values at the form
        className = (EditText) findViewById(R.id.editTextname);
        classtime = (EditText) findViewById(R.id.editTextTime);
        classday = (EditText) findViewById(R.id.editTextDay);
        startingDate = (EditText) findViewById(R.id.editTextSdate);
        endDate = (EditText) findViewById(R.id.editTextendDate);
        fee = (EditText) findViewById(R.id.editTextfee);
        /*
         *get values from the edit text views and assign them to variables
         * Check validation of the input
        */
        String ClassName = className.getText().toString();

        String time = classtime.getText().toString();

        String day = classday.getText().toString();
        if(!InputValidator.isValidLetters(day)){
            classday.setError("INVALID INPUT");
            validateCheck = false;
        }

        String StartDate = startingDate.getText().toString();

        /*if(!InputValidator.isValidDate(StartDate)){
            startingDate.setError("INVALID INPUT"+StartDate);
            validateCheck = false;
        }
        */

        String EndDate = endDate.getText().toString();
        /*if(!InputValidator.isValidDate(EndDate)){
            endDate.setError("INVALID INPUT");
            validateCheck = false;
        }
        */
        double classfee = Double.parseDouble(fee.getText().toString());

         if (validateCheck) {
                // pass class values for add to database and get student id
             if(cldc.AddClass(ClassName,time,day,StartDate,EndDate,classfee) != 0 );
                    return true;

            } else {
                return false;
            }

    }


}
