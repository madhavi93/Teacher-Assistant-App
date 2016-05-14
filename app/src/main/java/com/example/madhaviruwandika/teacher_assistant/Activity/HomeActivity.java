package com.example.madhaviruwandika.teacher_assistant.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;

import com.example.madhaviruwandika.teacher_assistant.Database.DBCreator;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements AdapterView.OnItemClickListener{


    StudentController sdc;

    private static Button btnhome;
    private static Button btnmsg;
    private static Button btnSyl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d("11111111111111111", "3");
        //create database
        DBCreator dbCreator= new DBCreator(this);
        Log.d("11111111111111111", "3");

        super.onCreate(savedInstanceState);

        sdc = new StudentController(this);

        //OnHomeButtonClickListner();
        //OnmsgButtonClickListner();
        //OnsylButtonClickListner();

        LoadSelection(0);


        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    private void LoadSelection(int i){
        if(i== 1){
            Intent intent = new Intent(".Activity.classFagments.ClassManegeActivity");
            startActivity(intent);
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


/*
     public void OnHomeButtonClickListner(){

        btnhome = (Button) findViewById(R.id.btn_home);

        btnhome.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity");
                        startActivity(intent);
                    }
                }

        );

    }


    public void OnmsgButtonClickListner(){

        btnmsg = (Button) findViewById(R.id.btnMSG);

        btnmsg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity.SendMessageActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnsylButtonClickListner(){

        btnSyl = (Button) findViewById(R.id.btnsyl);

        btnSyl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Activity.SyllabusTrackerActivity.AddSylActivity");
                        startActivity(intent);
                    }
                }

        );

    }

*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch(position){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
}

