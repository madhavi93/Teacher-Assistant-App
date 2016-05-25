package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.MyWorkActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.IntelligenceSyllabusController;
import com.example.madhaviruwandika.teacher_assistant.Model.Lesson;
import com.example.madhaviruwandika.teacher_assistant.R;

public class SeeCommentFragment extends Fragment {

    TextView className;
    TextView comment;
    Button button;

    Bundle myBundle;
    IntelligenceSyllabusController intelligenceSyllabusController;
    int classID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootVeiw =  inflater.inflate(R.layout.fragment_see_comment, container, false);

        className = (TextView)rootVeiw.findViewById(R.id.className);
        comment = (TextView)rootVeiw.findViewById(R.id.textViewComent);
        button = (Button) rootVeiw.findViewById(R.id.button);

        myBundle = this.getArguments();
        classID = myBundle.getInt("ClassID");
        className.setText(myBundle.getString("ClassName"));
        intelligenceSyllabusController = new IntelligenceSyllabusController(getActivity());

        if(((MyWorkActivity)getActivity()).getLessonCoveredToday()!= 0 && ((MyWorkActivity)getActivity()).getUnitID() != 0){

            String[] commentl = intelligenceSyllabusController.getCommentOnWorkAndSyllabus(classID,myBundle.getInt("UnitNo"),myBundle.getInt("LessonNO"));

            if(commentl[0] == ""){
                comment.setText(" "+commentl[1]);
            }
            else {
                comment.setText(" "+commentl[0]+"\n"+" "+commentl[1]);
            }

            ((MyWorkActivity)getActivity()).setLessonCoveredToday(0);
            ((MyWorkActivity)getActivity()).setUnitID(0);

        }

        OnButtonClick();
        return rootVeiw;


    }


    public void OnButtonClick(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] commentl = intelligenceSyllabusController.getCommentOnWorkAndSyllabus(classID);

                if(commentl[0] == ""){
                    comment.setText(" "+commentl[1]);
                }
                else {
                    comment.setText(" "+commentl[0]+"\n"+" "+commentl[1]);
                }

            }
        });

    }


}
