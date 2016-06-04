package com.example.madhaviruwandika.teacher_assistant.Model;

import junit.framework.TestCase;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Madhavi Ruwandika on 5/25/2016.
 */
public class Syllabus_InteligenceTest extends TestCase {


    Syllabus_Inteligence syllabus_inteligence;
    DateFormat dateFormat;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        syllabus_inteligence = Syllabus_Inteligence.getInstance();
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    }

    public void testGetWorkingDaysBetweenTwoDates() throws Exception {
        assertEquals(3,syllabus_inteligence.getWorkingDaysBetweenTwoDates(dateFormat.parse("01-05-2016"),dateFormat.parse("22-05-2016"),"Monday"));
        assertEquals(5,syllabus_inteligence.getWorkingDaysBetweenTwoDates(dateFormat.parse("02-05-2016"),dateFormat.parse("30-05-2016"),"Monday"));
        assertEquals(0,syllabus_inteligence.getWorkingDaysBetweenTwoDates(dateFormat.parse("05-05-2016"),dateFormat.parse("06-05-2016"),"Monday"));
        assertEquals(5,syllabus_inteligence.getWorkingDaysBetweenTwoDates(dateFormat.parse("30-05-2016"),dateFormat.parse("02-05-2016"),"Monday"));
    }

    public void testFindDay() throws Exception {
        assertEquals(Calendar.MONDAY,syllabus_inteligence.findDay("Monday"));
        assertEquals(-1,syllabus_inteligence.findDay("gffddz"));
        assertEquals(Calendar.SUNDAY,syllabus_inteligence.findDay("Sunday"));
    }

    public void testGetTimePeriod() throws Exception {
        assertEquals(120,syllabus_inteligence.getTimePeriod("02.30pm","4.30pm"));
        assertEquals(0,syllabus_inteligence.getTimePeriod("14.30","15.30"));
        assertEquals(0,syllabus_inteligence.getTimePeriod("13.00am","03.00pm"));
    }
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


}