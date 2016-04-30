package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.StudentDAO;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBCreator;
import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.StudentDA;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 3/27/2016.
 */
public class StudentController {

    private final SQLiteDatabase DBC;
    private StudentDAO  sDAO;

    public StudentController(Context context){
        DBC = DBConnection.getInstance(context).getWritableDatabase();
        sDAO = new StudentDA(context);
        Log.d("MYACTIVITY", "Called Controller====================================================================================================================================");
        //DBCreator dbCreater = new DBCreator(context);
    }

    public void addStudent(String name,String Dob, String address,String classname){
        Log.d("MYACTIVITY", "Called Controller====================================================================================================================================");
        Student s= new Student(name,Dob,address,classname);
        sDAO.setStudentDetails(s);

    }

    public void addParent(String name,int TpNo, String email){

    }

    public List<String> getDetails(){
        return sDAO.getStudentDetails();
    }


}
