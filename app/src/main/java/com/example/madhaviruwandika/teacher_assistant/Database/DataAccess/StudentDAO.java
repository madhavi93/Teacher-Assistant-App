package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 3/27/2016.
 */
public interface StudentDAO {

        public List<Student> getStudentDetails();
        public int setStudentDetails(Student s);
        public int getnextSID();
        public Long addParent(Parent parent);
        public int getNextParentID();
        public long AddStudentClass(int ClassId, int SID);
        public List<Student> getStudentListByClasssID(int id);


}
