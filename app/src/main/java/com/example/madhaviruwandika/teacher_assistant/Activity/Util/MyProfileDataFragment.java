package com.example.madhaviruwandika.teacher_assistant.Activity.Util;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Controller.MyProfileController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MyProfileDataFragment extends Fragment {

    TextView name ;
    ListView listView;

    MyProfileController myProfileController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_profile_data, container, false);
        name = (TextView)v.findViewById(R.id.name);
        listView = (ListView)v.findViewById(R.id.listViewClassList);

        myProfileController = new MyProfileController(getActivity());

        Map<String,String> myprofile = myProfileController.getProfileData();
        if(myprofile != null){
            name.setText(myprofile.get("name"));
        }

        List<String> classNames = myProfileController.getClassList();
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,classNames);
        listView.setAdapter(adapter);

        return v;
    }

}
