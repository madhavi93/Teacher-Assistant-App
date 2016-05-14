package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;

import java.net.URI;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class CommunicationController {


    public int sendSMSMessage(String phoneNo,String message ) {

        Log.i("Send SMS", "");
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

            Log.d("Send SMS", "SENT SUCCESSFULLY.........................................................................");
            return 1;
        }

        catch (Exception e) {
            e.printStackTrace();
            Log.d("Send SMS", "SENDING FAILED............................................................................");
            return 0;
        }
    }

    public int sendEmailMessage(){



        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");



    }
}
