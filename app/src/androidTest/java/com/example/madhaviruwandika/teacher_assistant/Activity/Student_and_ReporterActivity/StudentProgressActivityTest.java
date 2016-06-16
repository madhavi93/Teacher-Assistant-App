package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.AddExamRecordsActivity;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by Madhavi Ruwandika on 6/14/2016.
 */
public class StudentProgressActivityTest extends ActivityInstrumentationTestCase2<StudentProgressActivity> {

    StudentProgressActivity studentProgressActivity;
    private Solo solo;

    Button addExam;
    Button addPerfomance;
    Button seeOverallPerfomance;
    Button seeIndividualPerfomance;

    public StudentProgressActivityTest(Class<StudentProgressActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        studentProgressActivity = getActivity();
        solo = new Solo(getInstrumentation(),getActivity());

        addExam = (Button) solo.getView(R.id.btnAddExam);
        addPerfomance = (Button)solo.getView(R.id.btnAddPerfomance);
        seeIndividualPerfomance = (Button)solo.getView(R.id.btnSeeStudentPerfomance);
        seeOverallPerfomance =(Button)solo.getView(R.id.btnSeePerfomanceClass);
    }

    public void testCheckButtonClickNavigations(){

        // check navigation of button clicks
        solo.clickOnButton("Add Exams");
        solo.assertCurrentActivity("", AddExamRecordsActivity.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnButton("   Add Performance   ");
        solo.assertCurrentActivity("", AddPerformanceActivity.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnButton("SEE OVERALL CLASS PERFOMANCE");
        solo.assertCurrentActivity("", AddPerformanceActivity.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnButton("SEE INDIVIDUAL STUDENT PERFOMANCE");
        solo.assertCurrentActivity("", AddPerformanceActivity.class);
        solo.clickOnActionBarHomeButton();

    }




}