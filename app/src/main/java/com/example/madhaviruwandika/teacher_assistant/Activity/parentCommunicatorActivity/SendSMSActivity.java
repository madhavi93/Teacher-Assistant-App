package com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.HomeActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.CommunicationController;
import com.example.madhaviruwandika.teacher_assistant.R;

public class SendSMSActivity extends AppCompatActivity {

    EditText recipient;
    EditText sms;
    Button sendSMS;

    CommunicationController communicationController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        recipient = (EditText) findViewById(R.id.editTextpn);
        sms = (EditText)findViewById(R.id.editTextssms);
        sendSMS =(Button)findViewById(R.id.btnSend);

        communicationController = new CommunicationController();
    }

    public void onClick(View v){

        String pnNo = recipient.getText().toString();
        String sms = recipient.getText().toString();

        if(communicationController.sendSMSMessage(pnNo,sms)==1){
            Toast.makeText(SendSMSActivity.this, "Message Send succesfully.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(SendSMSActivity.this, "Message Sending failed.", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

}
