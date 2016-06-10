package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.SyllabusDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.SyllabusDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.Model.LessonUnit;
import com.example.madhaviruwandika.teacher_assistant.Model.Syllabus_Maneger;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Work;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class IntelligenceSyllabusController {

    SyllabusDAO syllabusDAO;
    Syllabus_Maneger syllabus_maneger;

    public IntelligenceSyllabusController(Context context) {
        syllabusDAO = new SyllabusDA(context);
        syllabus_maneger = new Syllabus_Maneger();
    }

    public int addToSyllabus(int classID,int UnitID,int lessonNo, String lesson, int timePeriod,String specialAct){
        LessonUnit lessonUnit = new LessonUnit(UnitID,classID);
        int UnitTID = syllabusDAO.addtoSylUnit(lessonUnit);
        if (UnitTID != 0){
            Lesson lesson1 = new Lesson(UnitTID,lessonNo,lesson,0.0,timePeriod,0,specialAct);

            if(syllabusDAO.addtoSylLesson(lesson1)!=-1){
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return 0;
        }
    }

    public List<Map<String,String>> getSyllabus(int ClassID){

        List<Map<String,String>> syllabus = new ArrayList<>();

        // retrive data from database
        List<Lesson> lessons = syllabusDAO.getLessonD(ClassID);

        for (int i=0;i<lessons.size();i++){
            Map<String,String> lesson =new HashMap<>();
            // assign values
            lesson.put("unitNo",String.valueOf(lessons.get(i).getUnitNo()));
            lesson.put("lessonNo",String.valueOf(lessons.get(i).getLessonNo()));
            lesson.put("Lesson",lessons.get(i).getLesson());
            lesson.put("amountCovered",String.valueOf(lessons.get(i).getAmountCovered()));
            lesson.put("totaltimeSupposedToSpend",String.valueOf(lessons.get(i).getTotaltimeSupposedToSpend()));
            lesson.put("AmountTimeSpent",String.valueOf(lessons.get(i).getAmountTimeSpent()));
            lesson.put("SpecialACT",lessons.get(i).getSpecialACT());

            syllabus.add(lesson);
        }

        return syllabus;
    }

    public List<Map<String,String>> getLessonListByUnitIDandClassID(int ClassID,int UnitID){

        List<Map<String,String>> syllabus = new ArrayList<>();

        // retrive data from database
        List<Lesson> lessons = syllabusDAO.getLessonListByClassIDandUnitID(ClassID, UnitID);

        for (int i=0;i<lessons.size();i++){
            Map<String,String> lesson =new HashMap<>();
            // assign values
            lesson.put("unitNo",String.valueOf(lessons.get(i).getUnitNo()));
            lesson.put("lessonNo",String.valueOf(lessons.get(i).getLessonNo()));
            lesson.put("Lesson",lessons.get(i).getLesson());
            lesson.put("amountCovered",String.valueOf(lessons.get(i).getAmountCovered()));
            lesson.put("totaltimeSupposedToSpend",String.valueOf(lessons.get(i).getTotaltimeSupposedToSpend()));
            lesson.put("AmountTimeSpent",String.valueOf(lessons.get(i).getAmountTimeSpent()));
            lesson.put("SpecialACT",lessons.get(i).getSpecialACT());
            syllabus.add(lesson);
        }

        return syllabus;
    }

    public List<Map<String,String>> getUnitListByClassID(int ClassID){

        List<Map<String,String>> units = new ArrayList<>();

        // retrive data from database
        List<LessonUnit> list = syllabusDAO.getUnitByClassID(ClassID);

        if(list != null) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> Unit = new HashMap<>();
                // assign values
                Unit.put("unitID", String.valueOf(list.get(i).getUnitID()));
                Unit.put("ClassID", String.valueOf(list.get(i).getClass_Id()));
                Unit.put("Unit", String.valueOf(list.get(i).getUnit()));

                units.add(Unit);
            }
        }

        return units;
    }

    public int addDailyWork(int classID, int unitID, int LessonID, int timePeriod, Double amountCovered,String procedure){

        int Unit_row_id = syllabusDAO.getUnitIDByClassandUnit(classID, unitID);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        Work work = new Work();

        work.setUnitID(Unit_row_id);
        work.setLesson_No(LessonID);
        work.setAmountCovered(amountCovered);
        work.setComment(procedure);
        work.setTimeTaken(timePeriod);
        work.setDateOT(dateFormat.format(cal.getTime()));

        if (syllabusDAO.addDailyWork(work)!= -1){

            Lesson l = syllabusDAO.getFinishedAmountOfLesson(unitID,LessonID);
            double currentCoveredAmount = l.getAmountCovered();
            int timeSpend = l.getAmountTimeSpent();
            Log.d("MY","*************************************"+currentCoveredAmount+"****************************************");

            amountCovered += currentCoveredAmount;
            timePeriod += timeSpend;
            Log.d("MY","*************************************"+timePeriod+"****************************************");

            if(amountCovered>1.0){
                amountCovered = 1.0;
            }

            syllabusDAO.updateCoverdAmountsInSyllubas(unitID,LessonID,amountCovered,timePeriod);

            return 1;
        }
        else {
            return 0;
        }

    }

    public Map<String,String> getLessonByClassIDandUnitIDandLessonNo(int ClassID,int UnitID,int LessonNo){
        Lesson mylesson= syllabusDAO.getLessonByClassIDandUnitIDandLessonNo(ClassID, UnitID, LessonNo);

        Map<String,String> lesson =new HashMap<>();
        // assign values
        lesson.put("unitNo",String.valueOf(mylesson.getUnitNo()));
        lesson.put("lessonNo", String.valueOf(mylesson.getLessonNo()));
        lesson.put("Lesson", mylesson.getLesson());
        lesson.put("amountCovered", String.valueOf(mylesson.getAmountCovered()));
        lesson.put("totaltimeSupposedToSpend", String.valueOf(mylesson.getTotaltimeSupposedToSpend()));
        lesson.put("AmountTimeSpent", String.valueOf(mylesson.getAmountTimeSpent()));
        lesson.put("SpecialACT", mylesson.getSpecialACT());

        return lesson;

    }

    public String[] getCommentOnWorkAndSyllabus(int ClassID,int UnitID,int lessonNO ){

        Lesson lesson = syllabusDAO.getLessonByClassIDandUnitIDandLessonNo(ClassID, UnitID, lessonNO);
        TutionClass tutionClass = syllabusDAO.getClassByClassID(ClassID);
        List<Lesson> lessons = syllabusDAO.getLessonD(ClassID);
        List<LessonUnit> unitList = syllabusDAO.getUnitByClassID(ClassID);
        int extraClassTime = extraClassTimeReleventToOneClass(ClassID);

        if(lessons.size()!= 0 && unitList.size()!=0 ){
            return syllabus_maneger.getComment(unitList,lessons,lesson,tutionClass,extraClassTime);
        }
        else {
            String[] s = new String[2];
            s[0] = "";
            s[1] = "";
            return s;
        }
    }

    public String[] getCommentOnWorkAndSyllabus(int ClassID ){
        Lesson lesson = null;
        TutionClass tutionClass = syllabusDAO.getClassByClassID(ClassID);
        List<Lesson> lessons = syllabusDAO.getLessonD(ClassID);
        List<LessonUnit> unitList = syllabusDAO.getUnitByClassID(ClassID);
        int extraClassTime = extraClassTimeReleventToOneClass(ClassID);

        if(lessons.size()!= 0 && unitList.size()!=0 ){
            return syllabus_maneger.getComment(unitList,lessons,lesson,tutionClass,extraClassTime);
        }
        else {
            String[] s = new String[2];
            s[0] = "";
            s[1] = "";
            return s;
        }
    }

    public int extraClassTimeReleventToOneClass(int ClassID) {

        int extraClassTime = 0;
        List<ExtraClass> extraClassList = syllabusDAO.getExtraClassListByClassID(ClassID);
        for (ExtraClass extraClass : extraClassList) {
            String time = extraClass.getTime();
            String[] StartEnd = time.split("-");
            extraClassTime += getTimePeriod(StartEnd[0],StartEnd[1]);
        }
        return extraClassTime;
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
        }
        else if(StartTime.substring(5).equals("pm") ){
            int x = Integer.parseInt(StartTime.substring(0,2));
            x += 12;
            formatStart_24 = x+StartTime.substring(2,5);

        }

        if(EndTime.substring(5).equals("am")){
            formatEnd_24 = EndTime.substring(0,3);//12.23am
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

        try {
            d1 = format.parse(formatStart_24);
            d2 = format.parse(formatEnd_24);
            //in milliseconds
            diff = d2.getTime() - d1.getTime();

            diffMinutes = diff / (60*1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;

            diffMinutes += diffMinutes+ (diffHours*60);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return (int)(long)diffMinutes ;
    }


}
