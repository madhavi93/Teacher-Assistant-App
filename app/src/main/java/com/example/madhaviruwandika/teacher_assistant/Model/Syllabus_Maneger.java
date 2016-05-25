package com.example.madhaviruwandika.teacher_assistant.Model;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 5/22/2016.
 */
public class Syllabus_Maneger {
    // syllabus inteligence which analize the time limit and give comment
    private Syllabus_Inteligence syllabus_inteligence;

    public Syllabus_Maneger(){
        this.syllabus_inteligence = Syllabus_Inteligence.getInstance();
    }

    public String[] getComment(List<LessonUnit> units,List<Lesson> lessons,Lesson lesson,TutionClass tutionClass,int extraClasseTime){
        return syllabus_inteligence.giveComment(units, lessons, lesson, tutionClass, extraClasseTime);
    }

}
