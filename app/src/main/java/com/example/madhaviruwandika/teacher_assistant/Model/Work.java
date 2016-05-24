package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/2/2016.
 */
public class Work {


    private int Daily_Work_ID;
    private String DateOT;
    private int UnitID;
    private int Lesson_No;
    private Double amountCovered;
    private int timeTaken;
    private String comment;

    public Work() {
    }

    public Work(String dateOT, int unitID, int lesson_No, Double amountCovered, int timeTaken, String comment) {
        DateOT = dateOT;
        UnitID = unitID;
        Lesson_No = lesson_No;
        this.amountCovered = amountCovered;
        this.timeTaken = timeTaken;
        this.comment = comment;
    }

    public int getDaily_Work_ID() {
        return Daily_Work_ID;
    }

    public void setDaily_Work_ID(int daily_Work_ID) {
        Daily_Work_ID = daily_Work_ID;
    }

    public String getDateOT() {
        return DateOT;
    }

    public void setDateOT(String dateOT) {
        DateOT = dateOT;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int unitID) {
        UnitID = unitID;
    }

    public int getLesson_No() {
        return Lesson_No;
    }

    public void setLesson_No(int lesson_No) {
        Lesson_No = lesson_No;
    }

    public Double getAmountCovered() {
        return amountCovered;
    }

    public void setAmountCovered(Double amountCovered) {
        this.amountCovered = amountCovered;
    }

    public int getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(int timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
