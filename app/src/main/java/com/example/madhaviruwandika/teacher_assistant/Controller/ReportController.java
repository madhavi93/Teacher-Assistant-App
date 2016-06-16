package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.ReportDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.ReporterDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.PDFDocumentManeger;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Perfomance_analyser;
import com.example.madhaviruwandika.teacher_assistant.Model.Student_perfomance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class ReportController {


   ReporterDAO reporterDAO;
   Perfomance_analyser perfomance_analyser ;

    public ReportController(Context context){
        this.reporterDAO = new ReportDA(context);
        perfomance_analyser = new Perfomance_analyser();

    }


    public List<List<Integer>> getDataToStudentPerfomanceReport(int classID,int s_id){

        List<List<Integer>> data = new ArrayList<>();
        List<Exam> examList = reporterDAO.getExamsThatMArksAreEntered(classID);

        ArrayList<Integer> min = new ArrayList<>();
        ArrayList<Integer> max = new ArrayList<>();
        ArrayList<Integer> stdMark = new ArrayList<>();

        for (Exam exam : examList){
            List<Student_perfomance> list = reporterDAO.getStudentPerfomanceInExam(exam.getExamID());
            int[] minMAx = perfomance_analyser.getMaxAndMinOfTheExam(list);
            min.add(minMAx[0]);
            max.add(minMAx[1]);
            stdMark.add(reporterDAO.getStudentMarkOnExam(s_id, exam.getExamID()));


        }

        data.add(min);
        data.add(stdMark);
        data.add(max);
        return data;
    }

    public ArrayList<String> getExamNameListByClassID(int ClassID){

        List<Exam> examList = reporterDAO.getExamsThatMArksAreEntered(ClassID);
        ArrayList<String> examNameList = new ArrayList<>();
        for(Exam exam : examList){
            examNameList.add(exam.getDate()+"_"+exam.getLesson()+"_");
        }

        return examNameList;
    }

    public String getAttendenceState(int s_id,int class_id){

        int[] attendence = reporterDAO.getAttendenceOfStudent(s_id,class_id);
        if(attendence!=null) {
            String comment = perfomance_analyser.AttendenceState(attendence[0], attendence[1]);
            return "Student has " + ((attendence[1] / attendence[0]) * 100) + "%. " + comment;
        }
        else return null;

    }

    public List<Map<String,String>> getPayments(int s_id,int class_id){

        List<Payment> paymentList = reporterDAO.getPayments(s_id, class_id);
        Log.d("MYACTIVITY", ">>>>>>>>>>>>>>>>>>>>>>>>."+s_id+">>>"+class_id+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        List<Map<String,String>> list = new ArrayList<>();
        for (int i=0;i<paymentList.size();i++){
            Map<String,String> map = new HashMap<>();

            map.put("No",String.valueOf(i+1));
            map.put("date",paymentList.get(i).getDoP());
            map.put("month",paymentList.get(i).getMonthOfPayment());

            list.add(map);
        }

        return list;
    }


    public ArrayList<Integer> getExamMarkListOfStudent(int s_id){

        ArrayList<Integer> markList = new ArrayList<>();
        List<Student_perfomance> list = reporterDAO.getMarkListOfExamsBystudentID(s_id);
        Log.d("Mark",">>>>>>>>>>>>>>>>>>>>>>>>>>>>"+list.size()+"!!!!!!!!!!!!!!!!!!!!!!!!"+s_id+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (Student_perfomance student_perfomance : list){
            markList.add(student_perfomance.getMark());
        }
        return markList;
    }


    public String getStudentName(int sID){
        return  reporterDAO.getStudent(sID).getName();
    }



}
