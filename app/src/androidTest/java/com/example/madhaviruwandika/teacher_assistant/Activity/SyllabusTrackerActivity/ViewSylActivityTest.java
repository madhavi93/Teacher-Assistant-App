package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by Madhavi Ruwandika on 6/14/2016.
 */
public class ViewSylActivityTest extends ActivityInstrumentationTestCase2<ViewSylActivity> {


    private Solo solo ;
    Button back;

    public ViewSylActivityTest(Class<ViewSylActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        solo= new Solo(getInstrumentation(),getActivity());
        back = (Button) solo.getView(R.id.buttonBack);
    }

    public void testBackButtonNevigation(){

        getActivity().myBundle.putInt("LoadFrom",1);
        solo.clickOnButton("<<Back");
        solo.assertCurrentActivity("", MyProgressActivity.class);

        getActivity().myBundle.putInt("LoadFrom", 2);
        getActivity().classID = 1;
        getActivity().myBundle.putString("ClassName","Maths-00");
        solo.clickOnButton("<<Back");
        solo.assertCurrentActivity("", AddSylActivity.class);

    }


}