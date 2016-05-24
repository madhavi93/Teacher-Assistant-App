package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.SyllabusDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.SyllabusDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.Model.LessonUnit;
import com.example.madhaviruwandika.teacher_assistant.Model.Work;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class IntelligenceSyllabusController {

    SyllabusDAO syllabusDAO;

    public IntelligenceSyllabusController(Context context) {
        syllabusDAO = new SyllabusDA(context);
    }

    public int addToSyllabus(int classID,int UnitID,int lessonNo, String lesson, int timePeriod,String specialAct){
        Log.d("My","=============================adding.........=============================================");
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

        for (int i=0;i<list.size();i++){
            Map<String,String> Unit =new HashMap<>();
            // assign values
            Unit.put("unitID",String.valueOf(list.get(i).getUnitID()));
            Unit.put("ClassID",String.valueOf(list.get(i).getClass_Id()));
            Unit.put("Unit",String.valueOf(list.get(i).getUnit()));

            units.add(Unit);
        }

        return units;
    }

    public int addDailyWork(int classID, int unitID, int LessonID, int timePeriod, Double amountCovered,String procedure){

        int Unit_row_id = syllabusDAO.getUnitIDByClassandUnit(classID,unitID);

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

            double currentCoveredAmount = syllabusDAO.getFinishedAmountOfLesson(unitID,LessonID);
            Log.d("MY","*************************************"+currentCoveredAmount+"****************************************");

            amountCovered += currentCoveredAmount;
            Log.d("MY","*************************************"+amountCovered+"****************************************");

            if(amountCovered>1.0){
                amountCovered = 1.0;
            }

            syllabusDAO.updateCoverdAmountsInSyllubas(unitID,LessonID,amountCovered);

            return 1;
        }
        else {
            return 0;
        }

    }

}
