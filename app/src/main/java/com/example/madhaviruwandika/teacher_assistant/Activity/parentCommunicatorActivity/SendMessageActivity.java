package com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.addPaymentFragment;
import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.markAttendenceFragment;
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

        return super.onOptionsItemSelected(item);
    }


}
