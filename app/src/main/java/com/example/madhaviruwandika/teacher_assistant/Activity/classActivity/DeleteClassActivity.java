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
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.List;
import java.util.Map;

public class DeleteClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ClassController cldc;
    private Spinner className;
    private TextView classtime;
    private TextView classday;
    private TextView startingDate;
    private TextView endDate;
    private TextView fee;
    private Button Delete;
    private int SpinnerClassid;

    Map<String,String> tutionClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialize controller
        cldc = new ClassController(this);

        // get values at the form
        className = (Spinner) findViewById(R.id.editTextname);
        classday = (TextView) findViewById(R.id.editTextDay);
        fee = (TextView) findViewById(R.id.editTextfee);
        startingDate = (TextView) findViewById(R.id.StartDate);
        endDate = (TextView)findViewById(R.id.EndDate);
        classtime = (TextView) findViewById(R.id.editTextTime);
        Delete = (Button) findViewById(R.id.btnDelete);


        List<String> categories = cldc.getClassListForSpinner();
        // Spinner click listener
        className.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        className.setAdapter(dataAdapter);

        onDeleteButtonClick();

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
    public void onDeleteButtonClick(){

        Delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (cldc.DeleteClass(SpinnerClassid) == 1) {
                            Toast.makeText(DeleteClassActivity.this, "Class Details are Deleted succesfully", Toast.LENGTH_LONG).show();
                            ClearInput();
                        } else {
                            Toast.makeText(DeleteClassActivity.this, "Class Details are not Deleted succesfully.Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    /*
    method for clear inputs
     */
    public void ClearInput(){
        classday.setText("");
        startingDate.setText("");
        endDate.setText("");
        fee.setText("");
        classtime.setText("");
        className.setSelection(0);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // set action when home button is clicked
        Intent myIntent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
