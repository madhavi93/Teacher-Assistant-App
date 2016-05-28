package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/18/2016.
 */
public class Attendence {

    private int Attendance_id ;
    private int S_id  ;
    private  int Class_Id;
    private String DateOPA;
    private boolean attendenceState;

    public Attendence() {
    }

    public Attendence(int s_id, int class_Id, String dateOPA,boolean attendenceState) {
        S_id = s_id;
        Class_Id = class_Id;
        DateOPA = dateOPA;
        this.setAttendenceState(attendenceState);
    }

    public Attendence(int attendance_id, int s_id, int class_Id, String dateOPA,boolean attendenceState) {
        Attendance_id = attendance_id;
        S_id = s_id;
        Class_Id = class_Id;
        DateOPA = dateOPA;
        this.setAttendenceState(attendenceState);
    }

    public int getAttendance_id() {
        return Attendance_id;
    }

    public void setAttendance_id(int attendance_id) {
        Attendance_id = attendance_id;
    }

    public int getS_id() {
        return S_id;
    }

    public void setS_id(int s_id) {
        S_id = s_id;
    }

    public int getClass_Id() {
        return Class_Id;
    }

    public void setClass_Id(int class_Id) {
        Class_Id = class_Id;
    }

    public String getDateOPA() {
        return DateOPA;
    }

    public void setDateOPA(String dateOPA) {
        DateOPA = dateOPA;
    }

    public boolean isAttendenceState() {
        return attendenceState;
    }

    public void setAttendenceState(boolean attendenceState) {
        this.attendenceState = attendenceState;
    }
}
