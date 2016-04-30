package com.example.madhaviruwandika.teacher_assistant.Activity.StudentActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import android.view.MotionEvent;

import static android.view.GestureDetector.*;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.View.addStudentView;

import com.example.madhaviruwandika.teacher_assistant.R;

public class StudentActivity extends BaseActivity implements addStudentView, OnGestureListener,OnDoubleTapListener  {


    StudentController sdc;

    EditText Ename;
    EditText Ecname;
    EditText EAddress;
    EditText EDoB;
    EditText pName;
    EditText pTP_no;
    EditText pEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        super.onCreate(savedInstanceState);

        sdc = new StudentController(this);

    }

    public void onClick(View v){

        addStudentDetails();
        Ecname.setText("");
        Ename.setText("");

    }


    @Override
    public void addStudentDetails() {

        // get values at the form
        Ename = (EditText) findViewById(R.id.sname);
        Ecname = (EditText) findViewById(R.id.cname);
        EAddress = (EditText)findViewById(R.id.cAddress);
       // EDoB = (EditText)findViewById(R.id.cDoB);
        pName = (EditText) findViewById(R.id.cParentName);
        pTP_no = (EditText) findViewById(R.id.cTpNo);
        pEmail = (EditText) findViewById(R.id.cEmail);

        String name = Ename.getText().toString();
        String  cname = Ecname.getText().toString();
        String address = EAddress.getText().toString();
        String DoB = EDoB.getText().toString();
        String pname = pName.getText().toString();
        int  pTP = Integer.parseInt(pTP_no.getText().toString());
        String email = pEmail.getText().toString();

        // pass values for add to database
        sdc.addStudent(name, DoB, address,cname);
        sdc.addParent(pname,pTP,email);

    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
