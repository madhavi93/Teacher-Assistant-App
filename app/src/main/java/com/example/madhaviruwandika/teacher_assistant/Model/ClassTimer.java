package com.example.madhaviruwandika.teacher_assistant.Model;

import android.content.Context;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Madhavi Ruwandika on 5/31/2016.
 */
public class ClassTimer {


    private int classTime;
    private Timer timer;
    ClassController classController;

    public ClassTimer(int classTime,Context context){
        this.classTime = (classTime*60*1000);
        classController = new ClassController(context);
        timer = new Timer();
        timer.schedule(new ClassFinshTask(),this.classTime);

    }

    class ClassFinshTask extends TimerTask{
        @Override
        public void run() {
            AppConstant.getInstance().setcontinuing_class(false);
            AppConstant.getInstance().setClassContinuing(null);
            AppConstant.getInstance().setMarkedAttendence(false);
            classController.markFinishingOftheClass();

        }
    }

}
