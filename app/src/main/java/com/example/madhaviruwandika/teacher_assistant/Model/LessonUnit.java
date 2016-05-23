package com.example.madhaviruwandika.teacher_assistant.Model;

/**
 * Created by Madhavi Ruwandika on 5/2/2016.
 */
public class LessonUnit {


    private int UnitID;
    private int Unit;
    private int class_Id;


    public LessonUnit() {
    }

    public LessonUnit(int unitID, int unit, int class_Id) {
        UnitID = unitID;
        Unit = unit;
        this.class_Id = class_Id;
    }

    public LessonUnit(int unit, int class_Id) {
        Unit = unit;
        this.class_Id = class_Id;
    }

    public int getUnitID() {
        return UnitID;
    }

    public void setUnitID(int unitID) {
        UnitID = unitID;
    }

    public int getUnit() {
        return Unit;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }

    public int getClass_Id() {
        return class_Id;
    }

    public void setClass_Id(int class_Id) {
        class_Id = class_Id;
    }


}

