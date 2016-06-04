package com.example.madhaviruwandika.teacher_assistant.Activity.Util;

import android.app.FragmentManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.madhaviruwandika.teacher_assistant.Activity.HomeActivity;
import com.example.madhaviruwandika.teacher_assistant.R;

public class MyProfileActivity extends AppCompatActivity {

    int fragmentID = 0;
    Button btnChange;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set home button and back arrow to toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        btnChange = (Button)findViewById(R.id.buttonChange);
        frameLayout = (FrameLayout)findViewById(R.id.myprofile);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.myprofile,  new MyProfileDataFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();




    }

    public void changeProfileButtonClick(View v){

        Fragment fragment;
                if(fragmentID == 0) {
                    fragmentID = 1;
                    btnChange.setText("Back");
                    fragment= new ChangeMyProfileFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.myprofile, fragment);
                    View container = findViewById(R.id.myprofile);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                else if (fragmentID == 1){
                    fragmentID = 0;
                    btnChange.setText("CHANGE MY PROFILE");
                    fragment = new MyProfileDataFragment();
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.myprofile,fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }


}
