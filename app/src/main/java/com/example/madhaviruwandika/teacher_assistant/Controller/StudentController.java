package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.StudentDAO;
import com.example.madhaviruwandika.teacher_assistant.Database.DBConnection;
import com.example.madhaviruwandika.teacher_assistant.Database.DBCreator;
import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.StudentDA;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String,String> getStudentBySid(int s_id){

        Student s = sDAO.getStudent(s_id);
        Map<String,String> student = new HashMap<>();

        student.put("S_ID",String.valueOf(s.getS_id()));
        student.put("name",s.getName());
        student.put("address",s.getAddress());
        student.put("DOB",s.getDoB());
        student.put("Class_Id",String.valueOf(s.getClassID()));

        Parent p = sDAO.getParent(s_id);
        student.put("parentID",String.valueOf(p.getParentID()));
        student.put("ParentName",p.getName());
        student.put("TPNo", p.getTp_no());
        student.put("email", p.getEmail());

        return student;

    }

    public List<Student> getDetails(){
        return sDAO.getStudentDetails();
    }

    public List<String[][]> getStudentListByClassID(int id){
        List<Student> studentList= sDAO.getStudentListByClasssID(id);
        List<String[][]> studentListD = new ArrayList<>();

        for (Student s : studentList){
            String[][] student = new String[1][2];
            student[0][0] = String.valueOf(s.getS_id());
            student[0][1] = s.getName();

            studentListD.add(student);
        }
        return studentListD;
    }

    public ArrayList<Map<String,String>> getExamList(){

        List<Exam> exams = sDAO.getExamList();
        ArrayList<Map<String,String>> examD = new ArrayList<>();

        for (Exam e : exams){
            Map<String,String> exam = new HashMap<>();
            exam.put("ExamID",String.valueOf(e.getExamID()));
            exam.put("ClassID",String.valueOf(e.getClassID()));
            exam.put("date",e.getDate());
            exam.put("Etype",e.getEtype());
            exam.put("lesson",e.getLesson());

            examD.add(exam);
        }
        return examD;
    }

    public List<TutionClass> getClassList(){
        List<TutionClass> classes = sDAO.getClassDetails();
        if(classes!= null){
            return classes;
        }
        else {
            Log.d("MYACTIVITY","No Class is added yet" );
            return null;
        }
    }

    public List<String> getClassListForSpinner(){
        //get class list from the database
        List<TutionClass> classList= getClassList();

        //create list of class names
        List<String> categories = new ArrayList<String>();
        categories.add("");
        for(int i=0;i<classList.size();i++){
            categories.add(classList.get(i).getClassName());
        }
        return categories;
    }

    public int getClassIDBySpinnerItemSelected(int position){
        //get class list from the database
        List<TutionClass> classList= getClassList();
        if(position > 0){
            return classList.get(position-1).getClassID();
        }
        else return 0;
    }

    public int UpdateStudent(Map<String,String> student){

        Student s = new Student();
        s.setS_id(Integer.parseInt(student.get("S_ID")));
        s.setName(student.get("name"));
        s.setDoB(student.get("DOB"));
        s.setAddress(student.get("address"));
        s.setClassID(Integer.parseInt(student.get("Class_Id")));

        Parent p = new Parent();
        p.setParentID(Integer.parseInt(student.get("parentID")));
        p.setName(student.get("ParentName"));
        p.setEmail(student.get("email"));
        p.setTp_no(student.get("TPNo"));

        if(sDAO.UpdateStudent(s,p)==1){
            return 1;
        }
        else  return 0;

    }

}
