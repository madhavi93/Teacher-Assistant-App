package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.R;

public class AddClass extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add_class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        super.onCreate(savedInstanceState);

    }

}
