package com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.HomeActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.CommunicationController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class SendSMSActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner recipient;
    Spinner spinnerClass;
    Button sendSMS;
    EditText message;
    CheckBox groupMessage;

    List<String[][]> studentList;
    int ClassID;
    int StudentID;
    CommunicationController communicationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // set home and back button to toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // initialise widgets in the form
        spinnerClass = (Spinner) findViewById(R.id.spinnerClass);
        recipient =(Spinner)findViewById(R.id.spinnerRecipient);
        sendSMS =(Button)findViewById(R.id.btnSend);
        groupMessage = (CheckBox)findViewById(R.id.checkBoxGroupEmail);
        message = (EditText)findViewById(R.id.editTextssms);

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


        OnCheckBoxItemClick();

    }

    /*
    method for claer inputs
     */


    public void onClick(View v){
        String smsText = message.getText().toString();
        if(!groupMessage.isChecked()) {
            String pnNo = communicationController.parentTPNo(StudentID);
            //Log.d("Send SMS", "SENT SUCCESSFULLY........................................................................." +StudentID+"....."+ pnNo);
            int sendStatus = communicationController.sendSMSMessage(pnNo, smsText) ;
            if ( sendStatus== 1) {
                Toast.makeText(SendSMSActivity.this, "Message Sent succesfully.", Toast.LENGTH_LONG).show();
                clearInterface();
            } else if(sendStatus == 0) {
                Toast.makeText(SendSMSActivity.this, "Message Sending failed.", Toast.LENGTH_LONG).show();
            }
            else if(sendStatus == 2) {
                Toast.makeText(SendSMSActivity.this, "Message Sent succesfully. But failed to add to your records", Toast.LENGTH_LONG).show();
                clearInterface();
            }
        }

        else{
            int FailureCount = communicationController.sendSMSForGroup(ClassID,smsText);

            if(FailureCount== 0){
                clearInterface();
                Toast.makeText(SendSMSActivity.this, "Messages are sent succesfully.", Toast.LENGTH_LONG).show();
            }
            else{
                clearInterface();
                Toast.makeText(SendSMSActivity.this, FailureCount+" Messages are not sent succesfully.", Toast.LENGTH_LONG).show();
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner)parent;
        // set action if selected spinner item is from class spinner
        if(spinner.getId() == R.id.spinnerClass){
            // check if message is group or not
            if(!groupMessage.isChecked()){

                // get selected class id
                ClassID = communicationController.getClassIDBySpinnerItemSelected(position);
                // get student list of the selected class
                studentList = communicationController.getStudentListByClassID(ClassID);

                ArrayList<String> students = new ArrayList<>();
                students.add("");
                for (int i=0;i< studentList.size();i++) {
                    students.add(studentList.get(i)[0][1]);
                }

                recipient = (Spinner) findViewById(R.id.spinnerRecipient);
                // Spinner click listener
                recipient.setOnItemSelectedListener(this);
                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,students );
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                recipient.setAdapter(dataAdapter);

            }
            else
            {
                ClassID = communicationController.getClassIDBySpinnerItemSelected(position);
            }
        }
        // set action if selected spinner item is from recipient spinner
        else if(spinner.getId() == R.id.spinnerRecipient){
            if(position!=0) {
                StudentID = Integer.parseInt(studentList.get(position-1)[0][0]);
            }
        }
    }


    public void OnCheckBoxItemClick(){

        groupMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipient.setSelection(0);
                recipient.setEnabled(!recipient.isEnabled());
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

    public void clearInterface(){
        recipient.setSelection(0);
        spinnerClass.setSelection(0);
        groupMessage.setChecked(false);
        message.setText("");

    }

}
