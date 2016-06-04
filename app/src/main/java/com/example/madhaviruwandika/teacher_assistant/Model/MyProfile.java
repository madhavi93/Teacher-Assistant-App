package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/31/2016.
 */
public class MyProfile {

    private int Change_ID;
    private String Name;
    private String password;
    private String DateOFChange ;


    public MyProfile() {
    }

    public MyProfile(String name, String password, String dateOFChange) {
        Name = name;
        this.password = password;
        DateOFChange = dateOFChange;
    }

    public MyProfile(int change_ID, String name, String password, String dateOFChange) {
        Change_ID = change_ID;
        Name = name;
        this.password = password;
        DateOFChange = dateOFChange;
    }

    public int getChange_ID() {
        return Change_ID;
    }

    public void setChange_ID(int change_ID) {
        Change_ID = change_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateOFChange() {
        return DateOFChange;
    }

    public void setDateOFChange(String dateOFChange) {
        DateOFChange = dateOFChange;
    }

}
