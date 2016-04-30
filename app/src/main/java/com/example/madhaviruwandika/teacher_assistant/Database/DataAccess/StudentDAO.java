package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.Student;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 3/27/2016.
 */
public interface StudentDAO {

        public List<String> getStudentDetails();
        public long setStudentDetails(Student s);

}
