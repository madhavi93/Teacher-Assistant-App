package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.IntelligenceSyllabusController;
import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.example.madhaviruwandika.teacher_assistant.Validator.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DailyWorkActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerClass;
    Spinner spinnerUnit;
    Spinner spinnerLesson;
    Spinner spinnerAmountCovered;
    EditText timeTaken;
    EditText procedure;
    Button add;
    TextView className;
    Button seeComment;

    Bundle myBundle;
    ClassController classController;
    IntelligenceSyllabusController intelligenceSyllabusController;

    int classID;
    String classN;
    int UnitID;
    int LessonId;
    Double amountCovered;
    int timePeriod;
    String procedureNote;

    List<Map<String, String>> LessonList;
    List<Map<String,String>> unitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_work);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set home button and back arrow to toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        spinnerClass = (Spinner)findViewById(R.id.spinnerClass);
        spinnerUnit = (Spinner)findViewById(R.id.spinnerUnit);
        spinnerLesson = (Spinner)findViewById(R.id.spinnerLesson);
        spinnerAmountCovered = (Spinner)findViewById(R.id.spinnerAmountCovered);
        timeTaken = (EditText)findViewById(R.id.editTextTimePeriod);
        procedure = (EditText)findViewById(R.id.editTextProcedure);
        add = (Button)findViewById(R.id.buttonAdd);
        seeComment = (Button)findViewById(R.id.buttonSeeComment);
        className = (TextView)findViewById(R.id.className);

        myBundle = getIntent().getExtras();
        classID = myBundle.getInt("ClassID");
        className.setText(myBundle.getString("ClassName"));

        classController = new ClassController(this);
        intelligenceSyllabusController = new  IntelligenceSyllabusController(this);

        List<String> covered_Amount = new ArrayList<>();
        covered_Amount.add("");
        covered_Amount.add("0.25");
        covered_Amount.add("0.50");
        covered_Amount.add("0.75");
        covered_Amount.add("1.00");
        // Spinner click listener
        spinnerAmountCovered.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, covered_Amount);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerAmountCovered.setAdapter(dataAdapter);


        List<String> categories = classController.getClassListForSpinner();
        // Spinner click listener for class
        spinnerClass.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerClass.setAdapter(dataAdapter3);



        myBundle = getIntent().getExtras();
        classID = myBundle.getInt("ClassID");
        className.setText(myBundle.getString("ClassName") );
        spinnerClass.setSelection(classID);
        spinnerClass.setEnabled(false);


        OnAddButtonClickListner();



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        Intent myIntent = new Intent(getApplicationContext(), MyProgressActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner =(Spinner)parent;
        if(spinner.getId() == R.id.spinnerClass){

            unitList = intelligenceSyllabusController.getUnitListByClassID(classID);
            List<String> units = new ArrayList<>();
            units.add("");
            for (int i=0;i<unitList.size();i++){
                units.add(unitList.get(i).get("Unit"));
            }

            // Spinner click listener for lesson No
            spinnerUnit.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,units);
            // Drop down layout style - list view with radio button
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerUnit.setAdapter(dataAdapter2);


        }
        else if(spinner.getId() == R.id.spinnerUnit){

            if(position != 0) {
                UnitID = Integer.parseInt(unitList.get(position - 1).get("Unit"));

                LessonList = intelligenceSyllabusController.getLessonListByUnitIDandClassID(classID, UnitID);
                List<String> lessons = new ArrayList<>();
                lessons.add("");
                for (int i = 0; i < LessonList.size(); i++) {
                    lessons.add(LessonList.get(i).get("lessonNo")+"-"+LessonList.get(i).get("Lesson"));
                }

                // Spinner click listener for lesson No
                spinnerLesson.setOnItemSelectedListener(this);
                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lessons);
                // Drop down layout style - list view with radio button
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinnerLesson.setAdapter(dataAdapter2);
            }

        }
        else  if(spinner.getId() == R.id.spinnerLesson){
             if(position!= 0)
             {
                 LessonId = Integer.parseInt(LessonList.get(position-1).get("lessonNo"));

             }


        }
        else if(spinner.getId() == R.id.spinnerAmountCovered){
            if(position!= 0){
                 amountCovered = Double.parseDouble(spinner.getSelectedItem().toString());
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void OnAddButtonClickListner(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addData() == 1){
                    Toast.makeText(DailyWorkActivity.this, "Details are succesfully added.", Toast.LENGTH_LONG).show();
                    ClearText();
                }
                else {
                    Toast.makeText(DailyWorkActivity.this, "Details are not added.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onSeeCommentClickListner(){

        seeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public int addData(){

        if(InputValidator.isValidDigits(timeTaken.getText().toString())){
            timePeriod = Integer.parseInt(timeTaken.getText().toString());
        }
        else {
            timePeriod = 0;
        }
        procedureNote = procedure.getText().toString();
        return intelligenceSyllabusController.addDailyWork(classID,UnitID,LessonId,timePeriod,amountCovered,procedureNote);
    }

    public void ClearText(){

        spinnerUnit.setSelection(0);
        spinnerLesson.setSelection(0);
        spinnerAmountCovered.setSelection(0);
        timeTaken.setText("");
        procedure.setText("");
    }
}
