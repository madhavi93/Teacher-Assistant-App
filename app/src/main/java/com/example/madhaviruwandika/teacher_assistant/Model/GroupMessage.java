package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/2/2016.
 */
public class GroupMessage {

    private int Message_ID;
    private int Msg_type;
    private String DateOfMsg;
    private String Content;
    private int Class_Id;
    private int No_Of_Recip;

    public GroupMessage(int msg_type, String dateOfMsg, String content, int class_Id, int no_Of_Recip) {
        Msg_type = msg_type;
        DateOfMsg = dateOfMsg;
        Content = content;
        Class_Id = class_Id;
        No_Of_Recip = no_Of_Recip;
    }

    public GroupMessage(int message_ID, int msg_type, String dateOfMsg, String content, int class_Id, int no_Of_Recip) {
        Message_ID = message_ID;
        Msg_type = msg_type;
        DateOfMsg = dateOfMsg;
        Content = content;
        Class_Id = class_Id;
        No_Of_Recip = no_Of_Recip;
    }

    public GroupMessage() {
    }

    public int getMessage_ID() {
        return Message_ID;
    }

    public void setMessage_ID(int message_ID) {
        Message_ID = message_ID;
    }

    public int getMsg_type() {
        return Msg_type;
    }

    public void setMsg_type(int msg_type) {
        Msg_type = msg_type;
    }

    public String getDateOfMsg() {
        return DateOfMsg;
    }

    public void setDateOfMsg(String dateOfMsg) {
        DateOfMsg = dateOfMsg;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getClass_Id() {
        return Class_Id;
    }

    public void setClass_Id(int class_Id) {
        Class_Id = class_Id;
    }

    public int getNo_Of_Recip() {
        return No_Of_Recip;
    }

    public void setNo_Of_Recip(int no_Of_Recip) {
        No_Of_Recip = no_Of_Recip;
    }
}
