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

    public void testIsValidDigits() throws Exception{
        assertEquals(true,InputValidator.isValidDigits("234"));
        assertEquals(false,InputValidator.isValidDigits("123.45"));
    }

    public void testIsValidDate() throws Exception{
        assertEquals(true,InputValidator.isValidDate("02-03-1992"));
        assertEquals(false,InputValidator.isValidDate("34-04-2018"));
        assertEquals(false,InputValidator.isValidDate("04-14-1993"));
        assertEquals(false,InputValidator.isValidDate("12/04/2018"));
        assertEquals(true,InputValidator.isValidDate("23-12-2020"));
        assertEquals(false,InputValidator.isValidDate("34-04-2018"));
    }

    public void testIsValidEmail() throws Exception{
        assertEquals(true,InputValidator.isValidEmail("m@gmail.com"));
        assertEquals(true,InputValidator.isValidEmail("mandhavi.ruwandika@gmail.com"));
        assertEquals(true,InputValidator.isValidEmail("wi@yahoo.com"));
        assertEquals(false,InputValidator.isValidEmail("mmmm.com"));
        assertEquals(true,InputValidator.isValidEmail("madhavi.13@cse.mrt.ac.lk"));
    }

    public void testClassFeeValidator() throws Exception{
        assertEquals(true,InputValidator.ClassFeeValidator("50.0"));
        assertEquals(true,InputValidator.ClassFeeValidator("100"));
        assertEquals(false,InputValidator.ClassFeeValidator("e345-"));
        assertEquals(true,InputValidator.ClassFeeValidator("0.50"));
        assertEquals(true,InputValidator.ClassFeeValidator("400f"));
    }

    public void testisDateisPast() throws Exception{
        assertEquals(true,InputValidator.isDateisPast("02-09-1999"));
        assertEquals(true,InputValidator.isDateisPast("12-12-2015"));
        assertEquals(false,InputValidator.isDateisPast("22-02-2030"));
    }


}