package com.example.madhaviruwandika.teacher_assistant.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Madhavi Ruwandika on 3/26/2016.
 */
public class DBConnection extends SQLiteOpenHelper {

    private static DBConnection instance;
    public SQLiteDatabase db = null;

    public DBConnection(Context context) {
        super(context,DBConstant.DB_name,null,1);
    }


    public static DBConnection getInstance(Context context){
        if (instance == null)
        {
            synchronized(DBConnection.class)
            {
                if (instance == null)
                { instance = new DBConnection(context);	}
            }
        }
        return instance;
    }

    public SQLiteDatabase getSQLiteDB(){
        return db;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
