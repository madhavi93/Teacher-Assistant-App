package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.SyllabusDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.SyllabusDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.Model.LessonUnit;

import java.util.ArrayList;
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
}
