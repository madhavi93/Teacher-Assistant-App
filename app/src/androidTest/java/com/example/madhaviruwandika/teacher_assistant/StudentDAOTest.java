package com.example.madhaviruwandika.teacher_assistant;

import android.content.Context;
import android.test.InstrumentationTestCase;



import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.StudentDA;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;

/**
 * Created by Madhavi Ruwandika on 4/17/2016.
 */
public class StudentDAOTest extends InstrumentationTestCase {

    Student student1;
    Student student2;
    Student student3;
    @Override
    protected void setUp() throws Exception {
        super.setUp();


    }

    public void testSaveStudent(){
        Context context = getInstrumentation().getContext();
        StudentDA studentDA = new StudentDA(context);

        student1 = new Student("nimali","10-12-1993","www,eee","A1");
        student2 = new Student("madhavi","11/01/1994","Sumudu,Ambalangoda","B");
        student3 = new Student("prasadi weerasuriya" ,"", "nimalsiri,Balangoda" , "d");

        assertEquals(1,studentDA.setStudentDetails(student1));
        assertEquals(1,studentDA.setStudentDetails(student2));
        assertEquals(1,studentDA.setStudentDetails(student3));

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    }
