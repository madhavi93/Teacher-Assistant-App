package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/2/2016.
 */
public class Payment {

    private int SID;
    private int CID;
    private String DoP;
    private String monthOfPayment;

    public Payment( int SID, int CID , String doP,String month) {
        DoP = doP;
        this.SID = SID;
        this.CID = CID;
        this.monthOfPayment = month;
    }

    public Payment() {
    }

    public Payment(int SID, int CID) {
        this.SID = SID;
        this.CID = CID;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getDoP() {
        return DoP;
    }

    public void setDoP(String doP) {
        DoP = doP;
    }

    public String getMonthOfPayment() {
        return monthOfPayment;
    }

    public void setMonthOfPayment(String monthOfPayment) {
        this.monthOfPayment = monthOfPayment;
    }




}
