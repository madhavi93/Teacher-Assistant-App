package com.example.madhaviruwandika.teacher_assistant.Activity.Util;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Controller.MyProfileController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.Map;


public class ChangeMyProfileFragment extends Fragment {

    EditText name;
    EditText currentPassward;
    EditText newPassward;
    EditText reEnteredpassward;
    Button saveChange;
    Boolean appFirstTime = false;

    MyProfileController myProfileController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_change_my_profile, container, false);

        name = (EditText)v.findViewById(R.id.editTextMyName);
        currentPassward = (EditText)v.findViewById(R.id.editTextcurrentPassword);
        newPassward = (EditText) v.findViewById(R.id.editTextNewPassword);
        reEnteredpassward = (EditText) v.findViewById(R.id.editTextReEnter);
        saveChange = (Button) v.findViewById(R.id.buttonSave);

        myProfileController = new MyProfileController(getActivity());
        Map<String,String> myProfile = myProfileController.getProfileData();

        if(myProfile == null){
            appFirstTime = true;
        }

        else{
            name.setText(myProfile.get("name"));
        }
        OnSaveButtonClickListner();
        return v;
    }

    public void OnSaveButtonClickListner(){

        saveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentPasswardString = currentPassward.getText().toString().trim();
                String newPasswardString = newPassward.getText().toString();
                String reEnteredpasswardString = reEnteredpassward.getText().toString();

                if(!appFirstTime){

                    if(myProfileController.ValidatePassward(currentPasswardString)){
                        if(newPasswardString.equals(reEnteredpasswardString)){
                            if(myProfileController.UpdateMyprofile(name.getText().toString(),newPasswardString)==1){
                                Toast.makeText(getActivity(), "Passward is changed", Toast.LENGTH_LONG).show();
                                clearText();
                            }
                            else {
                                Toast.makeText(getActivity(), "Failed to change the passward", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "New passward and re-entered passward is not matched.", Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(getActivity(), "Current passward is incorrect", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    if(myProfileController.SaveProfileDataFirstTime(name.getText().toString(),newPasswardString)==1){
                        Toast.makeText(getActivity(), "Profile details are saved.", Toast.LENGTH_LONG).show();
                        clearText();
                    }
                    else {
                        Toast.makeText(getActivity(), "Failed to save the profile details.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void clearText(){
        name.setText("");
        currentPassward.setText("");
        newPassward.setText("");
        reEnteredpassward.setText("");
    }

}
