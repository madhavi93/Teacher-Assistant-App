package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Adapter.DA.MyProfileDA;
import com.example.madhaviruwandika.teacher_assistant.Database.DataAccess.MyProfileDAO;
import com.example.madhaviruwandika.teacher_assistant.Model.MyProfile;
import com.example.madhaviruwandika.teacher_assistant.Model.PasswardEncryptor;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Madhavi Ruwandika on 6/1/2016.
 */
public class MyProfileController {

    MyProfileDAO myProfileDAO;
    PasswardEncryptor passwardEncryptor ;
    public MyProfileController(Context context){
        myProfileDAO = new MyProfileDA(context);
        passwardEncryptor = new PasswardEncryptor(context);
    }

    public int SaveProfileDataFirstTime(String name,String passward){
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String Date = sdf.format(calendar.getTime());

        String passwardEncrypt = passwardEncryptor.encryptString(passward);
        MyProfile myProfile = new MyProfile(name,passwardEncrypt,Date);

        if(myProfileDAO.saveProfileData(myProfile)!= -1){

            Log.d("PAssword",">>>>>>>>>>>>>>"+name+">>>>>>>>>>"+passward+">>>>>>>>>>>>>>>>>>>>>>>");
            return 1;
        }
        else{
            return 0;
        }
    }

    public int UpdateMyprofile(String name){
        MyProfile myProfile = myProfileDAO.getProfileData();
        myProfile.setName(name);

        if(myProfileDAO.updateProfileData(myProfile) != -1){
            return -1;
        }
        else {
            return 0;
        }

    }

    public int UpdateMyprofile(String name,String passward){

        MyProfile myProfile = new MyProfile();
        myProfile = myProfileDAO.getProfileData();
        myProfile.setName(name);

        String passwardEncrypted = passwardEncryptor.encryptString(passward);
        myProfile.setPassword(passwardEncrypted);

        if(myProfileDAO.updateProfileData(myProfile) != -1){
            return -1;
        }
        else {
            return 0;
        }
    }

    public Map<String,String> getProfileData(){

        Map map = new HashMap();
        MyProfile myProfile = myProfileDAO.getProfileData();
        if(myProfile != null){
            map.put("name", myProfile.getName());
            map.put("passward", myProfile.getPassword());
        }
        else {
            map = null;
        }
        return map;
    }

    public ArrayList<String> getClassList(){

        ArrayList<String> names = new ArrayList<>();
        List<TutionClass> classList = myProfileDAO.getClassList();
        for(TutionClass tutionClass : classList){
            names.add(tutionClass.getClassName());
        }
        return names;
    }

    public boolean ValidatePassward(String passward){

        MyProfile myProfile = myProfileDAO.getProfileData();
        String passwardDecrypted = passwardEncryptor.encryptString(passward);
        if(passwardDecrypted.equals(myProfile.getPassword())){
            return true;
        }
        else {
            // Should be corrected.
            return true;
        }
    }
}
