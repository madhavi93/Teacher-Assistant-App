package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/22/2016.
 */
public class Lesson {


    private int unitNo;
    private int lessonNo;
    private String Lesson;
    private Double amountCovered;
    private int totaltimeSupposedToSpend;
    private int AmountTimeSpent;
    private String SpecialACT;

    public Lesson(int unitNo, int lessonNo, String lesson, Double amountCovered, int totaltimeSupposedToSpend, int amountTimeSpent, String specialACT) {
        this.unitNo = unitNo;
        this.lessonNo = lessonNo;
        Lesson = lesson;
        this.amountCovered = amountCovered;
        this.totaltimeSupposedToSpend = totaltimeSupposedToSpend;
        AmountTimeSpent = amountTimeSpent;
        SpecialACT = specialACT;
    }

    public Lesson() {
    }

    public int getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(int unitNo) {
        this.unitNo = unitNo;
    }

    public int getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(int lessonNo) {
        this.lessonNo = lessonNo;
    }

    public String getLesson() {
        return Lesson;
    }

    public void setLesson(String lesson) {
        Lesson = lesson;
    }

    public Double getAmountCovered() {
        return amountCovered;
    }

    public void setAmountCovered(Double amountCovered) {
        this.amountCovered = amountCovered;
    }


    public int getAmountTimeSpent() {
        return AmountTimeSpent;
    }

    public void setAmountTimeSpent(int amountTimeSpent) {
        AmountTimeSpent = amountTimeSpent;
    }

    public int getTotaltimeSupposedToSpend() {
        return totaltimeSupposedToSpend;
    }

    public void setTotaltimeSupposedToSpend(int totaltimeSupposedToSpend) {
        this.totaltimeSupposedToSpend = totaltimeSupposedToSpend;
    }

    public String getSpecialACT() {
        return SpecialACT;
    }

    public void setSpecialACT(String specialACT) {
        SpecialACT = specialACT;
    }
}
