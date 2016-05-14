package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/2/2016.
 */
public class Topic {

    private int topic_id;
    private int class_Id;
    private int topic_level;
    private String topic;
    private int parentTopicID;


    public Topic() {
    }

    public Topic(int topic_id, int class_Id, int topic_level, String topic, int parentTopicID) {
        this.topic_id = topic_id;
        this.class_Id = class_Id;
        this.topic_level = topic_level;
        this.topic = topic;
        this.parentTopicID = parentTopicID;
    }

    public Topic(int class_Id, int topic_level, String topic, int parentTopicID) {
        this.class_Id = class_Id;
        this.topic_level = topic_level;
        this.topic = topic;
        this.parentTopicID = parentTopicID;
    }

    public int getClass_Id() {
        return class_Id;
    }

    public void setClass_Id(int class_Id) {
        class_Id = class_Id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        topic_id = topic_id;
    }

    public int getTopic_level() {
        return topic_level;
    }

    public void setTopic_level(int topic_level) {
        topic_level = topic_level;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        topic = topic;
    }

    public int getParentTopicID() {
        return parentTopicID;
    }

    public void setParentTopicID(int parentTopicID) {
        this.parentTopicID = parentTopicID;
    }
}

