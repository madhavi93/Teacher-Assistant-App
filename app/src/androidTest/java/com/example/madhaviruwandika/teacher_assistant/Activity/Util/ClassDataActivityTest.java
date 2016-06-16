package com.example.madhaviruwandika.teacher_assistant.Activity.Util;

import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;


import com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity.StudentActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity.UpdateStudentActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.AddClass;
import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.Add_ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.UpdateClassDActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.ViewExtraClassActivity;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by Madhavi Ruwandika on 6/13/2016.
 */
public class ClassDataActivityTest extends ActivityInstrumentationTestCase2<ClassDataActivity> {


    ClassDataActivity classDataActivity;
    private Solo solo ;


    public ClassDataActivityTest() {
        super(ClassDataActivity.class);
    }


    public void setUp() throws Exception {
        classDataActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testButtonNevigationTestCase(){

        solo.clickOnButton("     Add Student     ");
        solo.assertCurrentActivity("", StudentActivity.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnButton("     Add Class     ");
        solo.assertCurrentActivity("", AddClass.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnButton("     Add Extra Class     ");
        solo.assertCurrentActivity("", Add_ExtraClass.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnButton("UPDATE CLASS DETAIL");
        solo.assertCurrentActivity("", UpdateClassDActivity.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnButton("Update Student Details");
        solo.assertCurrentActivity("", UpdateStudentActivity.class);
        solo.clickOnActionBarHomeButton();

        solo.clickOnButton(" View/Update Extra Classes ");
        solo.assertCurrentActivity("", ViewExtraClassActivity.class);
        solo.clickOnActionBarHomeButton();

    }




}