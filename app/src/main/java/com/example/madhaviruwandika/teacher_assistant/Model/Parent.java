package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 4/15/2016.
 */
public class Parent
{
    private String name;
    private int Tp_no;
    private String Email;

    public Parent(String name, int tp_no, String email) {
        this.name = name;
        Tp_no = tp_no;
        Email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTp_no() {
        return Tp_no;
    }

    public void setTp_no(int tp_no) {
        Tp_no = tp_no;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
