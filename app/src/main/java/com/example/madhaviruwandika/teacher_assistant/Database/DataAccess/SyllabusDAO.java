package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.Model.LessonUnit;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Work;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public interface SyllabusDAO {

    public int addtoSylUnit(LessonUnit lessonUnit);
    public int addtoSylLesson(Lesson lesson);
    public int getUnitTID();
    public List<Lesson> getLessonD(int ClassID);
    public  List<LessonUnit> getUnitByClassID(int ClasID);
    public List<Lesson> getLessonListByClassIDandUnitID(int classID,int UnitID);
    public int addDailyWork(Work work);
    public int getUnitIDByClassandUnit(int classID,int UnitId);
    public long updateCoverdAmountsInSyllubas(int UnitID,int lessonNo,Double amountCovered,int timeSpent);
    public Lesson getFinishedAmountOfLesson(int UnitID,int lessonNo);
    public int getLastDailyWorkID();
    public Lesson getLessonByClassIDandUnitIDandLessonNo(int ClassID,int UnitID,int LessonNo);
    public TutionClass getClassByClassID(int ClassID);
    public List<ExtraClass> getExtraClassListByClassID(int ClassID);

}
