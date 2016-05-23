package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/15/2016.
 */
public class Exam {

    private int ExamID;
    private int ClassID;
    private String date;
    private String Etype;
    private String lesson;

    public Exam(int classID, String date, String etype,String lesson) {
        ClassID = classID;
        this.date = date;
        Etype = etype;
        this.lesson = lesson;
    }

    public Exam(int examID, String etype, String date, int classID,String lesson) {
        ExamID = examID;
        Etype = etype;
        this.date = date;
        ClassID = classID;
        this.lesson = lesson;
    }

    public Exam() {
    }

    public int getExamID() {
        return ExamID;
    }

    public void setExamID(int examID) {
        ExamID = examID;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtype() {
        return Etype;
    }

    public void setEtype(String etype) {
        Etype = etype;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
