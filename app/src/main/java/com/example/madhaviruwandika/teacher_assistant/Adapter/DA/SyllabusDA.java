package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.SyllabusDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.Model.LessonUnit;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class SyllabusDA implements SyllabusDAO{

    SQLiteDatabase db;

    public SyllabusDA(Context context) {
        this.db = DBConnection.getInstance(context).getWritableDatabase();
    }

    public int addtoSylUnit(LessonUnit lessonUnit){
        Log.d("MYACTIVITY", "INSERTING TO Tution Class");

        int unitID = getUnitTID()+1;
        // set values to contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.Unit_col1,unitID);
        contentValues.put(DBConstant.Unit_col2,lessonUnit.getClass_Id());
        contentValues.put(DBConstant.Unit_col3,lessonUnit.getUnit());

        long result = db.insert("Unit", null, contentValues);

        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO Unit");
            return 0;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO UNIT");
            return unitID;
        }

    }
    public int addtoSylLesson(Lesson lesson){


        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstant.SyllabusLesson_col1,lesson.getUnitNo());
        contentValues.put(DBConstant.SyllabusLesson_col2,lesson.getLesson());
        contentValues.put(DBConstant.SyllabusLesson_col3,lesson.getLessonNo());
        contentValues.put(DBConstant.SyllabusLesson_col4,lesson.getTotaltimeSupposedToSpend());
        contentValues.put(DBConstant.SyllabusLesson_col5,lesson.getSpecialACT());
        contentValues.put(DBConstant.SyllabusLesson_col6,lesson.getAmountCovered());

        long result = db.insert("SyllabusLesson", null, contentValues);

        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO SyllabusLesson ");
            return -1;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO SyllabusLesson  ");
            return 1;
        }

    }

    public int getUnitTID(){

        List<Integer> idList = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select " + DBConstant.Unit_col1 + " from Unit", null);
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
    public List<Lesson> getLessonD(int ClassID) {
        List<Lesson> LessonList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from SyllabusLesson inner join Unit on SyllabusLesson.Unit_id =Unit.UnitID where ClassID = "+ClassID, null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    Lesson l = new Lesson();

                    l.setUnitNo(cursor.getInt(cursor.getColumnIndex("Unit")));
                    l.setLesson(cursor.getString(cursor.getColumnIndex(DBConstant.SyllabusLesson_col2)));
                    l.setLessonNo(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col3)));
                    l.setAmountTimeSpent(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col4)));
                    l.setSpecialACT(cursor.getString(cursor.getColumnIndex(DBConstant.SyllabusLesson_col5)));
                    l.setAmountCovered(cursor.getDouble(cursor.getColumnIndex(DBConstant.SyllabusLesson_col6)));


                    LessonList.add(l);

                } while (cursor.moveToNext());
            }
        }
        return LessonList;
    }

}
