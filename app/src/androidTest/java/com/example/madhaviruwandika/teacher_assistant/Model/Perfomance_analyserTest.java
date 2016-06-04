package com.example.madhaviruwandika.teacher_assistant.Model;

import junit.framework.TestCase;

/**
 * Created by Madhavi Ruwandika on 5/30/2016.
 */
public class Perfomance_analyserTest extends TestCase {

    private Perfomance_analyser perfomance_analyser;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        perfomance_analyser = new Perfomance_analyser();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetMaxAndMinOfTheExam() throws Exception {


    }

    public void testAttendenceState() throws Exception {
        assertEquals("Very good Attendence",perfomance_analyser.AttendenceState(1,1));
        assertEquals("Very poor attendence.", perfomance_analyser.AttendenceState(100, 30));
        assertEquals("Very good Attendence",perfomance_analyser.AttendenceState(100,100));
    }
}