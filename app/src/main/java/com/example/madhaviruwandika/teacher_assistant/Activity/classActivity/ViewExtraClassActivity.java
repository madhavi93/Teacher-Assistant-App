package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewExtraClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner tclass;
    Spinner ExtraClass;
    FrameLayout container;
    ClassController classController;


    List<Map<String,String>> extraClassList;
    int Classid = 0;
    int ExamID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_extra_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set home button and back arrow in tool bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        tclass = (Spinner) findViewById(R.id.spinnerClass);
        ExtraClass = (Spinner) findViewById(R.id.spinnerExtraClass);
        container = (FrameLayout) findViewById(R.id.extraClassFragment);

        // initialize student controller
        classController = new ClassController(this);

        List<String> categories = classController.getClassListForSpinner();
        // Spinner click listener
        tclass.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        tclass.setAdapter(dataAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;

        if(spinner.getId() == R.id.spinnerClass){
            if(position != 0) {
                Classid = classController.getClassIDBySpinnerItemSelected(position);
                extraClassList = classController.getExtraClassByID(Classid);

                ArrayList<String> items = new ArrayList<>();
                items.add("");
                for(int i=0;i<extraClassList.size();i++){
                    items.add(extraClassList.get(i).get("date")+"_"+extraClassList.get(i).get("type"));
                }
                // Spinner click listener
                ExtraClass.setOnItemSelectedListener(this);
                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                ExtraClass.setAdapter(dataAdapter);

            }
        }

        if(spinner.getId() == R.id.spinnerExtraClass){
            Fragment fragment;
            if(position!= 0){

                Map<String,String> exc = extraClassList.get(position - 1);
                Bundle bundle = new Bundle();
                bundle.putInt("extraClassID",Integer.parseInt(exc.get("extraClassID")));
                bundle.putString("time", exc.get("time"));
                bundle.putString("date", exc.get("date"));
                bundle.putString("type", exc.get("type"));
                bundle.putString("state",exc.get("state"));

                fragment= new ViewExtra_ClassDetails();
                fragment.setArguments(bundle);
                FragmentManager fm = getFragmentManager();
                FragmentTransaction fragmentTransaction = fm.beginTransaction();
                fragmentTransaction.replace(R.id.extraClassFragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), ClassDataActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
