package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/2/2016.
 */
public class SingleMessage {

    private int Message_ID;
    private int Msg_type;
    private String DateOfMsg;
    private String Content;
    private String Recipient;

    public SingleMessage(String recipient, String content, String dateOfMsg, int msg_type, int message_ID) {
        Recipient = recipient;
        Content = content;
        DateOfMsg = dateOfMsg;
        Msg_type = msg_type;
        Message_ID = message_ID;
    }

    public SingleMessage(int msg_type, String dateOfMsg, String content, String recipient) {
        Msg_type = msg_type;
        DateOfMsg = dateOfMsg;
        Content = content;
        Recipient = recipient;
    }

    public SingleMessage() {
    }

    public String getRecipient() {
        return Recipient;
    }

    public void setRecipient(String recipient) {
        Recipient = recipient;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getMessage_ID() {
        return Message_ID;
    }

    public void setMessage_ID(int message_ID) {
        Message_ID = message_ID;
    }

    public String getDateOfMsg() {
        return DateOfMsg;
    }

    public void setDateOfMsg(String dateOfMsg) {
        DateOfMsg = dateOfMsg;
    }

    public int getMsg_type() {
        return Msg_type;
    }

    public void setMsg_type(int msg_type) {
        Msg_type = msg_type;
    }






}
