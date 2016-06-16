package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.R;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

import org.w3c.dom.Text;

/**
 * Created by Madhavi Ruwandika on 6/14/2016.
 */
public class AddPaymentActivityTest extends ActivityInstrumentationTestCase2<AddPaymentActivity> {

    AddPaymentActivity addPaymentActivity;
    private Spinner spinnerstd;
    private Spinner spinnercls;
    private Spinner month;
    private Button AddPayment;
    private Solo solo;

    public AddPaymentActivityTest(Class<AddPaymentActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {

        addPaymentActivity = getActivity();
        solo = new Solo(getInstrumentation(),getActivity());

        spinnercls = (Spinner) solo.getView(R.id.spinnerClass);
        spinnerstd = (Spinner) solo.getView(R.id.spinnerSName);
        month = (Spinner)solo.getView(R.id.spinnerFor_month);
        AddPayment = (Button) solo.getView(R.id.btnAdd);
    }

    public void testAddPaymentTest(){

        // check without selecting month
        solo.clickOnView(spinnercls);
        solo.clickOnView(solo.getView(TextView.class,1));
        solo.clickOnView(spinnerstd);
        solo.clickOnView(solo.getView(TextView.class,1));
        solo.clickOnView(AddPayment);
        assertTrue(solo.waitForText("Missing data.please check again"));

        // check without selecting student
        solo.clickOnView(spinnercls);
        solo.clickOnView(solo.getView(TextView.class, 1));
        solo.clickOnView(month);
        solo.clickOnView(solo.getView(TextView.class,1));
        solo.clickOnView(AddPayment);
        assertTrue(solo.waitForText("Missing data.please check again"));

        // check with selecting month and student
        solo.clickOnView(spinnercls);
        solo.clickOnView(solo.getView(TextView.class, 1));
        solo.clickOnView(spinnerstd);
        solo.clickOnView(solo.getView(TextView.class,1));
        solo.clickOnView(month);
        solo.clickOnView(solo.getView(TextView.class,1));
        solo.clickOnView(AddPayment);
        assertTrue(solo.waitForText("Missing data.please check again"));


    }
}