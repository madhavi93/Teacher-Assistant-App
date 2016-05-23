package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddPerformanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private Button saveMark;
    private Spinner spinner;
    private Spinner spinnerExam;
    private TableLayout logsTableLayout;

    int studentClssIDPos;
    List<String[][]> studentList;
    List<Map<String,String>> exams;
    ClassController cldc;
    StudentController stcr;
    int ExamId = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add_performance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        //set home and back buttom in toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialize class controller
        stcr = new StudentController(this);

        logsTableLayout = (TableLayout) findViewById(R.id.markList);
        TableRow tableRowHeader = (TableRow) findViewById(R.id.logs_table_header);

        List<String> categories = stcr.getClassListForSpinner();

        spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        exams = stcr.getExamList();
        ArrayList<String> examNames = new ArrayList<>();
        examNames.add("");

        for (int i=0; i < exams.size(); i++){
            String ename = exams.get(i).get("ClassID")+"_"+exams.get(i).get("date");
            examNames.add(ename);
        }

        spinnerExam = (Spinner) findViewById(R.id.spinnerExam);
        spinnerExam.setOnItemSelectedListener(this);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, examNames);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerExam.setAdapter(dataAdapter2);

        onAddButtonClickListner();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner)parent;

        if(spinner.getId() == R.id.spinner) {
            studentClssIDPos = position;

            studentList = stcr.getStudentListByClassID(studentClssIDPos);
            // set student list
            for (String[][] s : studentList) {

                TableRow tr = new TableRow(this);

                TextView Sid = new TextView(this);
                Sid.setText(String.valueOf(" " + s[0][0]));
                tr.addView(Sid);

                TextView name = new TextView(this);
                name.setText(s[0][1]);
                tr.addView(name);

                EditText mark = new EditText(this);
                mark.setText("");
                tr.addView(mark);
                logsTableLayout.addView(tr);
            }
        }
        else  if(spinner.getId() == R.id.spinnerExam){

            if(position != 0){
                ExamId = Integer.parseInt(exams.get(position-1).get("ExamID"));
            }

        }
    }

    public void  onAddButtonClickListner(){

        saveMark = (Button)findViewById(R.id.buttonAdd);
        saveMark.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ArrayList<String[]> markList = new ArrayList<>();
                        // generate list of marks
                        for(int i=1;i< logsTableLayout.getChildCount();i++){

                            TableRow row = (TableRow)logsTableLayout.getChildAt(i);
                            for(int j=0;j<3;j++){

                                String[] studentMark = new String[2];

                                TextView id = (TextView)row.getChildAt(0);
                                studentMark[0] =  id.getText().toString();

                                EditText mark = (EditText)row.getChildAt(2);
                                studentMark[1] = mark.getText().toString();

                                markList.add(studentMark);
                            }
                        }

                        if(ExamId != 0) {

                            if(cldc.addInclassMarks(markList, ExamId)== 1){
                                Toast.makeText(AddPerformanceActivity.this, "Marks are added succesfully.", Toast.LENGTH_LONG).show();
                                RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.markListLayout);
                                relativeLayout.setEnabled(false);
                            }
                            else {
                                Toast.makeText(AddPerformanceActivity.this, "Marks are not added succesfully.Select Class and Try Again", Toast.LENGTH_LONG).show();
                            }
                        }


                    }});
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), StudentProgressActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }
}
