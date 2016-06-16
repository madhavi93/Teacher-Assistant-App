package com.example.madhaviruwandika.teacher_assistant.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Madhavi Ruwandika on 5/22/2016.
 */
public class SyllabusTree {

    public Map<Integer,List<TreeNode>> syllabus;

    public SyllabusTree(){
    }

    // add lessons to the map
    public void fillTree(List<LessonUnit> units,List<Lesson> lessons){
        syllabus = new HashMap<>();

        for (int i=0;i<units.size();i++){
            syllabus.put(units.get(i).getUnit(),new ArrayList<TreeNode>());
        }
        for (int j=0;j<lessons.size();j++){
            String state = "Red";// lesson is not finished
            if(lessons.get(j).getAmountCovered() != 1 && lessons.get(j).getAmountCovered() != 0 ){
                state = "blue"; // lesson is partitially covered
            }
            else if(lessons.get(j).getAmountCovered()==1){
                state = "White"; //lesson is fully covered
            }

            TreeNode treeNode = new TreeNode(state,lessons.get(j));
            syllabus.get(lessons.get(j).getUnitNo()).add(treeNode);
        }
    }

   public int getRequiredTimeToCoverRemaining(){

       int totalRequiredTime = 0;
       for(Integer key : syllabus.keySet()){
           for (TreeNode l : syllabus.get(key)) {
                if(l.treeState != "White") {
                    totalRequiredTime += (1 - l.lesson.getAmountCovered()) * l.lesson.getTotaltimeSupposedToSpend();
                }
           }
       }
       return totalRequiredTime;

   }

    public List<Lesson> getSortedLessonListAccordingToTime() {

        List<Lesson> SortedLessonListAccordingToTime = new ArrayList<>();

        for(Integer key : syllabus.keySet()){
            for (TreeNode l : syllabus.get(key)) {
                SortedLessonListAccordingToTime.add(l.lesson);
            }
        }

        // srt the list using buble Sort
        int n = SortedLessonListAccordingToTime.size();
        boolean swapped = false;
        do{
            swapped = false;
            int newLimit = n;
            for (int i=0;i<n;i++){

                if( SortedLessonListAccordingToTime.get(i).getAmountCovered()== 1){
                    SortedLessonListAccordingToTime.remove(i);
                    if(i==0){
                        i = 0;
                    }
                    else {
                        i =-1;
                    }
                    n=n-1;
                }
                if ((i != 0) && (((1-SortedLessonListAccordingToTime.get(i).getAmountCovered())*SortedLessonListAccordingToTime.get(i).getTotaltimeSupposedToSpend()) < ((1-SortedLessonListAccordingToTime.get(i-1).getAmountCovered())*SortedLessonListAccordingToTime.get(i-1).getTotaltimeSupposedToSpend()) ) ){
                    Lesson temp = SortedLessonListAccordingToTime.get(i-1);
                    SortedLessonListAccordingToTime.set(i-1,SortedLessonListAccordingToTime.get(i));
                    SortedLessonListAccordingToTime.set(i,temp);
                    swapped = true;
                    newLimit = i-1;
                }
            }
            n = newLimit;
        }while (swapped);

       return SortedLessonListAccordingToTime;
    }

}

class TreeNode{

    public String treeState;
    public Lesson lesson;

    public TreeNode(String treeState,Lesson lesson){
        this.treeState = treeState;
        this.lesson = lesson;
    }

}
