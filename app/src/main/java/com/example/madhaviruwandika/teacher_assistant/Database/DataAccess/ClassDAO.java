package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.Attendence;
import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Exam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public interface ClassDAO {

    public long addClass(TutionClass tutionClass);
    public int getClassID();
    public List<TutionClass> getClassDetails();
    public long addPayment(Payment payment);
    public int getPaymentID();
    public long addExtraClass(ExtraClass exClass);
    public int getExClassID();
    public TutionClass getClassByID(int id);
    public long addStudentMarks(ArrayList<String[]> s,int examID);
    public Long addExams(Exam exam);
    public List<Exam> getExamList();
    public List<Student> getStudentListByClasssID(int id);
    public long UpdateClass(TutionClass tutionClass);
    public List<Exam> getExamListByClassID(int class_id);
    public Long addDailyAttendence(ArrayList<Attendence> attendence_sheets);
    public int getAttendenceID();
    public long markStartingOfClass(int classID, String date,String Starttime,String endTime);

}
