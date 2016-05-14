package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 4/15/2016.
 */
public class Parent
{

    private int SID;
    private String name;
    private String Tp_no;
    private String Email;

    public Parent(int SID,String name, String tp_no, String email) {
        this.SID = SID;
        this.name = name;
        Tp_no = tp_no;
        Email = email;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getTp_no() {
        return Tp_no;
    }

    public void setTp_no(String tp_no) {
        Tp_no = tp_no;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getSID() {return SID;}
}
