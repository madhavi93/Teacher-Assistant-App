package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.StudentDAO;
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


        Log.d("MYACTIVITY","+++++++++++++++++++++++++++++++++++++++++++++=NEXT ID = "+String.valueOf(idnext)+"+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
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
        Cursor cursor = db.rawQuery("select * from Student inner join Attend on Student.ID = Attend.S_id where Class_Id = "+String.valueOf(id), null);

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


}
