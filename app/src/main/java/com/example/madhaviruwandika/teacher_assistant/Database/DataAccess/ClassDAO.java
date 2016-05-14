package com.example.madhaviruwandika.teacher_assistant.Database.DataAccess;

import com.example.madhaviruwandika.teacher_assistant.Model.ExtraClass;
import com.example.madhaviruwandika.teacher_assistant.Model.Payment;
import com.example.madhaviruwandika.teacher_assistant.Model.TutionClass;

import java.util.List;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public interface ClassDAO {

    public long addClass(TutionClass tutionClass);
    public int getClassID();
    public List<TutionClass> getClassDetails();
    public long addPayment(Payment payment);
    public int getPaymentID();
    public long addExtraClass(ExtraClass exClass);
    public int getExClassID();
    public TutionClass getClassByID(int id);

}
