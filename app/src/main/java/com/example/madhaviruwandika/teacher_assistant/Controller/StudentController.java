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
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Perfomance_analyser;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.Student_perfomance;
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
    Perfomance_analyser perfomance_analyser;

    public StudentController(Context context){

        sDAO = new StudentDA(context);
        perfomance_analyser = new Perfomance_analyser();
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

    public ArrayList<Map<String,String>> getExamListByClassID(int classID){

        List<Exam> exams = sDAO.getExamList(classID);
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

    public ArrayList<Map<String,String>> getExamListWithOutMarkSheetByClassID(int classID){

        List<Exam> exams = sDAO.getExamListWithOutMarkSheetByClassID(classID);
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

    public List<Integer> getDataForGraphOverallPerfomanceOfClass(int examID){
        List<Student_perfomance> list = sDAO.getStudentPerfomanceInExam(examID);
        return perfomance_analyser.getGraphDetailsForOverallPerfpomance(list);
    }

    public List<List<Integer>> getDataToStudentPerfomanceReport(int classID,int s_id){

        List<List<Integer>> data = new ArrayList<>();
        List<Exam> examList = sDAO.getExamList(classID);

        ArrayList<Integer> min = new ArrayList<>();
        ArrayList<Integer> max = new ArrayList<>();
        ArrayList<Integer> stdMark = new ArrayList<>();

        for (Exam exam : examList){
            List<Student_perfomance> list = sDAO.getStudentPerfomanceInExam(exam.getExamID());
            int[] minMAx = perfomance_analyser.getMaxAndMinOfTheExam(list);
            min.add(minMAx[0]);
            max.add(minMAx[1]);
            stdMark.add(sDAO.getStudentMarkOnExam(s_id,exam.getExamID()));

        }

        data.add(min);
        data.add(stdMark);
        data.add(max);
        return data;
    }

    public ArrayList<String> getExamNameListByClassID(int ClassID){

        List<Exam> examList = sDAO.getExamList(ClassID);
        ArrayList<String> examNameList = new ArrayList<>();
        for(Exam exam : examList){
            examNameList.add(exam.getDate()+"_"+exam.getLesson()+"_");
        }

        return examNameList;
    }

    public ArrayList<Integer> getExamMarkListOfStudent(int s_id){

        ArrayList<Integer> markList = new ArrayList<>();
        List<Student_perfomance> list = sDAO.getMarkListOfExamsBystudentID(s_id);
        Log.d("Mark",">>>>>>>>>>>>>>>>>>>>>>>>>>>>"+list.size()+"!!!!!!!!!!!!!!!!!!!!!!!!"+s_id+">>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for (Student_perfomance student_perfomance : list){
            markList.add(student_perfomance.getMark());
        }
        return markList;
    }

    public String getAttendenceState(int s_id,int class_id){

        int[] attendence = sDAO.getAttendenceOfStudent(s_id,class_id);
        String comment = perfomance_analyser.AttendenceState(attendence[0], attendence[1]);
        return "Student has "+((attendence[1]/attendence[0])*100)+"%. "+comment;

    }

    public List<Map<String,String>> getPayments(int s_id,int class_id){

        List<Payment> paymentList = sDAO.getPayments(s_id,class_id);
        Log.d("MYACTIVITY", ">>>>>>>>>>>>>>>>>>>>>>>>."+s_id+">>>"+class_id+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        List<Map<String,String>> list = new ArrayList<>();
        for (int i=0;i<paymentList.size();i++){
            Map<String,String> map = new HashMap<>();

            map.put("No",String.valueOf(i+1));
            map.put("date",paymentList.get(i).getDoP());
            map.put("month",paymentList.get(i).getMonthOfPayment());

            list.add(map);
        }

        return list;
    }


}
