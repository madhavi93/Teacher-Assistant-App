package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

public class addPaymentFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ClassController classController;
    StudentController studentController;

    int StudentIDPos;
    int ClassIDPos;

    Context context;

    List<TutionClass> tutionClasses ;
    List<Student> students;

    Spinner spinnerstd;
    Spinner spinnercls;

    List<String> categoriesStudent ;

    private OnFragmentInteractionListener mListener;

    public addPaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("MY","£££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add_payment, container, false);
        context= rootView.getContext();

        classController = new ClassController(getContext());
        studentController = new StudentController(getContext());

        spinnerstd = (Spinner) rootView.findViewById(R.id.spinnerSName) ;
        spinnercls = (Spinner) rootView.findViewById(R.id.spinnerClass);

        tutionClasses = classController.getClassList();
        students = studentController.getDetails();


        List<String> categoriesClasses = new ArrayList<String>();
        categoriesClasses.add("");
        //set class list for spinner
        for(int i=0;i<tutionClasses.size();i++){

            categoriesClasses.add(tutionClasses.get(i).getClassName());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categoriesClasses);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnercls.setAdapter(dataAdapter);

        // Spinner click listener
        spinnercls.setOnItemSelectedListener(this);

        return rootView;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        Log.d("PPPPPPP","£££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££");

        Spinner spinner = (Spinner) parent;
        // what shoud do when item in classSpinner is selected

        //if(spinner.getId() == R.id.spinnerClass) {
         /*
            categoriesStudent = new ArrayList<String>();
            categoriesStudent.add("");

            for (int i = 0; i < students.size(); i++) {
                categoriesStudent.add(students.get(i).getName());
            }
            // Spinner click listener
            spinnerstd.setOnItemSelectedListener(this);
            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categoriesStudent);
            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinnerstd.setAdapter(dataAdapter);


            //set Selected Class ID
            ClassIDPos = position;

        /*

         }
        // what should do when item in StudentSpinner is selected
        else if(spinner.getId() == R.id.spinnerSName){

            StudentIDPos = position;

        }
        */

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View v){
        if(addPaymentDetails()){                                    // call add student method
            Toast.makeText(context, "Student Details are succesfully added.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Student Details are not added succesfully.Try Again", Toast.LENGTH_LONG).show();
        }
    }

    // method for call class controller to add details to database
    public boolean  addPaymentDetails(){

        int StudentID = students.get(StudentIDPos).getS_id();
        int ClassID=tutionClasses.get(ClassIDPos).getClassID();

        if(classController.addPayment(StudentID,ClassID)==1){
            return true;
        }
        else {return false;}
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
