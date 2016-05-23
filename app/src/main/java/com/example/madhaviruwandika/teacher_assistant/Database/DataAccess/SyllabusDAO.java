package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.Model.LessonUnit;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public interface SyllabusDAO {

    public int addtoSylUnit(LessonUnit lessonUnit);
    public int addtoSylLesson(Lesson lesson);
    public int getUnitTID();
    public List<Lesson> getLessonD(int ClassID);
}
