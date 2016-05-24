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
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
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
        Cursor cursor = db.rawQuery("select " + DBConstant.stdTable_col1 + " from Student", null);

        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            idnext = 0;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col1)));
                    idList.add(id);
                } while (cursor.moveToNext());
            }

            idnext = idList.size();
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
        Cursor cursor = db.rawQuery("select " + DBConstant.parent_col1 + " from Parent", null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            idnext = 0;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    idList.add(id);
                } while (cursor.moveToNext());
            }

            idnext = idList.size();
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
        Cursor cursor = db.rawQuery("select * from Student inner join Attend on Student.ID = Attend.S_id where Class_Id = " + String.valueOf(id), null);

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
        Cursor cursor = db.rawQuery("select " + DBConstant.exam_col1 + " from Exam", null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            idnext = 0;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt(cursor.getString(0));
                    idList.add(id);
                } while (cursor.moveToNext());
            }
            idnext = idList.size();
        }
        return idnext;
    }


    public List<Exam> getExamList(){

        List<Exam> examList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Exam", null);

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
        long j =db.update("Parent", contentValues1,DBConstant.parent_col1+" = "+p.getParentID(),null);

        if(i == -1 && j == -1){
            return 0;
        }

        return (long)1 ;
    }

}
