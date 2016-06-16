package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.ReporterDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.Student_perfomance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 6/14/2016.
 */
public class ReportDA  implements ReporterDAO{

    SQLiteDatabase db;

    public ReportDA(Context context) {
        db = DBConnection.getInstance(context).getWritableDatabase();
    }


    public int getExamID() {

        List<Integer> idList = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select " + DBConstant.exam_col1 + " from Exam ORDER BY "+DBConstant.exam_col1+" DESC LIMIT 1", null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            idnext = 0;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    idnext = id;
                } while (cursor.moveToNext());
            }
            else {
                idnext = 0;
            }
        }
        return idnext;
    }

    @Override
    public int getStudentMarkOnExam(int s_id, int examID) {

        int studentMark = 0;
        Cursor cursor = db.rawQuery("select * from PerformedAt where " + DBConstant.markSheet_col1 + " = " + examID + " and " + DBConstant.markSheet_col2 + " = " + s_id, null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    studentMark = cursor.getInt(2);
                } while (cursor.moveToNext());
            }
        }
        return studentMark;
    }

    @Override
    public List<Student_perfomance> getMarkListOfExamsBystudentID(int s_id) {
        List<Student_perfomance> studentMark = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from PerformedAt where "+DBConstant.markSheet_col2+" = "+s_id , null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    Student_perfomance student_perfomance = new Student_perfomance();
                    student_perfomance.setMark(cursor.getInt(2));
                    student_perfomance.setS_id(cursor.getInt(1));
                    studentMark.add(student_perfomance);
                } while (cursor.moveToNext());
            }
        }
        return studentMark;

    }


    @Override
    public int[] getAttendenceOfStudent(int s_id,int ClassID) {

        int attendCount = 0;
        int days = 0 ;

        Cursor cursor = db.rawQuery("select * from Attendance_Sheet where "+DBConstant.attendenceSheet_col2+" = "+s_id + " and "+DBConstant.attendenceSheet_col3+" = "+ClassID , null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {

            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    days += 1;
                    if(cursor.getInt(4) == 1){
                        attendCount+=1;
                    }
                } while (cursor.moveToNext());
            }
        }
        int[] array = {days,attendCount};

        return array;

    }

    @Override
    public List<Payment> getPayments(int s_id, int class_id) {
        List<Payment> paymentList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from Payment where S_id = "+s_id + " and Class_Id = "+class_id , null);
        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value...");
        }
        else {

            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    Payment payment = new Payment();
                    payment.setDoP(cursor.getString(3));
                    payment.setMonthOfPayment(cursor.getString(4));
                    paymentList.add(payment);
                } while (cursor.moveToNext());
            }
        }
        return  paymentList;
    }

    @Override
    public List<Exam> getExamListWithOutMarkSheetByClassID(int classID) {

        ArrayList<Integer> examIDList = getExamsThatMArksAreEntered();
        String arrayString = "";
        for (int i=0;i<examIDList.size();i++){
            arrayString = arrayString+examIDList.get(i);
            if(i != (examIDList.size()-1)){
                arrayString = arrayString+",";
            }

        }

        List<Exam> examList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Exam where "+ DBConstant.exam_col2 +" = "+classID+" AND"+DBConstant.exam_col1+" NOT IN ("+arrayString+")",null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    Exam exam = new Exam();

                    exam.setExamID(Integer.parseInt(cursor.getString(0)));
                    exam.setClassID(Integer.parseInt(cursor.getString(1)));
                    exam.setEtype(cursor.getString(2));
                    exam.setDate(cursor.getString(3));
                    exam.setLesson(cursor.getString(4));
                    examList.add(exam);
                } while (cursor.moveToNext());
            }
        }
        return examList;
    }

    public ArrayList<Integer> getExamsThatMArksAreEntered(){

        ArrayList<Integer> ExamID = new ArrayList<>();
        Cursor cursor = db.rawQuery("select Distinct "+DBConstant.markSheet_col1+" from PerformedAt", null);
        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    ExamID.add(cursor.getInt(0));
                } while (cursor.moveToNext());
            }
        }
        return ExamID;
    }

    public List<Exam> getExamsThatMArksAreEntered(int classID){

        ArrayList<Integer> examIDList = getExamsThatMArksAreEntered();
        String arrayString = "";
        for (int i=0;i<examIDList.size();i++){
            arrayString = arrayString+examIDList.get(i);
            if(i != (examIDList.size()-1)){
                arrayString = arrayString+",";
            }

        }
        List<Exam> examList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Exam where "+ DBConstant.exam_col2 +" = "+ classID+" and "+DBConstant.exam_col1+" IN ("+arrayString+")" , null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    Exam exam = new Exam();

                    exam.setExamID(Integer.parseInt(cursor.getString(0)));
                    exam.setClassID(Integer.parseInt(cursor.getString(1)));
                    exam.setEtype(cursor.getString(2));
                    exam.setDate(cursor.getString(3));
                    exam.setLesson(cursor.getString(4));

                    examList.add(exam);


                } while (cursor.moveToNext());
            }
        }
        return examList;
    }

    @Override
    public List<Student_perfomance> getStudentPerfomanceInExam(int examID) {

        List<Student_perfomance> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from PerformedAt where "+DBConstant.markSheet_col1+" = "+examID , null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    Student_perfomance student_perfomance = new Student_perfomance();
                    student_perfomance.setS_id(cursor.getInt(1));
                    student_perfomance.setMark(cursor.getInt(2));

                    list.add(student_perfomance);

                } while (cursor.moveToNext());
            }
        }
        return list;
    }


    @Override
    public Student getStudent(int S_id) {
        Student student = new Student();

        Cursor cursor = db.rawQuery("select * from Student where "+DBConstant.stdTable_col1+" = "+S_id, null);
        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {

                    student.setS_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col1))));
                    student.setName(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col2)));
                    student.setAddress(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col4)));
                    student.setDoB(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col3)));

                } while (cursor.moveToNext());
            }
        }
        return student;
    }



}
