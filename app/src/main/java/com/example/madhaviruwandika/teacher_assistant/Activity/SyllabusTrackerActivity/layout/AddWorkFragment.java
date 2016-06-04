package com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Activity.SyllabusTrackerActivity.MyWorkActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.IntelligenceSyllabusController;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.example.madhaviruwandika.teacher_assistant.Validator.InputValidator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddWorkFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner spinnerClass;
    Spinner spinnerUnit;
    Spinner spinnerLesson;
    Spinner spinnerAmountCovered;
    EditText timeTaken;
    EditText procedure;
    Button add;
    TextView className;
    Button seeComment;

    Bundle myBundle;
    ClassController classController;
    IntelligenceSyllabusController intelligenceSyllabusController;

    int classID = 0;
    String classN;
    int UnitID = 0;
    int LessonId = 0;
    Double amountCovered;
    int timePeriod;
    String procedureNote;

    List<Map<String, String>> LessonList;
    List<Map<String,String>> unitList;
    Map<String,String> work;


    public AddWorkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_add_work, container, false);
        spinnerClass = (Spinner)rootView.findViewById(R.id.spinnerClass);
        spinnerUnit = (Spinner)rootView.findViewById(R.id.spinnerUnit);
        spinnerLesson = (Spinner)rootView.findViewById(R.id.spinnerLesson);
        spinnerAmountCovered = (Spinner)rootView.findViewById(R.id.spinnerAmountCovered);
        timeTaken = (EditText)rootView.findViewById(R.id.editTextTimePeriod);
        procedure = (EditText)rootView.findViewById(R.id.editTextProcedure);
        add = (Button)rootView.findViewById(R.id.buttonAdd);
        seeComment = (Button)rootView.findViewById(R.id.buttonSeeComment);
        className = (TextView)rootView.findViewById(R.id.className);

        myBundle = this.getArguments();
        classID = myBundle.getInt("ClassID");
        className.setText(myBundle.getString("ClassName"));

        classController = new ClassController(getActivity());
        intelligenceSyllabusController = new  IntelligenceSyllabusController(getActivity());
        work = new HashMap<>();

        List<String> covered_Amount = new ArrayList<>();
        covered_Amount.add("");
        covered_Amount.add("0.25");
        covered_Amount.add("0.50");
        covered_Amount.add("0.75");
        covered_Amount.add("1.00");
        // Spinner click listener
        spinnerAmountCovered.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, covered_Amount);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerAmountCovered.setAdapter(dataAdapter);


        List<String> categories = classController.getClassListForSpinner();
        // Spinner click listener for class
        spinnerClass.setOnItemSelectedListener(this);
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerClass.setAdapter(dataAdapter3);



        myBundle = this.getArguments();
        classID = myBundle.getInt("ClassID");
        className.setText(myBundle.getString("ClassName"));
        spinnerClass.setSelection(classID);
        spinnerClass.setEnabled(false);


        OnAddButtonClickListner();
        onSeeCommentClickListner();
        return  rootView;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner =(Spinner)parent;
        if(spinner.getId() == R.id.spinnerClass){

            unitList = intelligenceSyllabusController.getUnitListByClassID(classID);
            List<String> units = new ArrayList<>();
            units.add("");
            for (int i=0;i<unitList.size();i++){
                units.add(unitList.get(i).get("Unit"));
            }

            // Spinner click listener for lesson No
            spinnerUnit.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,units);
            // Drop down layout style - list view with radio button
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // attaching data adapter to spinner
            spinnerUnit.setAdapter(dataAdapter2);


        }
        else if(spinner.getId() == R.id.spinnerUnit){

            if(position != 0) {
                UnitID = Integer.parseInt(unitList.get(position - 1).get("Unit"));

                LessonList = intelligenceSyllabusController.getLessonListByUnitIDandClassID(classID, UnitID);
                List<String> lessons = new ArrayList<>();
                lessons.add("");
                for (int i = 0; i < LessonList.size(); i++) {
                    lessons.add(LessonList.get(i).get("lessonNo")+"-"+LessonList.get(i).get("Lesson"));
                }

                // Spinner click listener for lesson No
                spinnerLesson.setOnItemSelectedListener(this);
                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, lessons);
                // Drop down layout style - list view with radio button
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinnerLesson.setAdapter(dataAdapter2);
            }

        }
        else  if(spinner.getId() == R.id.spinnerLesson){
            if(position!= 0)
            {
                LessonId = Integer.parseInt(LessonList.get(position-1).get("lessonNo"));

            }


        }
        else if(spinner.getId() == R.id.spinnerAmountCovered){
            if(position!= 0){
                amountCovered = Double.parseDouble(spinner.getSelectedItem().toString());
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void OnAddButtonClickListner(){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addData() == 1){

                    ((MyWorkActivity)getActivity()).setLessonCoveredToday(LessonId);
                    ((MyWorkActivity)getActivity()).setUnitID(UnitID);
                    Toast.makeText(getActivity(), "Details are succesfully added.", Toast.LENGTH_LONG).show();
                    ClearText();
                }
                else {
                    Toast.makeText(getActivity(), "Details are not added.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void onSeeCommentClickListner(){

        seeComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewPager viewPager = (ViewPager)getActivity().findViewById(R.id.container);
                viewPager.setCurrentItem(1);


            }
        });
    }

    public int addData(){

        boolean valiedCheck = true;

        if(InputValidator.isValidDigits(timeTaken.getText().toString())){
            try {
                timePeriod = Integer.parseInt(timeTaken.getText().toString());

            }catch (NumberFormatException e){
                valiedCheck = false;
            }
        }
        else {
            valiedCheck = false;
        }

        procedureNote = procedure.getText().toString();

        if(valiedCheck){

            return intelligenceSyllabusController.addDailyWork(classID,UnitID,LessonId,timePeriod,amountCovered,procedureNote);
        }
        else
        {return 0;}
    }

    public void ClearText(){

        spinnerUnit.setSelection(0);
        spinnerLesson.setSelection(0);
        spinnerAmountCovered.setSelection(0);
        timeTaken.setText("");
        procedure.setText("");
    }


}
