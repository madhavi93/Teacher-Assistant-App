package com.example.madhaviruwandika.teacher_assistant.Model;

import java.util.Date;

/**
 * Created by Madhavi Ruwandika on 5/1/2016.
 */
public class TutionClass {

    private int ClassID;
    private String ClassName;
    private String StartDate;
    private String EndDate;
    private String Day;
    private String Time;
    private double fee;

    public TutionClass() {
    }

    public TutionClass( String className, String startDate, String day, String time, String  endDate,double fee) {
        this.fee = fee;
        setClassName(className);
        setStartDate(startDate);
        setDay(day);
        setTime(time);
        setEndDate(endDate);
    }

    public TutionClass(int classID, String className, String startDate, String day, String time, String  endDate) {
        setClassID(classID);
        setClassName(className);
        setStartDate(startDate);
        setDay(day);
        setTime(time);
        setEndDate(endDate);
    }

    public TutionClass(String className, String time, String day, String endDate, String startDate) {
        ClassName = className;
        Time = time;
        Day = day;
        EndDate = endDate;
        StartDate = startDate;
    }

    public TutionClass(String className, String startDate, String day, String time) {
        ClassName = className;
        StartDate = startDate;
        Day = day;
        Time = time;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getDay() {
        return Day;
    }

    public void setDay(String day) {
        Day = day;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}

