package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 3/27/2016.
 */
public class Student {

    private int s_id;
    private String name;
    private int classID;
    private String doB;
    private String address;

    public Student(int s_id,String name,int classID,String Dob, String address){
        this.s_id = s_id;
        this.name = name;
        this.classID = classID;
        this.address = address;
        this.doB = Dob;
    }

    public Student(String name,String Dob, String address,int classID){
        this.name = name;
        this.address = address;
        this.doB = Dob;
        this.classID = classID;
    }

    public Student() {

    }

    public int getS_id() { return s_id; }

    public void setS_id(int s_id) { this.s_id = s_id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClassname() {
        return classID;
    }

    public void setClassname(int classID) {
        this.classID = classID;
    }

    public String getDoB() { return doB; }

    public void setDoB(String doB) { this.doB = doB; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }
}
