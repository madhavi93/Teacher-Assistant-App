package com.example.madhaviruwandika.teacher_assistant.Activity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity.StudentProgressActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.MyProgressActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.MyProfileActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.Util.SettingsActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.classActivity.TodaysClassActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity.SendMessageActivity;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.robotium.solo.Solo;

import junit.framework.TestCase;

/**
 * Created by Madhavi Ruwandika on 6/13/2016.
 */
public class HomeActivityTest extends ActivityInstrumentationTestCase2<HomeActivity> {
    private Solo solo;
    private ListView newList;
    HomeActivity homeActivity;

    public HomeActivityTest() {
        super(HomeActivity.class);
    }


    public void setUp() throws Exception {
        homeActivity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
        newList = (ListView)solo.getView(R.id.drawer_list);
    }

    public void testListItemClicking(){

        solo.clickOnActionBarHomeButton();
        solo.clickInList(1);
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("", MyProfileActivity.class);
        solo.clickOnActionBarHomeButton();
        solo.assertCurrentActivity("", HomeActivity.class);

        solo.clickOnActionBarHomeButton();
        solo.clickInList(2);
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("", TodaysClassActivity.class);

        solo.clickOnActionBarHomeButton();
        solo.clickInList(3);
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("", MyProgressActivity.class);

        solo.clickOnActionBarHomeButton();
        solo.clickInList(4);
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("", StudentProgressActivity.class);

        solo.clickOnActionBarHomeButton();
        solo.clickInList(5);
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("", SendMessageActivity.class);

        solo.clickOnActionBarHomeButton();
        solo.clickInList(6);
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("", ClassDataActivity.class);

        solo.clickOnActionBarHomeButton();
        solo.clickInList(7);
        solo.sleep(1000); // give it time to change activity
        solo.assertCurrentActivity("", SettingsActivity.class);

    }

}