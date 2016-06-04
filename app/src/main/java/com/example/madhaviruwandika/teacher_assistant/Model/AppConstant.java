package com.example.madhaviruwandika.teacher_assistant.Model;

import android.util.Log;

/**
 * Created by Madhavi Ruwandika on 5/30/2016.
 */
public class AppConstant {


    private static AppConstant instance;
    private static String reference = "";



    public AppConstant() {

    }

    public static AppConstant getInstance(){
        if (instance == null)
        {
            synchronized(AppConstant.class)
            {
                if (instance == null)
                {instance = new AppConstant();
                }
            }
        }
        return instance;

    }


    public static String getReference() {
        return reference;
    }
    public static void setReference(String reference) {
        AppConstant.reference = reference;
    }

    /*
    * *if AppConstant.iscontinuing_class()= true,then class is started.
    * if AppConstant.isMarkedAttendence()) = false ,then attendence are not marked
    */

    /*
        continuing class      mark attendence
        false             ->  false
        true              ->  true

    */
    private static boolean markedAttendence = false;
    private static boolean continuing_class = false;
    private static TutionClass ClassContinuing = null;
    private static String finishClassDate = "";

    public  boolean isMarkedAttendence() {
        return markedAttendence;
    }

    public  void setMarkedAttendence(boolean markedAttendence) {
        this.markedAttendence = markedAttendence;
    }

    public  boolean iscontinuing_class() {
        return continuing_class;
    }

    public void setcontinuing_class(boolean continuing_class) {
        this.continuing_class = continuing_class;
    }

    public  TutionClass getClassContinuing() {
        return ClassContinuing;
    }

    public void setClassContinuing(TutionClass classContinuing) {
        ClassContinuing = classContinuing;
    }

    public String getFinishClassDate() {
        return finishClassDate;
    }

    public void setFinishClassDate(String finishClassDate) {
        this.finishClassDate = finishClassDate;
    }
}
