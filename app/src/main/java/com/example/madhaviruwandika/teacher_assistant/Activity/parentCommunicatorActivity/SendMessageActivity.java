package com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.madhaviruwandika.teacher_assistant.Activity.HomeActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.R;


public class SendMessageActivity extends BaseActivity {

    ImageButton sendSms;
    ImageButton sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_send_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        SendSMSButtonClickListner();
        SendEmailButtonClickListner();

    }


    public void SendSMSButtonClickListner(){

        sendSms = (ImageButton)findViewById(R.id.imageButtonSendSMS);
        sendSms.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity.SendSMSActivity");
                        startActivity(intent);
                    }

                }

        );

    }

    public void SendEmailButtonClickListner(){

        sendEmail = (ImageButton)findViewById(R.id.imageButtonSendEmail);
        sendEmail.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity.SendEmailActivity");
                        startActivity(intent);
                    }

                }

        );

    }
    

}
