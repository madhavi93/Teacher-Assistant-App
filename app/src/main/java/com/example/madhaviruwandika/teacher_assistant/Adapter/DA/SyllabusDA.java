package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.SyllabusDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.Model.LessonUnit;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Work;

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


        int unitID = getUnitIDByClassandUnit(lessonUnit.getClass_Id(), lessonUnit.getUnit());

        if(unitID == 0){
            unitID = getUnitTID()+1;
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
        else {
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
        contentValues.put(DBConstant.SyllabusLesson_col6, lesson.getAmountCovered());
        contentValues.put(DBConstant.SyllabusLesson_col7,lesson.getAmountTimeSpent());


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
        Cursor cursor = db.rawQuery("select " + DBConstant.Unit_col1 + " from Unit ORDER BY "+DBConstant.Unit_col1+" DESC LIMIT 1", null);
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
                    l.setTotaltimeSupposedToSpend(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col4)));
                    l.setSpecialACT(cursor.getString(cursor.getColumnIndex(DBConstant.SyllabusLesson_col5)));
                    l.setAmountCovered(cursor.getDouble(cursor.getColumnIndex(DBConstant.SyllabusLesson_col6)));
                    l.setAmountTimeSpent(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col7)));

                    LessonList.add(l);

                } while (cursor.moveToNext());
            }
        }
        return LessonList;
    }

    @Override
    public List<LessonUnit> getUnitByClassID(int ClassID) {
        List<LessonUnit> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Unit where "+DBConstant.Unit_col2+ " = "+ ClassID, null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            return  null;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                   LessonUnit lessonUnit = new LessonUnit();
                   lessonUnit.setUnitID(cursor.getInt(0));
                   lessonUnit.setClass_Id(cursor.getInt(1));
                   lessonUnit.setUnit(cursor.getInt(2));
                   list.add(lessonUnit);

                } while (cursor.moveToNext());
            }

        }
        return list;
    }

    @Override
    public List<Lesson> getLessonListByClassIDandUnitID(int classID, int UnitID) {

        List<Lesson> LessonList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from SyllabusLesson inner join Unit on SyllabusLesson.Unit_id =Unit.UnitID where ClassID = "+classID+" and "+DBConstant.Unit_col3 +" = "+UnitID, null);

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
                    l.setAmountTimeSpent(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col7)));
                    LessonList.add(l);

                } while (cursor.moveToNext());
            }
        }
        return LessonList;
    }

    @Override
    public int getUnitIDByClassandUnit(int classID, int UnitId) {
        List<Integer> idList = new ArrayList<>();
        int id = 0;
        Cursor cursor = db.rawQuery("select " + DBConstant.Unit_col1 + " from Unit where " + DBConstant.Unit_col2 + " = " + classID + " and " + DBConstant.Unit_col3 + " = " + UnitId, null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            id = 0;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    id = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        return id;
    }

    @Override
    public long updateCoverdAmountsInSyllubas(int UnitID, int lessonNo, Double amountCovered,int amountTimeSpent) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.SyllabusLesson_col6,amountCovered);
        Log.d("my", "*********************************************" + amountTimeSpent + "*********************************");

        contentValues.put(DBConstant.SyllabusLesson_col7,amountTimeSpent);

        long i = db.update("SyllabusLesson", contentValues, DBConstant.SyllabusLesson_col1 + " = " + UnitID + " and " + DBConstant.SyllabusLesson_col3 + " = " + lessonNo, null);

        Log.d("my","*********************************************"+getFinishedAmountOfLesson(UnitID,lessonNo)+"*********************************");
        return i;
    }

    @Override
    public Lesson getFinishedAmountOfLesson(int UnitID, int lessonNo) {
        Lesson l = new Lesson();
        Cursor cursor = db.rawQuery("select * from SyllabusLesson where "+DBConstant.SyllabusLesson_col1+" = "+UnitID+" and "+DBConstant.SyllabusLesson_col3+" = "+lessonNo , null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
            l = null;
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {

                    l.setUnitNo(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col1)));
                    l.setLesson(cursor.getString(cursor.getColumnIndex(DBConstant.SyllabusLesson_col2)));
                    l.setLessonNo(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col3)));
                    l.setAmountTimeSpent(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col4)));
                    l.setSpecialACT(cursor.getString(cursor.getColumnIndex(DBConstant.SyllabusLesson_col5)));
                    l.setAmountCovered(cursor.getDouble(cursor.getColumnIndex(DBConstant.SyllabusLesson_col6)));
                    l.setAmountTimeSpent(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col7)));


                } while (cursor.moveToNext());
            }
        }
        return l;
    }

    @Override
    public int getLastDailyWorkID() {
        List<Integer> idList = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select " + DBConstant.DailyWork_col1 + " from DailyWork ORDER BY "+DBConstant.DailyWork_col1+" DESC LIMIT 1", null);
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
    public Lesson getLessonByClassIDandUnitIDandLessonNo(int ClassID, int UnitID, int LessonNo) {

        Lesson l = new Lesson();
        Cursor cursor = db.rawQuery("select * from SyllabusLesson inner join Unit on SyllabusLesson.Unit_id =Unit.UnitID where ClassID = "+ClassID+" and "+DBConstant.Unit_col3 +" = "+UnitID+" and "+DBConstant.SyllabusLesson_col3+" = "+LessonNo, null);

        if(cursor.getCount()==0){
            Log.d("MYACTIVITY", "No Value");
        }
        else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {

                    l.setUnitNo(cursor.getInt(cursor.getColumnIndex("Unit")));
                    l.setLesson(cursor.getString(cursor.getColumnIndex(DBConstant.SyllabusLesson_col2)));
                    l.setLessonNo(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col3)));
                    l.setAmountTimeSpent(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col4)));
                    l.setSpecialACT(cursor.getString(cursor.getColumnIndex(DBConstant.SyllabusLesson_col5)));
                    l.setAmountCovered(cursor.getDouble(cursor.getColumnIndex(DBConstant.SyllabusLesson_col6)));
                    l.setAmountTimeSpent(cursor.getInt(cursor.getColumnIndex(DBConstant.SyllabusLesson_col7)));

                } while (cursor.moveToNext());
            }
        }
        return l;
    }

    @Override
    public TutionClass getClassByClassID(int ClassID) {
        TutionClass tutionClass = new TutionClass();
        Cursor cursor = db.rawQuery("select * from TutionClass where "+DBConstant.tutionClass_col1+"="+String.valueOf(ClassID), null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            return null;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {

                    tutionClass.setClassID(Integer.parseInt(cursor.getString(0)));
                    tutionClass.setClassName(cursor.getString(1));
                    tutionClass.setStartDate(cursor.getString(2));
                    tutionClass.setEndDate(cursor.getString(3));
                    tutionClass.setDay(cursor.getString(4));
                    tutionClass.setTime(cursor.getString(5));
                    tutionClass.setFee(Integer.parseInt(cursor.getString(6)));

                } while (cursor.moveToNext());
            }
        }
        return tutionClass ;
    }

    @Override
    public List<ExtraClass> getExtraClassListByClassID(int ClassID) {
        List<ExtraClass> list = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select * from Extra_Class where Class_Id = "+ClassID, null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");
            idnext = 0;
        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {
                    ExtraClass extraClass = new ExtraClass();

                    extraClass.setExtraClassID(cursor.getInt(0));
                    extraClass.setClassID(cursor.getInt(1));
                    extraClass.setDate(cursor.getString(2));
                    extraClass.setTime(cursor.getString(3));
                    extraClass.setClassType(cursor.getString(4));

                    list.add(extraClass);

                } while (cursor.moveToNext());
            }

        }
        return list;
    }

    @Override
    public int addDailyWork(Work work) {


        ContentValues contentValues = new ContentValues();

        contentValues.put(DBConstant.DailyWork_col1,getLastDailyWorkID()+1);
        contentValues.put(DBConstant.DailyWork_col2,work.getDateOT());
        contentValues.put(DBConstant.DailyWork_col3,work.getUnitID());
        contentValues.put(DBConstant.DailyWork_col4,work.getLesson_No());
        contentValues.put(DBConstant.DailyWork_col5,work.getTimeTaken());
        contentValues.put(DBConstant.DailyWork_col6,work.getAmountCovered());
        contentValues.put(DBConstant.DailyWork_col7,work.getComment());

        long result = db.insert("DailyWork", null, contentValues);

        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO DailyWork ");
            return -1;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO DailyWork ");
            return 1;
        }
    }



}
