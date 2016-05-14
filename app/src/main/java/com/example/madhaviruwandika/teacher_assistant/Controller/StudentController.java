package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.StudentDAO;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBCreator;
import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.StudentDA;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 3/27/2016.
 */
public class StudentController {


    private StudentDAO  sDAO;

    public StudentController(Context context){

        sDAO = new StudentDA(context);
        Log.d("MYACTIVITY", "Called Student Controller");
        //DBCreator dbCreater = new DBCreator(context);
    }

    public int addStudent(String name,String Dob, String address,int classId){

        Log.d("MYACTIVITY", "Called Student Controller for add student");
        Student s= new Student(name,Dob,address,classId);
        int id = sDAO.setStudentDetails(s);
        return id;
    }

    public void addParent(int SID,String name,String TpNo, String email){

        Log.d("MYACTIVITY", "Called Student Controller for add parent");
        Parent parent = new Parent(SID,name,TpNo,email);
        sDAO.addParent(parent);

    }

    public void addStudentClass(int SID, int ClassID){

        Log.d("MYACTIVITY", "Called Student Controller for Student-Class");
        sDAO.AddStudentClass(ClassID, SID);

    }
    public List<Student> getDetails(){
        return sDAO.getStudentDetails();
    }

    public List<Student> getStudentListByClassID(int id){
        return sDAO.getStudentListByClasssID(id);
    }




}
