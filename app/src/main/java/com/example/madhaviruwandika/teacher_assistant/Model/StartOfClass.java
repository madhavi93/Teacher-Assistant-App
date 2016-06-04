package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/31/2016.
 */
public class StartOfClass {

    private int ClassID ;
    private String Date;
    private String Start_Time;
    private String End_Time;

    public StartOfClass() {
    }

    public StartOfClass(String date, int classID, String start_Time, String end_Time) {
        setDate(date);
        setClassID(classID);
        setStart_Time(start_Time);
        setEnd_Time(end_Time);
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(String start_Time) {
        Start_Time = start_Time;
    }

    public String getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(String end_Time) {
        End_Time = end_Time;
    }
}
