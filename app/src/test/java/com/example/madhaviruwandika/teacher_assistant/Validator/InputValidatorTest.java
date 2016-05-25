package com.example.madhaviruwandika.teacher_assistant.Validator;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import java.util.regex.Pattern;


/**
 * Created by Madhavi Ruwandika on 5/25/2016.
 */
public class InputValidatorTest extends TestCase {


    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }


    @Test
    public void letterValidator_correctLetters_returnTrue(){

        assertEquals(true, InputValidator.isValidLetters("WE"));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


}