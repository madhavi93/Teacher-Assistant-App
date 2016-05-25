package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/2/2016.
 */
public class ExtraClass {

    private int ExtraClassID;
    private int ClassID;
    private String date;
    private String time;
    private String classType;

    public ExtraClass() {
    }

    public ExtraClass(int extraClassID, int classID, String date, String time, String classType) {
        ExtraClassID = extraClassID;
        ClassID = classID;
        this.date = date;
        this.time = time;
        this.classType = classType;
    }

    public ExtraClass(int classID, String date, String time, String classType) {
        ClassID = classID;
        this.date = date;
        this.time = time;
        this.classType = classType;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }


    public int getExtraClassID() {
        return ExtraClassID;
    }

    public void setExtraClassID(int extraClassID) {
        ExtraClassID = extraClassID;
    }
}
