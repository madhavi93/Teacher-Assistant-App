package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.SettingsActivity;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by Madhavi Ruwandika on 6/14/2016.
 */
public class MyProgressActivityTest extends ActivityInstrumentationTestCase2<MyProgressActivity> {

    MyProgressActivity myProgressActivity;
    Solo solo;

    public MyProgressActivityTest() {
        super(MyProgressActivity.class);
    }

    public void setUp() throws Exception {
        myProgressActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testCheckNavigationONButton(){

        // check navigation of button clicks
        solo.clickOnView((Spinner)solo.getView(R.id.spinner));
        this.solo.clickOnView(solo.getView(TextView.class, 1));
        solo.clickOnButton("    View Syllabus    ");
        solo.assertCurrentActivity("", ViewSylActivity.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnView((Spinner) solo.getView(R.id.spinner));
        this.solo.clickOnView(solo.getView(TextView.class, 1));
        solo.clickOnButton("Add Syllabus ");
        solo.assertCurrentActivity("", AddSylActivity.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnView((Spinner) solo.getView(R.id.spinner));
        this.solo.clickOnView(solo.getView(TextView.class, 1));
        solo.clickOnButton("My Progress");
        solo.assertCurrentActivity("",MyWorkActivity.class);

    }

    public void testButtonClicks(){

        // check without selecting class
        solo.clickOnButton("    View Syllabus    ");
        assertTrue(solo.waitForText(" Please select class ."));

        solo.clickOnButton("Add Syllabus ");
        assertTrue(solo.waitForText(" Please select class ."));

        solo.clickOnButton("My Progress");
        assertTrue(solo.waitForText(" Please select class ."));

    }





}