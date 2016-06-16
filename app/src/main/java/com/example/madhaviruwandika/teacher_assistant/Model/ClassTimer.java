package com.example.madhaviruwandika.teacher_assistant.Model;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.madhaviruwandika.teacher_assistant.Activity.HomeActivity;
import com.example.madhaviruwandika.teacher_assistant.Controller.ClassController;
import com.example.madhaviruwandika.teacher_assistant.Controller.CommunicationController;
import com.example.madhaviruwandika.teacher_assistant.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Madhavi Ruwandika on 5/31/2016.
 */
public class ClassTimer {


    private int classTime;
    private int classID;
    private Timer timer;
    private Context context;
    ClassController classController;
    CommunicationController communicationController;

    public ClassTimer(int classTime,int classID,Context context){

        this.classTime = (classTime*60*1000);
        this.classID = classID;
        this.context = context;
        classController = new ClassController(context);
        communicationController = new CommunicationController(context);
        timer = new Timer();
        Log.d("Timer","Running Time.............................................................."+classTime);
        timer.schedule(new ClassFinshTask(),this.classTime);

    }

    class ClassFinshTask extends TimerTask{
        @Override
        public void run() {
            Log.d("Timer", "Running..............................................................");
            classController.markFinishingOftheClass();
            AppConstant.getInstance().setcontinuing_class(false);
            AppConstant.getInstance().setClassContinuing(null);
            AppConstant.getInstance().setMarkedAttendence(false);
            communicationController.SendSmsToNoifyFinishingtheClass(classID);

            Intent intent = new Intent();
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
            Notification notification = new Notification.Builder(context)
                    .setTicker("")
                    .setContentTitle("Class Notification")
                    .setContentText("Time to finish the class")
                    .setSmallIcon(R.drawable.app_icon)
                    .setContentIntent(pendingIntent).getNotification();

            notification.flags = Notification.FLAG_AUTO_CANCEL;
            NotificationManager nm  = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.notify(0,notification);




        }
    }

}
