package com.example.madhaviruwandika.teacher_assistant.Database;

/**
 * Created by Madhavi Ruwandika on 3/26/2016.
 */
public class DBConstant {

    //DB name
    public static final String DB_name = "TeacherAssistant";

    //names of coloumn in tables
    public static final String stdTable_col1 = "ID";
    public static final String stdTable_col2 = "Name";
    public static final String stdTable_col3 = "DateOfBirth";
    public static final String stdTable_col4 = "Address";

    public static final String attend_col1 = "S_id";
    public static final String attend_col2 = "ClassID";

    public static final String tutionClass_col1 = "ClassID";
    public static final String tutionClass_col2 = "ClassName";
    public static final String tutionClass_col3=  "StartDate";
    public static final String tutionClass_col4 = "EndDate";
    public static final String tutionClass_col5 = "Day";
    public static final String tutionClass_col6 = "Time";
    public static final String tutionClass_col7 = "Fee";


    public static final String behaviour_col1 = "Payment_id";
    public static final String behaviour_col2 = "S_id";
    public static final String behaviour_col3 = "Class_Id";
    public static final String behaviour_col4 = "Behaviour";
    public static final String behaviour_col5 = "DateOfRec";

    public static final String payment_col1 = "Payment_id";
    public static final String payment_col2 = "S_id";
    public static final String payment_col3 = "Class_Id";
    public static final String payment_col4 = "DateOP";

    public static final String attendenceSheet_col1 = "Attendance_id";
    public static final String attendenceSheet_col2 = "S_id";
    public static final String attendenceSheet_col3 = "Class_Id";
    public static final String attendenceSheet_col4 = "DateOPA";

    public static final String syllabusTopic_col1 = "Topic_id";
    public static final String syllabusTopic_col2 = "Class_Id";
    public static final String syllabusTopic_col3 = "Topic_level";
    public static final String syllabusTopic_col4 = "Topic";

    public static final String parentTopic_col1 = "Parent_Topic_id";
    public static final String parentTopic_col2 = "Child_Topic_Id";

    public static final String DailyWork_col1 = "Daily_Work_ID";
    public static final String DailyWork_col2 = "topic_ID";
    public static final String DailyWork_col3 = "DateOT";
    public static final String DailyWork_col4 = "TimeSpend";

    public static final String GroupMsg_col1 = "Message_ID";
    public static final String GroupMsg_col2 = "Msg_type";
    public static final String GroupMsg_col3 = "DateOfMsg";
    public static final String GroupMsg_col4 = "Content";
    public static final String GroupMsg_col5 = "Class_Id";
    public static final String GroupMsg_col6 = "No_Of_Recip";

    public static final String SingleMsg_col1 = "Message_ID";
    public static final String SingleMsg_col2 = "Msg_type";
    public static final String SingleMsg_col3 = "DateOfMsg";
    public static final String SingleMsg_col4 = "Content";
    public static final String SingleMsg_col5 = "Recipient";

    public static final String parent_col1 = "Parent_ID ";
    public static final String parent_col2 = "S_id";
    public static final String parent_col3 = "Name";
    public static final String parent_col4 = "TP_no";
    public static final String parent_col5 = "email";

    public static final String exam_col1 = " Exam_ID";
    public static final String exam_col2 = "Class_ID";
    public static final String exam_col3 = "InclassType";
    public static final String exam_col4 = "DateOfExam";
    public static final String exam_col5 = "Lesson";


    public static final String ExtraClass_col1 = "ExClassID";
    public static final String ExtraClass_col2 = "Class_Id";
    public static final String ExtraClass_col3 = "DateOfClass";
    public static final String ExtraClass_col4 = "time";
    public static final String ExtraClass_col5 = "ClassType";


}
