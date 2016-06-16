package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.Exam;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.Student_perfomance;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public interface ReporterDAO {

    public int getExamID();
    public int getStudentMarkOnExam(int s_id,int examID);
    public List<Student_perfomance> getMarkListOfExamsBystudentID(int s_id);
    public int[] getAttendenceOfStudent(int s_id,int ClassID);
    public List<Payment> getPayments(int s_id,int class_id);
    public List<Exam> getExamListWithOutMarkSheetByClassID(int classID );
    public List<Exam> getExamsThatMArksAreEntered(int classID);
    public List<Student_perfomance> getStudentPerfomanceInExam(int examID);
    public Student getStudent(int S_id);

}
