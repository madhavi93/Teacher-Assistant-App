package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.List;
import java.util.Map;

public class AttendenceAndPamentFragment extends Fragment {

    TextView atendenceState;
    TableLayout logsTableLayout;

    Bundle myBundle;
    StudentController studentController;
    List<Map<String,String>> payments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_attendence_and_pament, container, false);

        //initialize widgets in the form
        atendenceState = (TextView)v.findViewById(R.id.attendence_State);
        logsTableLayout = (TableLayout)v.findViewById(R.id.dateList);
        // initialize bundele to get data which passed from the previous activity
        myBundle = this.getArguments();

        // initialize controllers
        studentController = new StudentController(getActivity());
        String AState = studentController.getAttendenceState(myBundle.getInt("StudentID"),myBundle.getInt("ClassID"));

        if(AState != null) {
            atendenceState.setText(AState);
        }

        // add pament data to view in table
        payments = studentController.getPayments(myBundle.getInt("StudentID"),myBundle.getInt("ClassID"));

        if(payments != null){
            for (Map<String,String> payment : payments) {

                TableRow tr = new TableRow(getActivity());

                TextView number = new TextView(getActivity());
                number.setText(payment.get("No"));
                tr.addView(number);

                TextView date = new TextView(getActivity());
                date.setText(String.valueOf(" " + payment.get("date")));
                tr.addView(date);

                TextView month = new TextView(getActivity());
                month.setText(payment.get("month"));
                tr.addView(month);

                logsTableLayout.addView(tr);
            }
        }
        return v;
    }


}
