package com.example.madhaviruwandika.teacher_assistant.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * Created by Madhavi Ruwandika on 3/26/2016.
 */
public class DBCreator extends SQLiteOpenHelper {

        SQLiteDatabase db;
        public DBCreator(Context context) {

            super(context, DBConstant.DB_name,null,1);
            Log.d("MYACTIVITY", "Called Controller====================================================================================================================================");
            db= this.getWritableDatabase();
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.d("MYACTIVITY", "EXECUTING.......................................................................................................");
            /*
                * Createing tables in database
             */
            db.execSQL("CREATE TABLE Student (ID INTEGER PRIMARY KEY, Name TEXT NOT NULL, DateOfBirth TEXT , Address VARCHAR(100) );");
            db.execSQL("CREATE TABLE Parent (Parent_ID INTEGER PRIMARY KEY, S_id INTEGER NOT NULL, Name TEXT , TP_no TEXT ,email VARCHAR(100), FOREIGN KEY(S_id) REFERENCES Student(ID));");
            db.execSQL("CREATE TABLE Attend (S_id INTEGER, Class_Id INTEGER, FOREIGN KEY(S_id) REFERENCES Student(ID),FOREIGN KEY(Class_Id) REFERENCES TutionClass(ClassID));");
            db.execSQL("CREATE TABLE TutionClass (ClassID INTEGER PRIMARY KEY, ClassName TEXT NOT NULL UNIQUE, StartDate TEXT ,EndDate TEXT ,Day TEXT,Time TEXT,Fee REAL);");
            db.execSQL("CREATE TABLE StudentBehaviour (Record_id INTEGER PRIMARY KEY,S_id INTEGER, Class_Id INTEGER, Behaviour TEXT ,DateOfRec TEXT, FOREIGN KEY(S_id) REFERENCES Student(ID),FOREIGN KEY(Class_Id) REFERENCES TutionClass(ClassID));");
            db.execSQL("CREATE TABLE Payment (Payment_id INTEGER PRIMARY KEY,S_id INTEGER, Class_Id INTEGER, DateOP TEXT, FOREIGN KEY(S_id) REFERENCES Student(ID),FOREIGN KEY(Class_Id) REFERENCES TutionClass(ClassID));");
            db.execSQL("CREATE TABLE Extra_Class (ExClassID INTEGER PRIMARY KEY,Class_Id INTEGER, DateOfClass TEXT, time Text,ClassType Text, FOREIGN KEY(Class_Id) REFERENCES TutionClass(ClassID));");
            db.execSQL("CREATE TABLE Attendance_Sheet (Attendance_id INTEGER PRIMARY KEY,S_id INTEGER, Class_Id INTEGER, DateOPA TEXT, FOREIGN KEY(S_id) REFERENCES Student(ID),FOREIGN KEY(Class_Id) REFERENCES TutionClass(ClassID));");
            db.execSQL("CREATE TABLE SyllabusTopic (Topic_id INTEGER PRIMARY KEY, Class_Id INTEGER, Topic_level INTEGER, Topic Text,FOREIGN KEY(Class_Id) REFERENCES TutionClass(ClassID));");
            db.execSQL("CREATE TABLE ParentTopic (parent_Topic_id INTEGER, Child_Topic_Id INTEGER, FOREIGN KEY(Parent_Topic_id) REFERENCES SyllabusTopic(Topic_id ),FOREIGN KEY(Child_Topic_Id) REFERENCES SyllabusTopic(Topic_id));");
            db.execSQL("CREATE TABLE DailyWork (Daily_Work_ID INTEGER PRIMARY KEY, topic_ID TEXT NOT NULL UNIQUE, DateOT TEXT ,TimeSpend TEXT ,Content_Covered TEXT,  FOREIGN KEY(topic_ID) REFERENCES SyllabusTopic(Topic_id ));");
            db.execSQL("CREATE TABLE GroupMessage (Message_ID INTEGER PRIMARY KEY, Msg_type INTEGER, DateOfMsg TEXT ,Content TEXT ,Class_Id INTEGER, No_Of_Recip TEXT, FOREIGN KEY(Class_Id) REFERENCES TutionClass(ClassID));" );
            db.execSQL("CREATE TABLE SingleMessage(Message_ID INTEGER PRIMARY KEY, Msg_type INTEGER, DateOfMsg TEXT ,Content TEXT ,Recipient INTEGER);" );
            db.execSQL("CREATE TABLE Exam(Exam_ID INTEGER PRIMARY KEY, Class_ID INTEGER, InclassType TEXT ,DateOfExam TEXT ,Lesson TEXT, FOREIGN KEY(Class_ID) REFERENCES TutionClass(ClassID));" );
            db.execSQL("CREATE TABLE PerformedAt(Exam_ID INTEGER PRIMARY KEY,S_id INTEGER, Mark INTEGER , FOREIGN KEY(S_id) REFERENCES Student(ID));");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            /*
            Drop tables if they exist
             */
            db.execSQL("DROP TABLE IF EXISTS Student");
            db.execSQL("DROP TABLE IF EXISTS Parent");
            db.execSQL("DROP TABLE IF EXISTS Attend");
            db.execSQL("DROP TABLE IF EXISTS TutionClass");
            db.execSQL("DROP TABLE IF EXISTS StudentBehaviour");
            db.execSQL("DROP TABLE IF EXISTS Payment");
            db.execSQL("DROP TABLE IF EXISTS Extra_Class");
            db.execSQL("DROP TABLE IF EXISTS Attendance_Sheet");
            db.execSQL("DROP TABLE IF EXISTS SyllabusTopic");
            db.execSQL("DROP TABLE IF EXISTS ParentTopic");
            db.execSQL("DROP TABLE IF EXISTS DailyWork");
            db.execSQL("DROP TABLE IF EXISTS GroupMessage");
            db.execSQL("DROP TABLE IF EXISTS SingleMessage");
            db.execSQL("DROP TABLE IF EXISTS Exam");
            db.execSQL("DROP TABLE IF EXISTS PerformedAt");

            onCreate(db);
        }

}
