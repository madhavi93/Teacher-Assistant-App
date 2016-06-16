package com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.madhaviruwandika.teacher_assistant.R;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by Madhavi Ruwandika on 6/14/2016.
 */
public class SendEmailActivityTest extends ActivityInstrumentationTestCase2<SendEmailActivity> {

    private Solo solo;
    private CheckBox checkBox;
    private Button addPerfomanceReport;

    public SendEmailActivityTest(Class<SendEmailActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(),getActivity());
        checkBox = (CheckBox)solo.getView(R.id.checkBoxGroupEmail);
        addPerfomanceReport = (Button) solo.getView(R.id.btnAddReport);
    }

    public void testCheckBoxClicked(){

        solo.clickOnView(checkBox);
        assertEquals(true,checkBox.isChecked());

        solo.clickOnView(checkBox);
        assertEquals(false,checkBox.isChecked());

    }

    public void testAddPerfomanceButtonClick(){

        solo.clickOnView(addPerfomanceReport);
        assertEquals(true,getActivity().isReportAdded);

    }



}