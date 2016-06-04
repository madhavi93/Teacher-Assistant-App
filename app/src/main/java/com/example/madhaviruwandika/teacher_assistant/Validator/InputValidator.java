package com.example.madhaviruwandika.teacher_assistant.Validator;

import android.renderscript.Short4;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Model.StartOfClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

/**
 * Created by Madhavi Ruwandika on 5/1/2016.
 */
public class InputValidator {

    public  static boolean isValidLetters(String txt) {

        String regx = "^[a-zA-Z\\s\b(.)]*$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        boolean b = matcher.find();

        boolean a=false;
        if(txt != "" || txt != null){
            a = true;
        }
        return (b&a);


    }

    public static boolean isValidDigits(String txt) {

        String regx = "^[0-9\b]*$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        boolean b = matcher.find();
        boolean a=false;
        if(txt != "" || txt != null){
            a = true;
        }
        return (b&a);

    }

    public static boolean isValidDate(String txt) {
        if(txt == null){
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            //if not valid, it will throw ParseException
            Log.d("MyActivity","EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            Date date = sdf.parse(txt);
            System.out.println(date);
            return true;

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean isTimeperiodValid(String txt){

        String [] patern = {"(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)(\\s)(1[012]|[1-9]):[0-5][0-9](\\s)?(?i)(am|pm)"};
        for (String p : patern ){
            Pattern pattern = Pattern.compile(p);
            Matcher matcher = pattern.matcher(txt);
            if (matcher.find()){
                return true;
            }
        }
        return false;

    }

    public static boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        boolean b= matcher.matches();
        boolean a=false;
        if(email != "" || email != null){
            a = true;
        }
        return (b&a);

    }
    public static boolean ClassFeeValidator(String fee){

        try {
            Double val = Double.parseDouble(fee);
            if(val < 0){
                return false;
            }
            else {
                return true;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    public boolean  checkReEnteredPassword(String newPassward,String reEnter){
        if(newPassward.equals(reEnter)){
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isDateisPast(String date)
    {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            Date currntDate = dateFormat.parse(dateFormat.format(cal.getTime()).toString());
            Date newDate = dateFormat.parse(date);
            if(currntDate.after(newDate) || currntDate.equals(newDate)){
                return true;
            }
            else return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

}
