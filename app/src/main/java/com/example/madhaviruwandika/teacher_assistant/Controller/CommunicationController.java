package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity.SendEmailActivity;
import com.example.madhaviruwandika.teacher_assistant.Activity.parentCommunicatorActivity.SendMessageActivity;
import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.CommunicatorDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.CommunicationDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.GroupMessage;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.SingleMessage;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemRegisterName;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class CommunicationController {


    CommunicationDAO communicationDAO;

    public CommunicationController(Context context){
        communicationDAO = new CommunicatorDA(context);
    }


    public String parentTPNo(int s_id){
        Parent parent = communicationDAO.getParent(s_id);
        if(parent!= null){
            return parent.getTp_no();
        }
        else {
            return null;
        }
    }

    public String parentEmail(int s_id){
        Parent parent = communicationDAO.getParent(s_id);
        if(parent!= null){
            return parent.getEmail();
        }
        else {
            return null;
        }
    }


    public int sendSMS(String phoneNo,String message ) {
        try {


            SmsManager smsManager = SmsManager.getDefault();

            Log.d("Send SMS", "SENT SUCCESSFULLY........................................................................."+phoneNo);
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

            return 1;
        }

        catch (Exception e) {
            Log.d("Send SMS", "SENDING FAILED............................................................................"+phoneNo);
            e.printStackTrace();
              return 0;
        }
    }

    public int sendSMSMessage(String phoneNo,String message){

        if(sendSMS(phoneNo,message)==1){

            SingleMessage singleMessage = new SingleMessage();
            singleMessage.setContent(message);
            singleMessage.setRecipient(phoneNo);
            singleMessage.setMsg_type(1);

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            singleMessage.setDateOfMsg(dateFormat.format(cal.getTime()));


            if(communicationDAO.RecordOfSendingSingleMessage(singleMessage)!= -1){
                return 1;
            }
            else {
                return 2;
            }
        }
        else {
            return 0;
        }

    }

    public Intent sendEmailMessage(){
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        return emailIntent;
    }

    public int sendSMSForGroup(int ClassID,String message){


        List<Student> studentList = communicationDAO.getStudentListByClassID(ClassID);

        List<String> contactNoArray  = new ArrayList<String>();
        int failureCount = 0;

        for (Student s: studentList){
            contactNoArray.add(communicationDAO.getParent(s.getS_id()).getTp_no());
        }

        for (int i=0;i<contactNoArray.size();i++){
            if(sendSMS(contactNoArray.get(i),message)!= 1){
                failureCount+=1;
            }

            else {

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();

                GroupMessage groupMessage = new GroupMessage();
                groupMessage.setContent(message);
                groupMessage.setMsg_type(2);
                groupMessage.setNo_Of_Recip(studentList.size());
                groupMessage.setClass_Id(ClassID);
                groupMessage.setDateOfMsg(dateFormat.format(cal.getTime()));

                // add to datebase
                communicationDAO.RecordOfSendingGroupMessage(groupMessage);


            }
        }

        return failureCount;
    }

    public int sendEmailForGroup(){
        return 0;
    }

    public List<TutionClass> getClassList(){
        List<TutionClass> classes = communicationDAO.getClassDetails();
        if(classes!= null){
            return classes;
        }
        else {
            Log.d("MYACTIVITY","No Class is added yet" );
            return null;
        }
    }

    public List<String> getClassListForSpinner(){
        //get class list from the database
        List<TutionClass> classList= getClassList();

        //create list of class names
        List<String> categories = new ArrayList<String>();
        categories.add("");
        for(int i=0;i<classList.size();i++){
            categories.add(classList.get(i).getClassName());
        }
        return categories;
    }

    public int getClassIDBySpinnerItemSelected(int position){
        //get class list from the database
        List<TutionClass> classList= getClassList();
        if(position > 0){
            return classList.get(position-1).getClassID();
        }
        else return 0;
    }

    public List<String[][]> getStudentListByClassID(int id){
        List<Student> studentList= communicationDAO.getStudentListByClasssID(id);
        List<String[][]> studentListD = new ArrayList<>();

        for (Student s : studentList){
            String[][] student = new String[1][2];
            student[0][0] = String.valueOf(s.getS_id());
            student[0][1] = s.getName();

            studentListD.add(student);
        }
        return studentListD;
    }

    public  void sendSMSAfterFinishingAttendence(int ClassID,ArrayList<ItemRegisterName> list){

        List<Student> studentList = communicationDAO.getStudentListByClassID(ClassID);

        List<String> contactNoArray  = new ArrayList<String>();
        int failureCount = 0;

        for (Student s: studentList){
            contactNoArray.add(communicationDAO.getParent(s.getS_id()).getTp_no());
        }


        for (int j=0;j<list.size();j++) {
            if (list.get(j).getAttendence()) {
                sendSMS(contactNoArray.get(j),"Your child has attended to the class today.");
            }
            else
            {
                sendSMS(contactNoArray.get(j),"Your child has not attended to the class today.");
            }

        }


    }

}
