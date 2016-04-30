package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.StudentDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;

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
    public List<String> getStudentDetails() {
        List<String> NameList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select " + DBConstant.stdTable_col2 + " from Student", null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    String acc_no = cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col2));
                    NameList.add(acc_no);
                } while (cursor.moveToNext());
            }
        }
        return NameList;
    }

    @Override
    public long setStudentDetails(Student s) {

        Log.d("MYACTIVITY","INSERTED ACCOUNT");
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.stdTable_col1,getnextID());
        contentValues.put(DBConstant.stdTable_col2,s.getName());
        contentValues.put(DBConstant.stdTable_col3, s.getDoB());
        contentValues.put(DBConstant.stdTable_col4, s.getAddress());


        long result = db.insert("Student", null, contentValues);
        if(result == -1){
            Log.d("MYACTIVITY", "NOT_INSERTED++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return result;
        }
        else {
            Log.d("MYACTIVITY", "INSERTED name======================+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            Log.d("MYACTIVITY", String.valueOf(getnextID()));
            return result;
        }
    }

    public int getnextID() {
        List<Integer> idList = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select " + DBConstant.stdTable_col1 + " from Student", null);

        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            idnext = 1;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBConstant.stdTable_col1)));
                    idList.add(id);
                } while (cursor.moveToNext());
            }

            idnext = idList.size()+1;
        }
        return idnext;
    }


}
