package com.example.madhaviruwandika.teacher_assistant.Adapter.DA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConstant;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.SyllabusDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Topic;
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

    @Override
    public int AddTopic(Topic topic) {

        // set values to contentValues
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.syllabusTopic_col1, getLastTopicId()+1);
        contentValues.put(DBConstant.syllabusTopic_col2, topic.getClass_Id());
        contentValues.put(DBConstant.syllabusTopic_col3,topic.getTopic_level());
        contentValues.put(DBConstant.syllabusTopic_col4,topic.getTopic());


        long result = db.insert("SyllabusTopic", null, contentValues);

        if( topic.getTopic_level() != 0){
            addToParentTopicSelation(topic.getParentTopicID(),topic.getTopic_id());
        }


        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO SyllabusTopic");
            return 0;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO SyllabusTopic ");
            Log.d("MYACTIVITY", String.valueOf(getLastTopicId()));
            return getLastTopicId();
        }
    }

    @Override
    public int getLastTopicId() {
        List<Integer> idList = new ArrayList<>();
        int idnext;
        Cursor cursor = db.rawQuery("select " + DBConstant.syllabusTopic_col1 + " from SyllabusTopic ", null);
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
    public Topic getTopicByid(int ID) {
        Topic topic = new Topic();

        Cursor cursor = db.rawQuery("select * from SyllabusTopic where "+DBConstant.syllabusTopic_col1+"="+String.valueOf(ID), null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");

        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {

                    topic.setTopic_id(ID);
                    topic.setClass_Id(Integer.parseInt(cursor.getString(1)));
                    topic.setTopic_level(Integer.parseInt(cursor.getString(2)));
                    topic.setTopic(cursor.getString(3));

                } while (cursor.moveToNext());
            }
        }

        Cursor cursor2 = db.rawQuery("select * from ParentTopic where "+DBConstant.parentTopic_col2+"="+String.valueOf(ID), null);
        if (cursor.getCount() == 0) {
            Log.d("MYACTIVITY", "No Value");

        } else {
            //iterate through result set
            if (cursor.moveToFirst()) {
                do {

                    topic.setParentTopicID(Integer.parseInt(cursor.getString(0)));

                } while (cursor.moveToNext());
            }
        }


        return topic ;



    }


    public int addToParentTopicSelation(int parentT,int childT) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBConstant.parentTopic_col1, parentT);
        contentValues.put(DBConstant.parentTopic_col2, childT );



        long result = db.insert("ParentTopic", null, contentValues);
        if(result == -1){
            Log.d("MYACTIVITY", "VALUES ARE NOT_INSERTED TO ParentTopic");
            return 0;
        }
        else {
            Log.d("MYACTIVITY", "VALUES ARE INSERTED TO parentTTopic ");
            Log.d("MYACTIVITY", String.valueOf(getLastTopicId()));
            return getLastTopicId();
        }

    }


    // CREATE TABLE SyllabusTopic (Topic_id INTEGER PRIMARY KEY, Class_Id INTEGER, Topic_level INTEGER, Topic Text,FOREIGN KEY(Class_Id) REFERENCES TutionClass(ClassID));");

}
