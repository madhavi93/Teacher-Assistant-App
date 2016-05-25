package com.example.madhaviruwandika.teacher_assistant.Validator;

import junit.framework.TestCase;

/**
 * Created by Madhavi Ruwandika on 5/25/2016.
 */
public class InputValidatorTest extends TestCase {

    public void testIsValidLetters() throws Exception {
        assertEquals(true,InputValidator.isValidLetters("WE"));
        assertEquals(false,InputValidator.isValidLetters("2ftrgrtgr"));
    }
}