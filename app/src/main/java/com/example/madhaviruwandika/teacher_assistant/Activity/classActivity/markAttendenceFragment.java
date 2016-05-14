package com.example.madhaviruwandika.teacher_assistant.Activity.classActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madhaviruwandika.teacher_assistant.Adapter.Util.StudentRegisterAdapter;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.Model.Student;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Util.ItemRegisterName;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link markAttendenceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link markAttendenceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class markAttendenceFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    ListView v ;
    TextView className ;
    ArrayList<ItemRegisterName> register;
    StudentRegisterAdapter adapter;


   List<Student> studentList ;
    int classID = 0;
    ClassController classController;
    StudentController studentController;
    Bundle myBundle;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public markAttendenceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment markAttendenceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static markAttendenceFragment newInstance(String param1, String param2) {
        markAttendenceFragment fragment = new markAttendenceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_mark_attendence, container, false);

        className = (TextView) root.findViewById(R.id.textViewClass);
        classController = new ClassController(getContext());
        studentController = new StudentController(getContext());

        myBundle = this.getArguments();
        classID = myBundle.getInt("ClassID");

        TutionClass today_Class = new TutionClass();
        if(classID != 0) {
            today_Class = classController.getTutionClassByID(classID);
        }

        className.setText(today_Class.getClassName());



        studentList = studentController.getStudentListByClassID(classID);

        register = new ArrayList<>();

        for(int i=0;i<studentList.size();i++){
            register.add(new ItemRegisterName(studentList.get(i).getName(),false));
        }

        // Inflate the layout for this fragment

        v = (ListView) root.findViewById(R.id.register);


        adapter = new StudentRegisterAdapter(getActivity(),register);
        v.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        v.setAdapter(adapter);

        v.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {



                }

        });

        return root;
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

        /*if (context instanceof OnFragmentInteractionListener) {
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




