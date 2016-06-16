package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.Util.ClassDataActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.R;


public class ViewExtra_ClassDetails extends Fragment {

    TextView classType;
    TextView time;
    TextView classDate;
    TextView classState;
    Button cancel;

    int extraClassID;
    String Ttime;
    String TDate;
    String TclassType;
    String TclassState = "";


    ClassController classController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_view_extra__class_details, container, false);

        classType = (TextView) v.findViewById(R.id.textViewClassType);
        time = (TextView) v.findViewById(R.id.textViewTime);
        classDate = (TextView) v.findViewById(R.id.textViewDate);
        classState = (TextView)v.findViewById(R.id.textViewClassState);
        cancel = (Button) v.findViewById(R.id.btnCancel);
        classController = new ClassController(getActivity());

        Bundle myBundle = this.getArguments();
        extraClassID = myBundle.getInt("extraClassID");
        Ttime = myBundle.getString("time");
        TDate = myBundle.getString("date");
        TclassType = myBundle.getString("type");
        TclassState = myBundle.getString("state");


        classType.setText(TclassType);
        time.setText(Ttime);
        classDate.setText(TDate);
        classState.setText(TclassState);

        if(classState.getText().toString().equals("Cancel")){
            cancel.setEnabled(false);
        }

        CancelExtraClass();
        return v;

    }

    public void CancelExtraClass(){
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(classController.CancelExtraClass(extraClassID)==1)
                    Toast.makeText(getActivity(), "Extra class was Cancelled", Toast.LENGTH_LONG).show();
                else {
                    Toast.makeText(getActivity(), "Failed to cancel extra class", Toast.LENGTH_LONG).show();
                }
            }
        });

    }




}
