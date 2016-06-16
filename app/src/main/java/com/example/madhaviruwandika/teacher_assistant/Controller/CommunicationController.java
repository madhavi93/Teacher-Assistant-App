package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.CommunicatorDA;
import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.MyProfileDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.CommunicationDAO;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.MyProfileDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.AppConstant;
import com.example.madhaviruwandika.teacher_assistant.Model.GroupMessage;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.Report_Generator;
import com.example.madhaviruwandika.teacher_assistant.Model.SingleMessage;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemRegisterName;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
    MyProfileDAO myProfileDAO;

    public CommunicationController(Context context){
        communicationDAO = new CommunicatorDA(context);
        myProfileDAO = new MyProfileDA(context);
    }

    /*
    this method return the parent's telephone number of the student
     */
    public String parentTPNo(int s_id){
        Parent parent = communicationDAO.getParent(s_id);
        if(parent!= null){
            return parent.getTp_no();
        }
        else {
            return null;
        }
    }

    /*
    this method return the parent's email address of the student
     */
    public String parentEmail(int s_id){
        Parent parent = communicationDAO.getParent(s_id);
        if(parent!= null){
            return parent.getEmail();
        }
        else {
            return null;
        }
    }

    /*
        *this method get the phone no and SMS message as inputs and send SMS to the given phone number
        * if SMS detais are succesfully sent to the messange application on the device it returns 1. othervice it returns 0
     */
    public int sendSMS(String phoneNo,String message ) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            Log.d("Send SMS", "SENT SUCCESSFULLY........................................................................."+phoneNo);
            smsManager.sendTextMessage(phoneNo, null, message+"\n**"+ myProfileDAO.getProfileData().getName()+", Tution Class Teacher"+"**" , null, null);
            return 1;
        }

        catch (Exception e) {
            Log.d("Send SMS", "SENDING FAILED............................................................................"+phoneNo);
            e.printStackTrace();
              return 0;
        }
    }

    /*
        *this method get the phone no and SMS message as inputs and send SMS to the given phone number
        * this create message in to SingleMessage object format and invoke method to save sending message logs in the database
        * if SMS detais are succesfully sent to the messange application on the device it returns 1. othervice it returns 0
     */
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

    /*
    this method send SMS for group
     */

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

    /*
    * this is method to send simple email to one recipient
     */
    public Intent sendEmailMessage(String className,int Sid,String message){

        String subject = "Regarding details of Tution Class : "+ className ;
        String EmilAddress = this.parentEmail(Sid);
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_BCC, new String[]{EmilAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        return emailIntent;

    }

    /*
    this method send email for oll of the patent in the class
     */
    public Intent sendEmailForGroup(int classID, String className, String message){

        List<Student> studentList = communicationDAO.getStudentListByClassID(classID);
        List<String> contactNoArray  = new ArrayList<String>();
        String[] TO = new String[studentList.size()];
        int i=0;
        for (Student s: studentList){
            contactNoArray.add(communicationDAO.getParent(s.getS_id()).getEmail());
            TO[i] = communicationDAO.getParent(s.getS_id()).getEmail();
            i++;
        }

        String subject = "Regarding details of Tution Class : "+ className ;

        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_BCC, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        return emailIntent;

    }

    /*
    this method returns tution class list that the user conduct
     */
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

    /*
    this method retrive class data from the database and arange those data to send to view layer
     */
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

    /*
    this method retrive student details those who registered in the given class
    those details are arranged in the way to send to the activity
     */
    public List<String[][]> getStudentListByClassID(int id){
        List<Student> studentList= communicationDAO.getStudentListByClassID(id);
        List<String[][]> studentListD = new ArrayList<>();

        for (Student s : studentList){
            String[][] student = new String[1][2];
            student[0][0] = String.valueOf(s.getS_id());
            student[0][1] = s.getName();

            studentListD.add(student);
        }
        return studentListD;
    }


    /*
    *This method for sending sms after finishing the class
    * telephone numbers of the parent's of the student who attend to the class is retrived from the database
    * sms will be send by indicating the time that class was finished
     */
    public  void sendSMSAfterFinishingAttendence(int ClassID,ArrayList<ItemRegisterName> list){

        List<Student> studentList = communicationDAO.getStudentListByClassID(ClassID);

        List<String> contactNoArray  = new ArrayList<String>();
        int failureCount = 0;

        for (Student s: studentList){
            contactNoArray.add(communicationDAO.getParent(s.getS_id()).getTp_no());
        }


        for (int j=0;j<list.size();j++) {
            if (list.get(j).getAttendence()) {
                sendSMS(contactNoArray.get(j),"Your child has attended to the class today." );
            }
            else
            {
                sendSMS(contactNoArray.get(j),"Your child has not attended to the class today.");
            }

        }
    }

    public void SendSmsToNoifyFinishingtheClass(int ClassID){

        List<Student> studentList = communicationDAO.getStudentListByClassID(ClassID);
        List<String> contactNoArray  = new ArrayList<String>();

        for (Student s: studentList){
            contactNoArray.add(communicationDAO.getParent(s.getS_id()).getTp_no());
        }
        ArrayList<ItemRegisterName> list = communicationDAO.getTodaysRegister(ClassID,AppConstant.getInstance().getFinishClassDate());
        for (int j=0;j<list.size();j++) {
            if (list.get(j).getAttendence()) {
                sendSMS(contactNoArray.get(j), "Today's Class has been finished.");
            }
        }
    }

    /*
    *this method for send email after adding student's preformance report
    * pdf document is creating including the students perfomance details
     */
    public Intent sendEmailWithAttacthment(String className,int classID,int Sid,String message,Context context){

        Report_Generator report_generator = new Report_Generator(context);

        String subject = "Regarding details of Tution Class : "+ className ;
        String EmilAddress = this.parentEmail(Sid);
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
       // Uri uri = Uri.fromFile(report_generator.createStudentReport(context,Sid,classID,className));
       // emailIntent.putExtra(Intent.EXTRA_STREAM,uri);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{EmilAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Dear sir, maam,\n This is the progerss report of your child.This contain his/her perfomance, attendence detais and payment details.Hope you will pay attention on this report and will motivale your child. \n"+ message);

        return emailIntent;


    }


    public Intent sendEmailWithAttacthment(String className,int classID,int Sid,String message,Context context,RelativeLayout barChart,RelativeLayout lineChart){

        Report_Generator report_generator = new Report_Generator(context);

        String subject = "Regarding details of Tution Class : "+ className ;
        String EmilAddress = this.parentEmail(Sid);
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        Uri uri = Uri.fromFile(report_generator.createStudentReport(context,Sid,classID,className,barChart,lineChart));
        emailIntent.putExtra(Intent.EXTRA_STREAM,uri);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{EmilAddress});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Dear sir, maam,\n This is the progerss report of your child.This contain his/her perfomance, attendence detais and payment details.Hope you will pay attention on this report and will motivale your child. \n"+ message);

        return emailIntent;


    }

    /*
    public File createPDF(Context context)  {

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
        String pdfName = "pdfdemo"
                + sdf.format(Calendar.getInstance().getTime()) + ".pdf";

        try {

            File outputFile = new File(context.getExternalFilesDir("/My app"), pdfName);
            OutputStream out =  new FileOutputStream(outputFile);

            Document pdfDocument = new Document(PageSize.A4,72,36,36,36);
            PdfWriter.getInstance(pdfDocument, new FileOutputStream(outputFile));

            pdfDocument.open();
            Anchor anchorTarget = new Anchor("First page of the document.");
            anchorTarget.setName("BackToTop");
            Paragraph paragraph1 = new Paragraph();
            paragraph1.setSpacingBefore(50);
            paragraph1.add(anchorTarget);


            pdfDocument.add(paragraph1);
            pdfDocument.add(new Paragraph("Some more text on the \first page with different color and font type.",
                    FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD, new CMYKColor(0, 255, 0, 0))));
            pdfDocument.close();
            out.close();
            return outputFile;

        } catch (BadElementException e) {
            e.printStackTrace();
            return null;
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
    */

}
