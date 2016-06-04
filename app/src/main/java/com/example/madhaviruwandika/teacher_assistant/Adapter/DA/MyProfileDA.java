package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.MyProfileDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.MyProfile;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 5/31/2016.
 */
public class MyProfileDA implements MyProfileDAO {

    SQLiteDatabase db;
    public MyProfileDA(Context context){
        db = DBConnection.getInstance(context).getWritableDatabase();
    }


    @Override
    public long saveProfileData(MyProfile myProfile) {
        Log.d("MYACTIVITY", "INSERTING TO MyAccount");

        // set values to contentValues
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstant.myProfileTable_col1,getLastProfileID()+1);
        contentValues.put(DBConstant.myProfileTable_col2,myProfile.getName());
        contentValues.put(DBConstant.myProfileTable_col3,myProfile.getPassword());
        contentValues.put(DBConstant.myProfileTable_col4,myProfile.getDateOFChange());


        long result = db.insert("MyAccount", null, contentValues);

        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO MyAccount");
            return result;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO MyAccount ");
            return result;
        }
    }

    public int getLastProfileID(){

        List<Integer> idList = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select " + DBConstant.myProfileTable_col1 + " from MyAccount", null);
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
    public MyProfile getProfileData() {

        MyProfile myProfile = new MyProfile();
        int changeID = (getLastProfileID());
        if(changeID != 0) {
            Cursor cursor = db.rawQuery("select * from MyAccount where " + DBConstant.myProfileTable_col1 + " = " + changeID, null);

            if (cursor.getCount() == 0) {
                Log.d("MYACTIVITY", "No Value");
            }
            else {
                //iterate through result set
                if (cursor.moveToFirst()) {
                    do {
                            myProfile.setChange_ID(cursor.getInt(0));
                            myProfile.setName(cursor.getString(1));
                            myProfile.setPassword(cursor.getString(2));
                            myProfile.setDateOFChange(cursor.getString(3));

                    } while (cursor.moveToNext());
                }
            }
        }else {
            myProfile=null;
        }
        return myProfile;
    }

    @Override
    public long updateProfileData(MyProfile myProfile) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstant.myProfileTable_col1,getLastProfileID()+1);
        contentValues.put(DBConstant.myProfileTable_col2,myProfile.getName());
        contentValues.put(DBConstant.myProfileTable_col3,myProfile.getPassword());
        contentValues.put(DBConstant.myProfileTable_col4,myProfile.getDateOFChange());

        long result = db.insert("MyAccount", null, contentValues);

        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO MyAccount");
            return result;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO MyAccount ");
            return result;
        }
    }

    @Override
    public List<TutionClass> getClassList() {
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
}
