package com.example.madhaviruwandika.teacher_assistant.Activity;

import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.AddClass;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class AddClassTest extends ActivityInstrumentationTestCase2<AddClass> {

    AddClass addClassActivity;

    private Solo solo;
    private EditText className;
    private TextView time;
    private TextView sdate;
    private Spinner day;
    private TextView edate;
    private EditText fee;
    private ImageButton From;
    private ImageButton To;
    private ImageButton stsrtD;
    private ImageButton endD;
    private Button add;

    public AddClassTest() {
        super(AddClass.class);
    }

    public void setUp() throws Exception {
        addClassActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
        this.className = (EditText) solo.getView(R.id.editTextname);
        this.time = (TextView)solo.getView(R.id.editTextTime);
        this.sdate = (TextView) solo.getView(R.id.StartDate);
        this.edate = (TextView)solo.getView(R.id.EndDate);
        this.day = (Spinner)solo.getView(R.id.spinnerDay);
        this.fee = (EditText)solo.getView(R.id.editTextfee);
        this.From = (ImageButton)solo.getView(R.id.imageButtonFrom);
        this.To = (ImageButton) solo.getView(R.id.imageButtonTo);
        this.stsrtD = (ImageButton) solo.getView(R.id.imageButtonSdate);
        this.endD = (ImageButton) solo.getView(R.id.imageButtonEndDate);
        this.add = (Button)solo.getView(R.id.btnDelete);
    }


    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        try {
            this.solo.finalize();
        }
        catch (Throwable e){
            e.printStackTrace();
        }
        getActivity().finish();
        super.tearDown();

    }
    public void testaddValuesTestCase(){

        this.solo.enterText(className, "Maths-8");
        this.solo.clickOnView(stsrtD);
        this.solo.setDatePicker(0, 2016, 11, 10);
        this.solo.clickOnText("OK");
        this.solo.clickOnView(endD);
        this.solo.setDatePicker(0, 2017, 11, 10);
        this.solo.clickOnText("OK");
        this.solo.clickOnView(From);
        this.solo.setTimePicker(0, 13, 30);
        this.solo.clickOnText("OK");
        this.solo.clickOnView(To);
        this.solo.setTimePicker(0, 15, 30);
        this.solo.clickOnText("OK");
        this.solo.clickOnView(day);
        this.solo.clickOnView(solo.getView(TextView.class, 2));
        this.solo.enterText(fee, String.valueOf(500));

        this.solo.clickOnView(add);
        assertTrue(solo.waitForText("Class Details are succesfully added"));


    }


}