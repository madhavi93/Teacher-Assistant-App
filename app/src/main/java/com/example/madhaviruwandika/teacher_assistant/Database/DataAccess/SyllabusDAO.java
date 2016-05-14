package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.Topic;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public interface SyllabusDAO {

    public int  AddTopic(Topic topic);
    public int getLastTopicId();
    public Topic getTopicByid(int ID);
    public int addToParentTopicSelation(int parentT,int childT);
}
