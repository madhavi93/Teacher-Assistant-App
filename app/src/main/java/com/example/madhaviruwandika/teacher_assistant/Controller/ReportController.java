package com.example.madhaviruwandika.teacher_assistant.Controller;

import android.app.Activity;

import com.example.madhaviruwandika.teacher_assistant.Model.PDFDocumentManeger;

/**
 * Created by Madhavi Ruwandika on 4/12/2016.
 */
public class ReportController {

    PDFDocumentManeger pdfDocumentManeger;

    ReportController(Activity activity){
        pdfDocumentManeger = new PDFDocumentManeger(activity);
    }

    public void generateStudentPerfomanceReport(){

    }



}
