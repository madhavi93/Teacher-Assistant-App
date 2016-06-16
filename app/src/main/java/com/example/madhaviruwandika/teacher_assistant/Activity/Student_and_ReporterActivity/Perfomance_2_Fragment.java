package com.example.madhaviruwandika.teacher_assistant.Activity.Student_and_ReporterActivity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.mikephil.charting.charts.LineChart;

import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

public class Perfomance_2_Fragment extends Fragment {

    LineChart lineChart;
    RelativeLayout chartContainer;
    private TextView std;
    Bundle mybundle;
    StudentController studentController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfomance_2_, container, false);
        lineChart = (LineChart) view.findViewById(R.id.perfomance_chart);
        chartContainer = (RelativeLayout)view.findViewById(R.id.container);
        std = (TextView) view.findViewById(R.id.stdName);

        // initialize bundle to aed data which passed from the previous activity
        mybundle = this.getArguments();
        // initialize controller
        studentController = new StudentController(getActivity());


        // initialize the chart of performance with the values

        // creating list of entry of the chart
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Integer> markList = studentController.getExamMarkListOfStudent(mybundle.getInt("StudentID"));
        entries.add(new Entry(0f,0));
        for(int i=1;i<=markList.size();i++){
            entries.add(new Entry(markList.get(i - 1), i));
        }
        ArrayList<String> labels = new ArrayList<>();
        for (int i=0;i<entries.size();i++)
        {
            if(i==0){
                labels.add("");
            }
            else {
                labels.add("Exam_"+(i));
            }
        }
        // set data to the chart and modify chart appearance
        LineDataSet dataset = new LineDataSet(entries, "Marks");
        dataset.setColor(Color.RED);
        LineData data = new LineData(labels, dataset);
        lineChart.setData(data);
        lineChart.setDescription("Exams"); // set the description

        return view;

    }

}
