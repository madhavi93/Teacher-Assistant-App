package com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.SettingsActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.CommunicationController;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SendEmailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    CheckBox groupMessage;
    EditText Etext_message;
    Spinner spinnerClass;
    Spinner spinnerStudent;
    String  message ;

    List<String[][]> studentList;
    int ClassID;
    String className;
    int StudentID;
    boolean isReportAdded;
    CommunicationController communicationController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set home and back button to toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialise widgets in the form
        spinnerClass = (Spinner) findViewById(R.id.spinnerClass);
        spinnerStudent =(Spinner)findViewById(R.id.spinnerStudent);
        Etext_message  = (EditText)findViewById(R.id.editTextEmail);
        groupMessage = (CheckBox)findViewById(R.id.checkBoxGroupEmail);

        isReportAdded = false;
        // initialize controller
        communicationController = new CommunicationController(this);

        List<String> categories = communicationController.getClassListForSpinner();
        // Spinner click listener
        spinnerClass.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerClass.setAdapter(dataAdapter);

        onButtonSendClickListner();
        SetClickListnerOnGroup();
    }

    /*
    button click listner for send button
     */
    public void onButtonSendClickListner(){

        Button send = (Button)findViewById(R.id.btnSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        // check whether the email is for group
                        if (!groupMessage.isChecked()) {
                            message = Etext_message.getText().toString();
                            Intent emailIntent = communicationController.sendEmailMessage(className, StudentID, message);
                            Intent choser = Intent.createChooser(emailIntent, "......Sending Email.....");
                            SendEmailActivity.this.startActivity(choser);
                            ClearText();
                        } else {
                            // send email for all of the parent's of students
                            message = Etext_message.getText().toString();
                            Intent emailIntent = communicationController.sendEmailForGroup(ClassID, className, message);
                            Intent choser = Intent.createChooser(emailIntent, "......Sending Email.....");
                            SendEmailActivity.this.startActivity(choser);
                            ClearText();
                        }


            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner)parent;
        // set action if selected spinner item is from class spinner
        if(spinner.getId() == R.id.spinnerClass){
            // check if message is group or not
            if(!groupMessage.isChecked()){
                // get selected class id
                ClassID = communicationController.getClassIDBySpinnerItemSelected(position);
                className = spinner.getSelectedItem().toString();
                // get student list of the selected class
                studentList = communicationController.getStudentListByClassID(ClassID);

                ArrayList<String> students = new ArrayList<>();
                students.add("");
                for (int i=0;i< studentList.size();i++) {
                    students.add(studentList.get(i)[0][1]);
                }

                // Spinner click listener
                spinnerStudent.setOnItemSelectedListener(this);
                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,students );
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinnerStudent.setAdapter(dataAdapter);

            }
            else
            {
                ClassID = communicationController.getClassIDBySpinnerItemSelected(position);
            }
        }
        // set action if selected spinner item is from recipient spinner
        else if(spinner.getId() == R.id.spinnerStudent){
            if(position!=0) {
                StudentID = Integer.parseInt(studentList.get(position-1)[0][0]);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), SendMessageActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /*
      this metnod is to implement click listener on check boa
     */
    public void SetClickListnerOnGroup(){

        groupMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerStudent.setSelection(0);
                spinnerStudent.setEnabled(!spinnerStudent.isEnabled());
            }
        });
    }

    /*
    this method is to clear the interfaces after adding data
     */

    public void ClearText(){
        spinnerStudent.setSelection(0);
        spinnerClass.setSelection(0);
        Etext_message.setText("");
        groupMessage.setChecked(false);
    }


}
