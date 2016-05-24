package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity.StudentProgressActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.IntelligenceSyllabusController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.List;
import java.util.Map;

public class ViewSylActivity extends AppCompatActivity {

    private TableLayout logsTableLayout;
    private TextView Tclass;
    IntelligenceSyllabusController intelligenceSyllabusController;
    List<Map<String,String>> syllabus;
    Bundle myBundle;

    int classID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_syl);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set home and back buttom in toolbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Tclass = (TextView)findViewById(R.id.textViewClass);
        logsTableLayout = (TableLayout) findViewById(R.id.Syllbus);
        intelligenceSyllabusController = new IntelligenceSyllabusController(this);
        myBundle = getIntent().getExtras();

        classID = myBundle.getInt("ClassID");
        Tclass.setText(myBundle.getString("ClassName") );
        syllabus = intelligenceSyllabusController.getSyllabus(classID);

        for (Map<String,String> l : syllabus){

            TableRow tr = new TableRow(this);

            TextView unit = new TextView(this);
            unit.setText(String.valueOf(" " + l.get("unitNo")));
            tr.addView(unit);

            TextView lesson_no = new TextView(this);
            lesson_no.setText(l.get("lessonNo"));
            tr.addView(lesson_no);

            TextView lessonN = new TextView(this);
            lessonN.setText(l.get("Lesson"));
            Log.d("MY", "%%%%%%%%%%%%%%%%%%%%%%%%%" + l.get("Lesson") + "%%%%%%%%%%%%%%%%%%%%%%%%");
            tr.addView(lessonN);

            TextView time = new TextView(this);
            time.setText(l.get("totaltimeSupposedToSpend"));
            tr.addView(time);

            TextView amount = new TextView(this);
            amount.setText(l.get("amountCovered"));
            tr.addView(amount);

            logsTableLayout.addView(tr);
        }
        backButtonClick();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent myIntent = new Intent(getApplicationContext(), MyProgressActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public void backButtonClick(){
        Button btnBack = (Button)findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backViewID = myBundle.getInt("LoadFrom");
                if(backViewID == 1) {

                    Intent myIntent = new Intent(getApplicationContext(), MyProgressActivity.class);
                    startActivityForResult(myIntent, 0);
                }
                else if(backViewID ==2){

                    Intent intent = new Intent(".Activity.SyllabusTrackerActivity.AddSylActivity");
                    intent.putExtra("ClassID",classID );
                    intent.putExtra("ClassName", myBundle.getString("ClassName"));
                    startActivity(intent);
                }
            }
        });

    }

}
