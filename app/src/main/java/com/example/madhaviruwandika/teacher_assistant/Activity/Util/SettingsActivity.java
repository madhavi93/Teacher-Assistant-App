package com.example.madhaviruwandika.teacher_assistant.Activity.Util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.HomeActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.MyProfileController;
import com.example.madhaviruwandika.teacher_assistant.R;

public class SettingsActivity extends BaseActivity {

    Button deleteClass;
    Button deleteStudent;
    Button reset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        OnDeleteClassButtonClickListner();
        OnDeleteStudentButtonClickListner();
        OnResetButtonClickListner();
    }

    public void OnDeleteClassButtonClickListner(){

        deleteStudent = (Button) findViewById(R.id.buttonDeleteClass);

        deleteStudent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.DeleteClassActivity");
                        startActivity(intent);
                    }
                }

        );

    }


    public void OnDeleteStudentButtonClickListner(){

        deleteClass = (Button) findViewById(R.id.buttonDeleteStudent);

        deleteClass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity.StudentActivity");
                        startActivity(intent);
                    }
                }

        );

    }

    public void OnResetButtonClickListner(){

        reset = (Button) findViewById(R.id.btnReset);
        final MyProfileController myProfileController = new MyProfileController(this);

        reset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case DialogInterface.BUTTON_POSITIVE:
                                        // call controller to reset application
                                        if(myProfileController.ResetApplication() == 1){
                                            Toast.makeText(SettingsActivity.this,"Applicarion is successfully Reset.",Toast.LENGTH_LONG).show();
                                        }
                                        else {
                                            Toast.makeText(SettingsActivity.this,"Failed to reset Application",Toast.LENGTH_LONG).show();
                                        }
                                        break;

                                    case DialogInterface.BUTTON_NEGATIVE:
                                        // nothing to do
                                        break;
                                }
                            }
                        };

                        AlertDialog.Builder builder = new AlertDialog.Builder(getWindow().getContext());
                        builder.setMessage("Are you sure that you want to RESET the app?").setPositiveButton("Yes", dialogClickListener)
                                .setNegativeButton("No", dialogClickListener).show();
                    }
                }

        );

    }

}
