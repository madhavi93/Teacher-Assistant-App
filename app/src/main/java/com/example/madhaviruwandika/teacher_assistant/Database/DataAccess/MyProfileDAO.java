package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.MyProfile;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 5/31/2016.
 */
public interface MyProfileDAO {

    public long saveProfileData(MyProfile myProfile);
    public MyProfile getProfileData();
    public long updateProfileData(MyProfile myProfile);
    public List<TutionClass> getClassList();
    public long resetApp();

}
