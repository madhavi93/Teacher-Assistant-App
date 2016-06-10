package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.IntelligenceSyllabusController;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.example.madhaviruwandika.teacher_assistant.Validator.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class AddSylActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinnerLesson;
    Spinner spinnerClass;
    Spinner spinnerUnit;
    EditText lesson;
    EditText timeperiod;
    EditText specialAct;
    TextView className;

    int LessonNo;
    int UnitNo;
    int ClassID;
    int timePeriod = 0;
    String lessonName;
    String specialNote;
    Bundle myBundle;

    ClassController classController;
    IntelligenceSyllabusController intelligenceSyllabusController;

    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add_syl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        //set home button and back arrow to toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialize widgets in the form
        spinnerClass = (Spinner)findViewById(R.id.spinnerClass);
        spinnerLesson = (Spinner)findViewById(R.id.spinnerLessonNo);
        spinnerUnit = (Spinner)findViewById(R.id.spinnerUnit);
        lesson = (EditText)findViewById(R.id.editTextLesson);
        specialAct = (EditText)findViewById(R.id.editTextSpecialAct);
        timeperiod = (EditText)findViewById(R.id.editTextTimePeriod);
        className = (TextView)findViewById(R.id.ClassName);

        // initialize controllers
        classController = new ClassController(this);
        intelligenceSyllabusController = new IntelligenceSyllabusController(this);

        // initialize bundle and get data which passed fronm the previous activity
        myBundle = getIntent().getExtras();
        ClassID = myBundle.getInt("ClassID");
        className.setText(myBundle.getString("ClassName") );

        // create list of numbers for the use of sinner lesson and spinner unit
        List<String> numbers = new ArrayList<>();
        numbers.add("");
        for(int i=1;i<50;i++){
            numbers.add(String.valueOf(i));
        }

        // Spinner click listener
        spinnerUnit.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerUnit.setAdapter(dataAdapter);

        // Spinner click listener for lesson No
        spinnerLesson.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerLesson.setAdapter(dataAdapter2);


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
        ClassID = myBundle.getInt("ClassID");
        className.setText(myBundle.getString("ClassName") );
        spinnerClass.setSelection(ClassID);
        spinnerClass.setEnabled(false);

        OnAddButtonClickListner();
        OnButtonClickVeiwData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_class_manege, menu);
        return true;
    }

    @Override
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

        Spinner spinner = (Spinner)parent;
        if(spinner.getId() == R.id.spinnerClass){
            ClassID = classController.getClassIDBySpinnerItemSelected(position);
        }
        else if(spinner.getId() == R.id.spinnerUnit){
            UnitNo = position;
        }
        else if(spinner.getId() == R.id.spinnerLessonNo){
            LessonNo = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void OnAddButtonClickListner(){

        Button ADD = (Button) findViewById(R.id.buttonAdd);
        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validChect = true;
                lessonName = lesson.getText().toString();

                if(lesson.getText().toString()== null ){
                    validChect = false;
                }

                specialNote = specialAct.getText().toString();

                if (!InputValidator.isValidDigits(timeperiod.getText().toString()) || (timeperiod.getText().toString()=="") ){
                    validChect = false;
                    timeperiod.setError("Invalied input");
                }
                else
                {
                    timePeriod = Integer.parseInt(timeperiod.getText().toString());
                }



                if(validChect != false) {
                    if(intelligenceSyllabusController.addToSyllabus(ClassID, UnitNo, LessonNo, lessonName, timePeriod, specialNote)== 1){
                        Toast.makeText(AddSylActivity.this, "Lesson Detais are succesfully added.", Toast.LENGTH_LONG).show();
                        ClearText();
                    }
                    else {
                        Toast.makeText(AddSylActivity.this, "Lesson Details are not added.Try again", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(AddSylActivity.this, "There are some invalid inputs.correct them and try again", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void OnButtonClickVeiwData(){

        Button veiwD = (Button)findViewById(R.id.buttonVeiwData);
        veiwD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".Activity.SyllabusTrackerActivity.ViewSylActivity");
                intent.putExtra("ClassID", ClassID);
                intent.putExtra("ClassName",myBundle.getString("ClassName") );
                intent.putExtra("LoadFrom",2);
                startActivity(intent);
            }
        });
    }

    /*
    method for clear inputs
     */
    public void ClearText(){
        spinnerUnit.setSelection(0);
        spinnerLesson.setSelection(0);
        lesson.setText("");
        timeperiod.setText("");
        specialAct.setText("");
    }

}
