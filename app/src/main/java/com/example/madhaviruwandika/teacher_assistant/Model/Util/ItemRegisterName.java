package com.example.madhaviruwandika.teacher_assistant.Model.Util;

/**
 * Created by Madhavi Ruwandika on 5/1/2016.
 */
public class ItemRegisterName {

    private int SID;
    private String name;
    private Boolean attendence;

    public ItemRegisterName(int SID, String name, Boolean attendence) {
        this.setSID(SID);
        this.setName(name);
        this.setAttendence(attendence);
    }

    public ItemRegisterName( String name, Boolean attendence) {

        this.setName(name);
        this.setAttendence(attendence);
    }


    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAttendence() {
        return attendence;
    }

    public void setAttendence(Boolean attendence) {
        this.attendence = attendence;
    }
}
