package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class AddPaymentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ClassController classController;

    int StudentIDPos = 0;
    int ClassIDPos = 0;

    List<String> tutionClasses ;
    List<String[][]> studentList;
    Bundle myBundle;

    Spinner spinnerstd;
    Spinner spinnercls;
    Button AddPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        classController = new ClassController(this);


        spinnerstd = (Spinner) findViewById(R.id.spinnerSName) ;
        spinnercls = (Spinner) findViewById(R.id.spinnerClass);

        tutionClasses = classController.getClassListForSpinner();


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tutionClasses);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnercls.setAdapter(dataAdapter);
        // Spinner click listener
        myBundle = getIntent().getExtras();

        if(myBundle != null){
            ClassIDPos = myBundle.getInt("ClassID");
            spinnercls.setSelection(ClassIDPos);
            spinnercls.setEnabled(false);
        }


        spinnercls.setOnItemSelectedListener(this);
        onAddPaymentClickListner();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner)parent;

        if(spinner.getId() == R.id.spinnerClass) {
            ClassIDPos = classController.getClassIDBySpinnerItemSelected(position);

            studentList = classController.getStudentListByClassID(ClassIDPos);

            ArrayList<String> students = new ArrayList<>();
            students.add("");

            for (int i=0;i< studentList.size();i++) {
                students.add(studentList.get(i)[0][1]);
            }
            // Spinner click listener
            spinnerstd.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,students );
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerstd.setAdapter(dataAdapter);

        }
        else  if(spinner.getId() == R.id.spinnerSName){

            if(position != 0){
                 StudentIDPos = Integer.parseInt(studentList.get(position-1)[0][0]);
                Log.d("55555555","/////////////////"+StudentIDPos+"///////////////////////"+ClassIDPos+"//////////////////");
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onAddPaymentClickListner(){
        AddPayment = (Button)findViewById(R.id.btnAdd);

        AddPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPayment(StudentIDPos,ClassIDPos);
                Log.d("4444444444","/////////////////"+StudentIDPos+"///////////////////////"+ClassIDPos+"//////////////////");
            }
        });

    }

    public int addPayment(int s_id,int c_id){
        if(classController.addPayment(s_id,c_id) == 1){
            Toast.makeText(AddPaymentActivity.this, "Payment added successfully.", Toast.LENGTH_LONG).show();
            return 1;
        }else {
            Toast.makeText(AddPaymentActivity.this, "Adding Payment failed.Try again.", Toast.LENGTH_LONG).show();
            return 0;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), TodaysClassActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
