package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.telephony.SmsManager;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.BaseActivity;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.ClassDAO;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.CommunicationDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.GroupMessage;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.SingleMessage;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class CommunicatorDA implements CommunicationDAO{


    SQLiteDatabase db;

    public CommunicatorDA(Context context) {
        db = DBConnection.getInstance(context).getWritableDatabase();
    }



    @Override
    public Parent getParent(int s_id) {

        Parent parent = new Parent();
        Cursor cursor = db.rawQuery("select * from Parent where "+DBConstant.parent_col2+" = "+s_id, null);
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
    public List<Parent> getParentList(List<Student> studentList) {
        List<Parent> parentList = new ArrayList<>();


        for( Student s : studentList){

            Cursor cursor = db.rawQuery("select * from Parent where "+DBConstant.parent_col2+" = "+ s.getS_id() , null);
            if (cursor.getCount() == 0) {
                Log.d("MYACTIVITY", "No Value");
                return null;
            } else {
                //iterate through result set
                if (cursor.moveToFirst()) {
                    do {

                        Parent parent = new Parent();

                        parent.setParentID(Integer.parseInt(cursor.getString(0)));
                        parent.setSID(Integer.parseInt(cursor.getString(1)));
                        parent.setName(cursor.getString(2));
                        parent.setTp_no(cursor.getString(3));
                        parent.setEmail(cursor.getString(4));

                        parentList.add(parent);

                    } while (cursor.moveToNext());
                }

            }

        }
        return parentList;
    }

    @Override
    public List<Student> getStudentListByClassID(int id) {

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

    public int getMessageID(String mType){

        List<Integer> idList = new ArrayList<>();
        int idnext;

        if(mType == "S"){
            Cursor cursor = db.rawQuery("select " + DBConstant.SingleMsg_col1 + " from SingleMessage", null);
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
        else if(mType == "G"){
            Cursor cursor = db.rawQuery("select " + DBConstant.SingleMsg_col1 + " from GroupMessage", null);
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
        else return 0;
    }

    @Override
    public long RecordOfSendingSingleMessage(SingleMessage m) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstant.SingleMsg_col1,getMessageID("S")+1);
        contentValues.put(DBConstant.SingleMsg_col2,m.getMsg_type());
        contentValues.put(DBConstant.SingleMsg_col3,m.getDateOfMsg());
        contentValues.put(DBConstant.SingleMsg_col4,m.getContent());
        contentValues.put(DBConstant.SingleMsg_col5,m.getRecipient());


        long result = db.insert("SingleMessage", null, contentValues);

        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED");
            return result;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED ");
            return result;
        }
    }


    @Override
    public long RecordOfSendingGroupMessage(GroupMessage m) {

            ContentValues contentValues = new ContentValues();

            contentValues.put(DBConstant.GroupMsg_col1,getMessageID("G")+1);
            contentValues.put(DBConstant.GroupMsg_col2,m.getMsg_type());
            contentValues.put(DBConstant.GroupMsg_col3,m.getDateOfMsg());
            contentValues.put(DBConstant.GroupMsg_col4,m.getContent());
            contentValues.put(DBConstant.GroupMsg_col5,m.getClass_Id());
            contentValues.put(DBConstant.GroupMsg_col6,m.getNo_Of_Recip());


            long result = db.insert("GroupMessage", null, contentValues);

            if(result == -1){
                Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED ");
                return result;
            }
            else {
                Log.d("MYACTIVITY", "VALUES ARE INSERTED ");
                return result;
            }

    }


}
