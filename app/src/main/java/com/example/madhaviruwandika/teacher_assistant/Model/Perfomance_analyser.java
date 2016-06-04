package com.example.madhaviruwandika.teacher_assistant.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Madhavi Ruwandika on 5/26/2016.
 */
public class Perfomance_analyser {

    List<Student_perfomance> studentPerfomances ;

    public Perfomance_analyser(){
        studentPerfomances = new ArrayList<>();
    }

    public List<Integer> getGraphDetailsForOverallPerfpomance(List<Student_perfomance> list){

        studentPerfomances = sortTheMarkList(list);
        List<Integer> markInRange = new ArrayList<>();
        for (int i=0;i<10;i++){
            markInRange.add(i, 0);
        }
        for (int i= 0;i<studentPerfomances.size();i++){

            if(studentPerfomances.get(i).getMark()<100){
                int currentCount = markInRange.get(studentPerfomances.get(i).getMark()/10)+1;
                markInRange.set((studentPerfomances.get(i).getMark()/10),currentCount);
            }
            else if (studentPerfomances.get(i).getMark() == 100){
                int currentCount = markInRange.get((studentPerfomances.get(i).getMark()/10)-1)+1;
                markInRange.set(((studentPerfomances.get(i).getMark()/10)-1),currentCount);
            }
            else {
                studentPerfomances = null;
            }
        }
        return markInRange;
    }


    public List<Student_perfomance> sortTheMarkList(List<Student_perfomance> list){

        studentPerfomances = list;
        // srt the list using buble Sort
        int n = studentPerfomances.size();
        boolean swapped = false;
        do{
            swapped = false;
            int newLimit = n;
            for (int i=0;i<n;i++){
                if(studentPerfomances.get(i)!= null) {
                    if ((i != 0) && studentPerfomances.get(i).getMark() < studentPerfomances.get(i - 1).getMark()) {
                        Student_perfomance temp = studentPerfomances.get(i - 1);
                        studentPerfomances.set(i - 1, studentPerfomances.get(i));
                        studentPerfomances.set(i, temp);
                        swapped = true;
                        newLimit = i - 1;
                    }
                }
            }
            n = newLimit;
        }while (swapped);

       return studentPerfomances;
    }

    public int[] getMaxAndMinOfTheExam(List<Student_perfomance> list){
        int[] minMax = new int[2];
        minMax[0] = 0;
        minMax[1] = 0;
        if(list.size() != 0) {

            studentPerfomances = sortTheMarkList(list);
            minMax[0] = studentPerfomances.get(0).getMark();
            minMax[1] = studentPerfomances.get(studentPerfomances.size() - 1).getMark();
        }
        return minMax;

    }

    public String AttendenceState(int classDays,int NoOfDatesAttended){


        if(classDays!= 0) {
            int percentage = ((NoOfDatesAttended / classDays) * 100);

            if (percentage < 50) {
                Log.d("PPPPP",">>>>>>>>>>>>>>>"+percentage+">>>>>>>>>>>>>>>>>");
                return "Very poor attendence.";
            } else if (percentage >= 50 && percentage < 70) {
                return "poor Attendence";
            } else if (percentage >= 70 && percentage < 90) {
                return "Attendence is good";
            } else {
                return "Very good Attendence";
            }
        }
        else
            return "Class is not started.";
    }

    public static ArrayList<String[]> convertToMarkRangeOutOf100(ArrayList<String[]> s,int markRange){
        ArrayList<String[]> mymark = s;
        for (int i=0;i<s.size();i++){
            int mark = Integer.parseInt(mymark.get(i)[1]);
            Log.d("Mark", ">>>>>>>>>>>>>>>>>>>>" + markRange + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            int newMark = (int)(((double)mark/(double)markRange) * 100);
            Log.d("MarkNEW",">>>>>>>>>>>>>>>>>>>>"+newMark+">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            String[] std = {s.get(i)[0],String.valueOf(newMark)};
            mymark.set(i,std);
        }
        return mymark;
    }

}
