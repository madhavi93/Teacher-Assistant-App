package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.ClassDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.ClassDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Attendence;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemRegisterName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class ClassController {

    public ClassDAO classDAO;

    public ClassController(Context context) {
        classDAO = new ClassDA(context);
    }

    public int AddClass(String cname ,String time , String day, String startingDate,String EndDate,double fee){


        TutionClass tutionClass = new TutionClass(cname,startingDate , day, time, EndDate, fee);
        if(classDAO.addClass(tutionClass)==-1){
            return 0;
        }
        else {
            return 1;
        }

    }

    public List<TutionClass> getClassList(){
        List<TutionClass> classes = classDAO.getClassDetails();
        if(classes!= null){
            return classes;
        }
        else {
            Log.d("MYACTIVITY","No Class is added yet" );
            return null;
        }
    }

    public int addPayment(int SID,int CID){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy");
        String Date = sdf.format(calendar.getTime());

        Payment p = new Payment(SID,CID,Date);
        long r = classDAO.addPayment(p);

        if(r== -1){
            return 0;
        }
        else {return 1;}

    }

    public int ExtraAddClass(int cID ,String time , String Date,String classType){

        ExtraClass ExClass = new ExtraClass(cID,Date,time,classType);
        if(classDAO.addExtraClass(ExClass)==-1){
            return 0;
        }
        else {
            return 1;
        }

    }

    public Map<String ,String> getTutionClassByID(int id){

        Map<String, String> myClass = new HashMap<String, String>();

        if(id != 0) {
            TutionClass tutionClass = classDAO.getClassByID(id);

            myClass.put("ClassID", String.valueOf(tutionClass.getClassID()));
            myClass.put("ClassName", tutionClass.getClassName());
            myClass.put("StartDate", tutionClass.getStartDate());
            myClass.put("EndDate", tutionClass.getEndDate());
            myClass.put("Day", tutionClass.getDay());
            myClass.put("Time", tutionClass.getTime());
            myClass.put("fee", String.valueOf(tutionClass.getFee()));
        }
        return myClass;

    }

    public int addInclassMarks(ArrayList<String[]> s,int eID){

        if(classDAO.addStudentMarks(s, eID)!=-1){
            return 1;
        }
        else return 0;
    }

    public int addExams(int ClassID, String date, String EType,String lesson){

        Exam exam = new Exam(ClassID,date,EType,lesson);
        if(classDAO.addExams(exam)!= -1){
            return 1;
        }
        else return 0;
    }

    public ArrayList<Map<String,String>> getExamList(){

        List<Exam> exams = classDAO.getExamList();
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

    public int UpdateClass(Map<String,String> myClass){

        TutionClass tutionClass = new TutionClass();

        try {
            tutionClass.setClassID(Integer.parseInt(myClass.get("ClassID")));
            tutionClass.setClassName(myClass.get("ClassName"));
            tutionClass.setStartDate(myClass.get("StartDate"));
            tutionClass.setEndDate(myClass.get("EndDate"));
            tutionClass.setDay(myClass.get("Day"));
            tutionClass.setTime(myClass.get("Time"));
            tutionClass.setFee(Double.parseDouble(myClass.get("fee")));
            if(classDAO.UpdateClass(tutionClass)!= -1){
                return 1;
            }
            else {
                return 0;
            }
        }
        catch (NumberFormatException e){
            return 0;
        }
    }

    public List<Map<String,String>> getExamListByID(int class_id) {

        List<Exam> exams = classDAO.getExamListByClassID(class_id);
        List<Map<String,String>> examD = new ArrayList<>();

        for (Exam e : exams){
            Map<String,String> exam = new HashMap<>();
            exam.put("ExamID",String.valueOf(e.getExamID()));
            exam.put("ClassID",String.valueOf(e.getClassID()));
            exam.put("date", e.getDate());
            exam.put("Etype",e.getEtype());
            exam.put("lesson",e.getLesson());
            examD.add(exam);
        }
        return examD;

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

    public List<String[][]> getStudentListByClassID(int id){
        List<Student> studentList= classDAO.getStudentListByClasssID(id);
        List<String[][]> studentListD = new ArrayList<>();

        for (Student s : studentList){
            String[][] student = new String[1][2];
            student[0][0] = String.valueOf(s.getS_id());
            student[0][1] = s.getName();

            studentListD.add(student);
        }
        return studentListD;
    }

    public int addAttendence(List<ItemRegisterName> list, int ClassID){

        ArrayList<Attendence> attendence_sheet = new ArrayList<>();
        for (int i=0;i< list.size();i++){
            //get Today's date
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();

            Attendence attendence = new Attendence();
            attendence.setS_id(list.get(i).getSID());
            attendence.setClass_Id(ClassID);
            attendence.setDateOPA(dateFormat.format(cal.getTime()));
            attendence.setAttendenceState(list.get(i).getAttendence());

            attendence_sheet.add(attendence);

        }

        if(classDAO.addDailyAttendence(attendence_sheet)!= -1){
            return 1;
        }
        else return 0;
    }

    public int MarkStartingOfTheClass(int ClasID){

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd:MMMM:yyyy");
        String Date = sdf.format(calendar.getTime());

        SimpleDateFormat timeFf = new SimpleDateFormat("hh:mm");
        String Stime = timeFf.format(calendar.getTime());

        if(classDAO.markStartingOfClass(ClasID,Date,Stime,"")!= -1){
            return 1;
        }
        else return 0;

    }

}
