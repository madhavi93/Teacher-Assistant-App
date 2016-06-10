package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.ClassDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.ClassDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.AppConstant;
import com.example.madhaviruwandika.teacher_assistant.Model.Attendence;
import com.example.madhaviruwandika.teacher_assistant.Model.ClassTimer;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Perfomance_analyser;
import com.example.madhaviruwandika.teacher_assistant.Model.StartOfClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemRegisterName;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class ClassController {

    public ClassDAO classDAO;
    Context context;

    public ClassController(Context context) {
        classDAO = new ClassDA(context);
        this.context = context;
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

    public int addPayment(int SID,int CID,String month){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String Date = sdf.format(calendar.getTime());

        Payment p = new Payment(SID,CID,Date,month);
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

    public int addInclassMarks(ArrayList<String[]> s,int eID,int markRange){

        for (int i=0;i<s.size();i++){
            Log.d("MarkController",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+s.get(i)[1]+">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
        ArrayList<String[]> ml = Perfomance_analyser.convertToMarkRangeOutOf100(s,markRange);
        for (int i=0;i<s.size();i++){
            Log.d("MarkController",">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+s.get(i)[1]+">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }

        if(classDAO.addStudentMarks(ml, eID)!=-1){
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

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime()).toString();
        AppConstant.getInstance().setFinishClassDate(date);

        ArrayList<Attendence> attendence_sheet = new ArrayList<>();
        for (int i=0;i< list.size();i++){
            //get Today's date
            Attendence attendence = new Attendence();
            attendence.setS_id(list.get(i).getSID());
            attendence.setClass_Id(ClassID);
            attendence.setDateOPA(date);
            attendence.setAttendenceState(list.get(i).getAttendence());

            attendence_sheet.add(attendence);

        }

        if(classDAO.addDailyAttendence(attendence_sheet)!= -1){
            AppConstant.getInstance().setMarkedAttendence(true);
            return 1;
        }
        else {
            AppConstant.getInstance().setMarkedAttendence(false);
            return 0;
        }
    }

    public int MarkStartingOfTheClass(int ClasID){

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String Date = sdf.format(calendar.getTime());

        SimpleDateFormat timeFf = new SimpleDateFormat("hh.mm");
        String Stime = timeFf.format(calendar.getTime());

        if(classDAO.markStartingOfClass(ClasID, Date, Stime, "")!= -1){

            TutionClass tutionClass = classDAO.getClassByID(ClasID);
            AppConstant.getInstance().setClassContinuing(tutionClass);
            AppConstant.getInstance().setcontinuing_class(true);
            AppConstant.getInstance().setMarkedAttendence(false);
            // get class time
            String[] classTime = tutionClass.getTime().split("-");
            int myclassTime = getTimePeriod(classTime[0],classTime[1]);
            // start timer that update app constant data regarding to class starting
            new ClassTimer(myclassTime,ClasID,context);

            return 1;
        }
        else return 0;

    }

    public int getTimePeriod(String StartTime, String EndTime){

        if(StartTime.length()==6){
            StartTime = '0'+StartTime;
        }
        if(EndTime.length() == 6){
            EndTime = '0'+EndTime;
        }
        String formatStart_24 = "";
        String formatEnd_24 = "";

        long diff = 0;
        if(StartTime.substring(5).equals("am")){
            formatStart_24 = StartTime.substring(0,5);
            if(Integer.parseInt(formatStart_24.substring(0,2))>12){
                formatStart_24 = "";
            }
        }
        else if(StartTime.substring(5).equals("pm") ){
            int x = Integer.parseInt(StartTime.substring(0,2));
            x += 12;
            formatStart_24 = x+StartTime.substring(2,5);

        }
        if(EndTime.substring(5).equals("am")){
            formatEnd_24 = EndTime.substring(0,5);//12.23am
            if(Integer.parseInt(formatEnd_24.substring(0,2))>12){
                formatEnd_24 = "";
            }
        }

        else if(EndTime.substring(5).equals("pm") ){
            int x = Integer.parseInt(EndTime.substring(0, 2));
            x += 12;
            formatEnd_24 = x+EndTime.substring(2,5);

        }

        SimpleDateFormat format = new SimpleDateFormat("HH.mm");
        Date d1 = null;
        Date d2 = null;
        long diffMinutes = 0;
        long diffHours = 0;

        try {
            d1 = format.parse(formatStart_24);
            d2 = format.parse(formatEnd_24);
            //in milliseconds

            diff = d2.getTime() - d1.getTime();
            if(diff>0) {
                diffMinutes = diff / (60 * 1000) % 60;
                diffHours = diff / (60 * 60 * 1000) % 24;

                diffMinutes += diffMinutes+ (diffHours*60);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return (int)(long)diffMinutes ;
    }

    public void getTodaysClassList() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String Date = sdf.format(calendar.getTime());

        List<StartOfClass> classList = classDAO.getTodaysClassList(Date);
        if (classList.size() != 0) {
            for (StartOfClass startOfClass : classList) {

                if (startOfClass.getEnd_Time() == "") {
                    AppConstant.getInstance().setClassContinuing(classDAO.getClassByID(startOfClass.getClassID()));
                    AppConstant.getInstance().setMarkedAttendence(false);
                    AppConstant.getInstance().setcontinuing_class(true);
                } else {

                }

            }
        }
    }

    public int markFinishingOftheClass(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String Date = sdf.format(calendar.getTime());

        SimpleDateFormat timeFf = new SimpleDateFormat("hh.mm");
        String Etime = timeFf.format(calendar.getTime());

        String StartTime = AppConstant.getInstance().getClassContinuing().getTime().split("-")[0];
        String currentClassSTime = "";
        if(StartTime.length()==6){
            StartTime = '0'+StartTime;
        }

        if(StartTime.substring(5).equals("am")){
            currentClassSTime = StartTime.substring(0,5);
            if(Integer.parseInt(currentClassSTime.substring(0,2))>12){
                currentClassSTime = "";
            }
        }
        else if(StartTime.substring(5).equals("pm") ){
            int x = Integer.parseInt(StartTime.substring(0,2));
            x += 12;
            currentClassSTime = x+StartTime.substring(2,5);
        }
        StartOfClass startOfClass = new StartOfClass(Date,AppConstant.getInstance().getClassContinuing().getClassID(), currentClassSTime,Etime);
        if(classDAO.markFinishingOfTheClass(startOfClass) != -1){
            return 1;
        }
        else {
            return 0;
        }

    }

    public List<Map<String,String>> getExtraClassByID( int classID){

        List<ExtraClass> list = classDAO.getExtraClassByID(classID);
        List<Map<String,String>> eClassList = new ArrayList<>();
        for (ExtraClass extraClass: list){
            Map<String,String> extraClassD = new HashMap<>();
            extraClassD.put("extraClassID",String.valueOf(extraClass.getExtraClassID()));
            extraClassD.put("time",extraClass.getTime());
            extraClassD.put("date",extraClass.getDate());
            extraClassD.put("type",extraClass.getClassType());
            extraClassD.put("state",extraClass.getClassState());

            eClassList.add(extraClassD);
        }
        return eClassList;
    }

    public int CancelExtraClass(int ExtraClassID){
        if(classDAO.CancelExtraClass(ExtraClassID) != -1){
            return 1;
        }
        else {
            return 0;
        }
    }

    public int DeleteClass(int classID){

        if(classDAO.DeleteClass(classID)!= -1){
            return 1;
        }
        else {
            return 0;
        }

    }

}
