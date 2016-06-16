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
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.madhaviruwandika.teacher_assistant.Controller.StudentController;
import com.example.madhaviruwandika.teacher_assistant.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PerfomanceFragment extends Fragment {

    BarChart barChart;
    RelativeLayout chartContainer;
    TextView std;

    Bundle mybundle;
    StudentController studentController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfomance, container, false);
        barChart = (BarChart) view.findViewById(R.id.perfomance_chart);
        std = (TextView) view.findViewById(R.id.stdName);
        chartContainer = (RelativeLayout)view.findViewById(R.id.container);

        // initialize bundle to get data from previous activity
        mybundle = this.getArguments();
        // initialize controller
        studentController = new StudentController(getActivity());

        // collect data to create chart
        List<List<Integer>> groupList = studentController.getDataToStudentPerfomanceReport(mybundle.getInt("ClassID"),mybundle.getInt("StudentID"));
        // assign data according to the chart format
        List<Integer> groupmin = groupList.get(0);
        List<Integer> groupStdMark = groupList.get(1);
        List<Integer> groupMax = groupList.get(2);

        // create BarEntry for group 1
        ArrayList<BarEntry> group1 = new ArrayList<BarEntry>();
        for(int i=0;i<groupmin.size();i++ ){
            group1.add(new BarEntry(groupmin.get(i),i));
        }

        // create BarEntry for group 2
        ArrayList<BarEntry> group2 = new ArrayList<BarEntry>();
        for(int i=0;i<groupStdMark.size();i++ ){
            group2.add(new BarEntry(groupStdMark.get(i),i));
        }

        // create BarEntry for group 3
        ArrayList<BarEntry> group3 = new ArrayList<BarEntry>();
        for(int i=0;i<groupMax.size();i++ ){
            group3.add(new BarEntry(groupMax.get(i),i));
        }

        // creating dataset for group1
        BarDataSet barDataSet1 = new BarDataSet(group1, "Min");
        barDataSet1.setColors(new int[]{Color.RED});

        // creating dataset for group2
        BarDataSet barDataSet2 = new BarDataSet(group2, "Student's Mark");
        barDataSet2.setColors(new int[]{Color.BLUE});

        // creating dataset for group3
        BarDataSet barDataSet3 = new BarDataSet(group3, "Max");
        barDataSet3.setColors(new int[]{Color.GREEN});

        // combined all dataset into an arraylist
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);

        // initialize the Bardata with argument labels and dataSet
        ArrayList<String> labels = new ArrayList<>();
        for (int i=0;i<group1.size();i++)
        {
            labels.add("Exam_"+(i+1));

        }

        BarData data = new BarData(labels,dataSets);
        barChart.setData(data);
        barChart.setDescription("Exams");
        std.setText(mybundle.getString("name"));

        return view;
    }




}
