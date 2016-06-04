package com.example.madhaviruwandika.teacher_assistant.Model;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 5/25/2016.
 */
public class SyllabusTreeTest extends TestCase {

    private SyllabusTree syllabusTree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        syllabusTree = new SyllabusTree();
        List<LessonUnit> unitLesson = new ArrayList<>();
        List<Lesson> lessons = new ArrayList<>();

        unitLesson.add(new LessonUnit(1,1));
        unitLesson.add(new LessonUnit(2,1));
        unitLesson.add(new LessonUnit(3,1));

        lessons.add(new Lesson(1,1,"adding",0.50,120,60,"Group activity" ));
        lessons.add(new Lesson(1,2,"substraction",0.25,120,60,"Group activity" ));
        lessons.add(new Lesson(1,3,"Multiply",0.00,180,00,"Group activity" ));
        lessons.add(new Lesson(2,1,"MyFriend",0.00,120,0,"Group activity" ));
        lessons.add(new Lesson(2,2,"School",0.00,120,00,"Group activity" ));
        lessons.add(new Lesson(3,1,"Static",0.00,120,00,"Group activity" ));
        lessons.add(new Lesson(1,2,"Dynamic",0.00,120,00,"Group activity" ));
        syllabusTree.fillTree(unitLesson,lessons);

    }

    public void testGetRequiredTimeToCoverRemaining() throws Exception {
        assertEquals(810,syllabusTree.getRequiredTimeToCoverRemaining());

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}