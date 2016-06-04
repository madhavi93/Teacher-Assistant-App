package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.GroupMessage;
import com.example.madhaviruwandika.teacher_assistant.Model.Parent;
import com.example.madhaviruwandika.teacher_assistant.Model.SingleMessage;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemRegisterName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public interface CommunicationDAO {


    public Parent getParent(int s_id);
    public List<Parent> getParentList(List<Student> studentList);
    public List<Student> getStudentListByClassID(int Class_id);
    public List<TutionClass> getClassDetails();
    public List<Student> getStudentListByClasssID(int id);
    public long RecordOfSendingSingleMessage(SingleMessage m);
    public long RecordOfSendingGroupMessage(GroupMessage m);
    public int getMessageID(String mType);
    public ArrayList<ItemRegisterName> getTodaysRegister(int classID,String date);
}
