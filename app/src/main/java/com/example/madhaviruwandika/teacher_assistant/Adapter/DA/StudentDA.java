package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.StudentDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.Student_perfomance;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 3/27/2016.
 */
public class StudentDA implements StudentDAO {

    SQLiteDatabase db;

    public StudentDA(Context context){
        db = DBConnection.getInstance(context).getWritableDatabase();
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

    @Override
    public int getAttendingClass(int S_id) {

        int ClassID = 0;

        Cursor cursor = db.rawQuery("select * from Parent where "+DBConstant.parent_col2+" = "+S_id, null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            return ClassID;

        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {

                    ClassID = Integer.parseInt(cursor.getString(1));

                } while (cursor.moveToNext());
            }

        }
        return ClassID;
    }

    @Override
    public Parent getParent(int S_id) {

        Parent parent = new Parent();
        Cursor cursor = db.rawQuery("select * from Parent where "+DBConstant.parent_col2+" = "+S_id, null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            parent = null;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {

                    parent.setParentID(Integer.parseInt(cursor.getString(0)));
                    parent.setSID(Integer.parseInt(cursor.getString(1)));
                    parent.setName(cursor.getString(2));
                    parent.setTp_no(cursor.getString(3));
                    parent.setEmail(cursor.getString(4));

                } while (cursor.moveToNext());
            }

        }
        return parent;
    }

    @Override
    /*
    *method for get student detais from student table
     */
    public List<Student> getStudentDetails() {

        List<Student> NameList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Student", null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();

                    student.setS_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col1))));
                    student.setName(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col2)));
                    student.setAddress(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col4)));
                    student.setDoB(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col3)));

                    NameList.add(student);
                } while (cursor.moveToNext());
            }
        }
        return NameList;
    }

    @Override
    public int setStudentDetails(Student s) {

        Log.d("MYACTIVITY","INSERTING TO STUDENT");

        // set values to contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.stdTable_col1,getnextSID()+1);
        contentValues.put(DBConstant.stdTable_col2,s.getName());
        contentValues.put(DBConstant.stdTable_col3, s.getDoB());
        contentValues.put(DBConstant.stdTable_col4, s.getAddress());


        long result = db.insert("Student", null, contentValues);
        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO STUDENT");
            return 0;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO STUDENT ");
            Log.d("MYACTIVITY", String.valueOf(getnextSID()));
            return getnextSID();
        }
    }

    public int getnextSID() {
        List<Integer> idList = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select " + DBConstant.stdTable_col1 + " from Student ORDER BY "+DBConstant.stdTable_col1 +" DESC LIMIT 1", null);

        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            idnext = 0;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col1)));
                    idnext = id;
                } while (cursor.moveToNext());
            }else {
                idnext = 0;
            }
        }


        Log.d("MYACTIVITY", "+++++++++++++++++++++++++++++++++++++++++++++=NEXT ID = " + String.valueOf(idnext) + "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return idnext;
    }

    public Long addParent(Parent parent){
        Log.d("MYACTIVITY","INSERTING TO PARENT");

        //set values to ContentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.parent_col1,getNextParentID()+1);
        contentValues.put(DBConstant.parent_col2, parent.getSID());
        contentValues.put(DBConstant.parent_col3, parent.getName());
        contentValues.put(DBConstant.parent_col4, parent.getTp_no());
        contentValues.put(DBConstant.parent_col5, parent.getEmail());

        //db query to execute
        long result = db.insert("Parent", null, contentValues);

        if(result == -1){
            Log.d("MYACTIVITY", "Values are NOT_INSERTED to parent");
            return result;
        }
        else {
            Log.d("MYACTIVITY", "Values are INSERTED to Parent");
            return result;
        }
    }

    public int getNextParentID(){

        List<Integer> idList = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select " + DBConstant.parent_col1 + " from Parent ORDER BY "+DBConstant.parent_col1+" DESC LIMIT 1", null);
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
    public long AddStudentClass(int ClassId, int SID) {
        Log.d("MYACTIVITY","INSERTING TO ATTEND");

        // set values to contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.attend_col1,SID);
        contentValues.put(DBConstant.attend_col2,ClassId);


        long result = db.insert("Attend", null, contentValues);
        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO Attend");
            return result;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO Attend ");
            return result;
        }

    }

    @Override
    public List<Student> getStudentListByClasssID(int id) {

        List<Student> NameList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Student left outer join Attend on Student.ID = Attend.S_id where Class_Id = " + String.valueOf(id), null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    Student student = new Student();

                    student.setS_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col1))));
                    student.setName(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col2)));
                    student.setAddress(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col4)));
                    student.setDoB(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col3)));

                    NameList.add(student);
                } while (cursor.moveToNext());
            }
        }
        return NameList;

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

    public List<Exam> getExamList(int classID){

        List<Exam> examList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Exam where "+ DBConstant.exam_col2 +" = "+ classID, null);

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
    public List<TutionClass> getClassDetails() {
        List<TutionClass> ClassList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from TutionClass", null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    TutionClass t = new TutionClass();

                    t.setClassID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConstant.tutionClass_col1))));
                    t.setStartDate(cursor.getString(cursor.getColumnIndex(DBConstant.tutionClass_col3)));
                    t.setClassName(cursor.getString(cursor.getColumnIndex(DBConstant.tutionClass_col2)));
                    t.setDay(cursor.getString(cursor.getColumnIndex(DBConstant.tutionClass_col5)));
                    t.setTime(cursor.getString(cursor.getColumnIndex(DBConstant.tutionClass_col6)));

                    ClassList.add(t);

                } while (cursor.moveToNext());
            }
        }
        return ClassList;
    }

    @Override
    public long UpdateStudent(Student s,Parent p) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstant.stdTable_col1,s.getS_id());
        contentValues.put(DBConstant.stdTable_col2,s.getName());
        contentValues.put(DBConstant.stdTable_col3, s.getDoB());
        contentValues.put(DBConstant.stdTable_col4,s.getAddress());

        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(DBConstant.parent_col3,p.getName());
        contentValues1.put(DBConstant.parent_col4,p.getTp_no());
        contentValues1.put(DBConstant.parent_col5,p.getEmail());



        long i = db.update("Student", contentValues, DBConstant.stdTable_col1 + " = " + s.getS_id(), null);
        long j =db.update("Parent", contentValues1, DBConstant.parent_col1 + " = " + p.getParentID(), null);

        if(i == -1 && j == -1){
            return 0;
        }

        return (long)1 ;
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
                    Log.d("Payment", ">>>>>>>>>>>>>>>>>>>>>>>>." + cursor.getString(4) + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
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
    public long DeleteStudent(int s_id) {
        long i = db.delete("Student", DBConstant.stdTable_col1 + " = " + s_id, null);
        return i;
    }
 }
