package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.Student_perfomance;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

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
        public List<Exam> getExamList(int classID);
        public int getExamID();
        public List<Student_perfomance> getStudentPerfomanceInExam(int examID);
        public List<TutionClass> getClassDetails();
        public Student getStudent(int S_id);
        public int getAttendingClass(int S_id);
        public Parent getParent(int S_id);
        public long UpdateStudent(Student s,Parent p);
        public int getStudentMarkOnExam(int s_id,int examID);
        public List<Student_perfomance> getMarkListOfExamsBystudentID(int s_id);
        public int[] getAttendenceOfStudent(int s_id,int ClassID);
        public List<Payment> getPayments(int s_id,int class_id);


}
