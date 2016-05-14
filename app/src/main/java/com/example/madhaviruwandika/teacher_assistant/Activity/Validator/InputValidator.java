package com.example.madhaviruwandika.teacher_assistant.Activity.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Madhavi Ruwandika on 5/1/2016.
 */
public class InputValidator {

    public  static boolean isValidLetters(String txt) {
        String regx = "^[a-zA-Z\\s\b(.)]*$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        boolean b = matcher.find();
        return b;
    }

    public static boolean isValidDigits(String txt) {
        String regx = "^[0-9\b]*$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        boolean b = matcher.find();
        return b;
    }

    public static boolean isValidDate(String txt) {

        String[] patern = {"\\d{4}[/.-][0-2]?[0-2][/.-][0-9]?[0-3]","[0-4]?[0-9][/.-][0-2]?[0-3][/.-]\\d{4}"};
        for (String p : patern ){
            Pattern pattern = Pattern.compile(p);
            Matcher matcher = pattern.matcher(txt);
            if (matcher.find()){
                return true;
            }
        }

        return false;
       /*
        String regx = "^[0-9\b(-)]*$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        boolean b = matcher.find();
        return b;
        */
    }

    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
