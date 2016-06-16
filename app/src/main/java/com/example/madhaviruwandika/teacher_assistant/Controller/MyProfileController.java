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

    }

    /*
    *this method is called when account details are going to added in first time
    * just after installing the app and after reseting the data
     */
    public int SaveProfileDataFirstTime(String name,String passward){

        // get date of password changed
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String Date = sdf.format(calendar.getTime());

        String passwardEncrypt = null;
        try {
            // encrypt password before saving
            passwardEncrypt = PasswardEncryptor.encrypt(passward);
            MyProfile myProfile = new MyProfile(name,passwardEncrypt,Date);
            if(myProfileDAO.saveProfileData(myProfile)!= -1){
                return 1;
            }
            else{
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }


    public int UpdateMyprofile(String name,String passward){

        // get profile details from the database to match the current password
        MyProfile myProfile = new MyProfile();
        myProfile = myProfileDAO.getProfileData();
        myProfile.setName(name);

        String passwardEncrypted = null;
        try {
            // encrypt passward
            passwardEncrypted = PasswardEncryptor.encrypt(passward);
            myProfile.setPassword(passwardEncrypted);

            if(myProfileDAO.updateProfileData(myProfile) != -1){
                return 1;
            }
            else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean ValidatePassward(String passward) {

        MyProfile myProfile = myProfileDAO.getProfileData();
        String passwardDecrypted = null;
        try {
            // decrypt stored passward in the database
            passwardDecrypted = PasswardEncryptor.decrypt(myProfile.getPassword());
            // check equility of passwords
            if (passwardDecrypted.equals(passward)) {
                return true;
            } else {
                // Should be corrected.
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /*
    *this method is to reset application
    *will empty all the tables
     */
    public int ResetApplication(){
        if(myProfileDAO.resetApp()!= -1){
            return 1;
        }
        else return 0;
    }
}
