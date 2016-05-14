package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Add_ExtraClass extends BaseActivity implements AdapterView.OnItemSelectedListener{

    ClassController cldc;
    EditText ClassID;
    EditText date;
    EditText Time;
    RadioGroup radio_g;
    RadioButton radio_b;
    Button btnAdd ;
    Spinner spinner_class;
    List<TutionClass> classList;
    int SpinnerClassid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add__extra_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        cldc = new ClassController(this);   // initialize student controller

        spinner_class = (Spinner)findViewById(R.id.spinner);

        classList= cldc.getClassList();

        List<String> categories = new ArrayList<String>();
        categories.add("");
        //set class list for spinner
        for(int i=0;i<classList.size();i++){
            categories.add(classList.get(i).getClassName());
        }

        // Spinner click listener
        spinner_class.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_class.setAdapter(dataAdapter);
        onAddButtonClick();
    }

    public void onAddButtonClick(){
        radio_g = (RadioGroup)findViewById(R.id.ClassType);
        btnAdd = (Button)findViewById(R.id.add);

        Time = (EditText)findViewById(R.id.editTextTime);
        date = (EditText)findViewById(R.id.editTextDate);

        btnAdd.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        int selected_id = radio_g.getCheckedRadioButtonId();
                        radio_b = (RadioButton) findViewById(selected_id);


                        if(cldc.ExtraAddClass(SpinnerClassid,date.getText().toString(),Time.getText().toString(),radio_b.getText().toString())==1)
                        {                                    // call add class method

                            Toast.makeText(Add_ExtraClass.this, "Class Details are succesfully added.", Toast.LENGTH_LONG).show();
                            Time.setText("");
                            date.setText("");
                            spinner_class.setSelection(0);



                        }
                        else {
                            Toast.makeText(Add_ExtraClass.this, "Class Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );


        };

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SpinnerClassid = position-1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}

