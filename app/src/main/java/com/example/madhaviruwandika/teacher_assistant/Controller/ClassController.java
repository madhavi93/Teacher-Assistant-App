package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.ClassDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.ClassDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class ClassController {

    public ClassDAO classDAO;

    public ClassController(Context context) {
        classDAO = new ClassDA(context);
    }


    public int AddClass(String cname ,String time , String day, String startingDate,String EndDate,double fee){


        TutionClass tutionClass = new TutionClass(cname,startingDate , day, time, EndDate, fee);
        if(classDAO.addClass(tutionClass)==-1){
            return 0;
        }
        else {
            return 1;
        }

    }

    public List<TutionClass> getClassList(){
        List<TutionClass> classes = classDAO.getClassDetails();
        if(classes!= null){
            return classes;
        }
        else {
            Log.d("MYACTIVITY","No Class is added yet" );
            return null;
        }
    }

    public int addPayment(int SID,int CID){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy");
        String Date = sdf.format(calendar.getTime());

        Payment p = new Payment(SID,CID,Date);
        long r = classDAO.addPayment(p);

        if(r== -1){
            return 0;
        }
        else {return 1;}

    }



    public int ExtraAddClass(int cID ,String time , String Date,String classType){

        ExtraClass ExClass = new ExtraClass(cID,Date,time,classType);
        if(classDAO.addExtraClass(ExClass)==-1){
            return 0;
        }
        else {
            return 1;
        }

    }

    public TutionClass getTutionClassByID(int id){

        return classDAO.getClassByID(id);

    }





}
